#!/usr/bin/env python3

import argparse
import sys
import requests
from collections import defaultdict
import tqdm
import numpy as np
import csv
import math
import networkx as nx
import matplotlib.pyplot as plt 

FACEBOOK_USER = "facebook"
REACT_REPO = "react"
TOP_CONTRIBUTERS_N = 50
SINCE_DATE = "2020-01-01"
UNTIL_DATE = ""
REQUEST_PAGE_LEN = 100
MATRIX_FILENAME = "matrix.csv"
TOP_AUTHORS_FILENAME = "top.csv"
AUTH_FILENAME = "auth"


def build_graph(user, repo, n, from_date, to_date):
    username = ""
    token = ""
    with open(AUTH_FILENAME, "r") as csv_file:
        reader = csv.reader(csv_file, delimiter=',')
        for line in reader:
            username = line[0]
            token = line[1]        
    session = requests.Session()
    session.auth = (username, token)
    hostname = "https://api.github.com"
    
    contributors_url = f"https://api.github.com/repos/{user}/{repo}/stats/contributors"
    auth = session.post(hostname)
    r = session.get(contributors_url)
    contributors = r.json()
    total_cmp = lambda c: c['total']
    contributors = sorted(contributors, key=total_cmp, reverse=True)
    top_authors = [(contributor["author"]["login"], contributor["total"]) for contributor in contributors[:n]]

    authors_to_files = defaultdict(lambda: defaultdict(lambda: 0))
    
    commits_url = f"https://api.github.com/repos/{user}/{repo}/commits"
    for author, total in tqdm.tqdm(top_authors):
        commits_page = 0
        while True:
            params = {"author": author,
                      "since": from_date,
                      "until": to_date,
                      "per_page": REQUEST_PAGE_LEN,
                      "page": commits_page}
            auth = session.post(hostname)
            r = session.get(commits_url, params=params)
            commits = r.json()
            if len(commits) == 0:
                break

            for commit_info in commits:
                sha = commit_info["sha"]
                url = f"https://api.github.com/repos/{user}/{repo}/commits/{sha}"
                auth = session.post(hostname)
                r = session.get(url)
                commit_data = r.json()
                files = commit_data["files"]
                
                for file in files:
                    filename = file["filename"]
                    # TODO: use file["changes"] ?
                    authors_to_files[author][filename] += 1
            
            commits_page += 1

    adj_matrix = np.zeros((n, n))
    
    for i in range(n):
        for j in range(i):
            weight = 0
            for file in authors_to_files[top_authors[i][0]].keys():
                if file in authors_to_files[top_authors[j][0]]:
                    weight += authors_to_files[top_authors[i][0]][file] / top_authors[i][1] 
                    weight += authors_to_files[top_authors[j][0]][file] / top_authors[j][1] 

            adj_matrix[i][j] = weight
            adj_matrix[j][i] = weight
    
    np.savetxt(MATRIX_FILENAME, adj_matrix, delimiter=',')
    with open(TOP_AUTHORS_FILENAME, "w") as csv_file:
        writer = csv.writer(csv_file, delimiter=',')
        for line in top_authors:
            writer.writerow(line)    
    

def draw_graph():
    adj_matrix = np.loadtxt(MATRIX_FILENAME, delimiter=',')
    top_authors = []

    with open(TOP_AUTHORS_FILENAME, "r") as csv_file:
        reader = csv.reader(csv_file, delimiter=',')
        for line in reader:
            if len(line) < 2:
                continue
            top_authors.append(line)    
    
    for i in range(adj_matrix.shape[0]):
        for j in range(adj_matrix.shape[1]):
            adj_matrix[i][j] = int(adj_matrix[i][j] * int(top_authors[0][1]))
    
    A = adj_matrix
    G = nx.from_numpy_matrix(A, create_using=nx.DiGraph)
    layout = nx.spring_layout(G)
    labels = {i: author[0] for i, author in enumerate(top_authors)}
    sizes = [float(author[1]) for author in top_authors]
    nx.draw(G, layout, with_labels=True, labels=labels, node_size=sizes)
    w_labels = nx.get_edge_attributes(G, "weight")
    nx.draw_networkx_edge_labels(G, pos=layout, edge_labels=w_labels)
    plt.show()

    
def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('-u', dest='user', default=FACEBOOK_USER,
        help="GitHub username")
    parser.add_argument('-r', dest='repo', default=REACT_REPO,
        help="Repository name")
    parser.add_argument('-n', dest='n', default=TOP_CONTRIBUTERS_N, type=int,
        help="Number of top contributors to analize")
    parser.add_argument('-f', dest='from_date', default=SINCE_DATE,
        help="Only use commits after this date. Expected format: YYYY-MM-DD")
    parser.add_argument('-t', dest='to_date', default=UNTIL_DATE,
        help="Only use commits before this date. Expected format: YYYY-MM-DD")
    args = parser.parse_args()
    
    build_graph(args.user, args.repo, args.n, args.from_date, args.to_date)
    draw_graph()


if __name__ == "__main__":
    sys.exit(main())
