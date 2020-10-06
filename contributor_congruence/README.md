# Analysis of Socio-Technical Congruence

## Run
There is a rate limit for unauthenticated requests for GitHub API, which allows only up to 60 requests per hour ([rate-limiting](https://developer.github.com/v3/#rate-limiting)).
Therefore this script uses requests with Basic Authentication. Username and personal token should be placed in file **```auth```** in following format:
```
username,token
```

Build and draw graph:
```
python main.py
```

Help option available:
```
python main.py -h
```

## Solution
1. For each contributor from the top-50 get list of all commits, for each commit get list of all changed files.
2. For every pair of contributors and for every file they both have changed increase correspondent weight by number of times they have changed that file (normalizing by amount of commits they have made in total).

Graph drawing is implemented using NetworkX library.
