#!/usr/bin/env python3

from radon.cli import Config
from radon.cli.harvest import Harvester, CCHarvester, RawHarvester, MIHarvester
from radon.metrics import h_visit
from radon.complexity import cc_rank, SCORE
from cohesion import module
import argparse
import sys
import os
# import tqdm


COMPLEX_TRESHOLD = 9
MI_TRESHOLD = 20
HAL_VOLUME_TRESHOLD = 10
HAL_DIFFICULTY_TRESHOLD = 10
HAL_BUGS_TRESHOLD = 1
COHESION_TRESHOLD = 66.66

def is_python_file(file):
    return len(file) >= 3 and file[-3:] == ".py"


def list_files(path):
    if os.path.isfile(path):
        return [path]

    folders = []
    for i in os.walk(path):
        folders.append(i)

    paths = []
    for address, dirs, files in folders:
        for file in files:
            if is_python_file(file):
                paths.append(address + "/" + file)

    return paths


class HalsteadHarvester(Harvester):
    def gobble(self, fobj):
        data = fobj.read()
        return {"result": h_visit(data)}


class CodeAnalyzer:
    def __init__(self, path, out):
        self.paths = list_files(path)
        self.root = path
        self.crop_paths = os.path.isdir(self.root)
        self.out = out

    def crop_file(self, filename):
        if self.crop_paths:
            filename = filename[len(self.root):]
        return filename

    def log(self, s):
        if self.out is None:
            print(s)
        else:
            with open(self.out, "a") as f:
                f.write(s + "\n")
    
    def analyze_complexity(self):
        config = Config(
            exclude=None,
            ignore=None,
            order=SCORE,
            show_closures=False,
            no_assert=True,
            min='A',
            max='F',
        )
        harvester = CCHarvester(self.paths, config)
        results = harvester._to_dicts()
                
        complex_files = []
        for file in results:
            for func in results[file]:
                if func["complexity"] > COMPLEX_TRESHOLD:
                    filename = self.crop_file(file)
                    complex_files.append((filename, func["name"], func["complexity"]))

        self.log("")
        if len(complex_files) == 0:
            self.log(f"================")
            self.log(f"======== No functions with Cyclomatic Complexity > {COMPLEX_TRESHOLD}")
            self.log(f"================")
        else:
            self.log(f"================")
            self.log(f"======== Functions with Cyclomatic Complexity > {COMPLEX_TRESHOLD}")
            self.log(f"================")
            self.log("")
            for i, (filename, funcname, complexity) in enumerate(complex_files):
                self.log(f"#{i + 1} File: {filename}\n\tFunction: {funcname}\n\tComplexity: {complexity}")

    def analyze_mi(self):
        config = Config(
            exclude=None,
            ignore=None,
            order=SCORE,
            show_closures=False,
            no_assert=True,
            min='A',
            max='F',
            multi=False,
        )
        harvester = MIHarvester(self.paths, config)
        results = harvester.results
        low_mi_files = []

        for r in results:
            mi = r[1]["mi"]
            if mi < MI_TRESHOLD:
                filename = self.crop_file(r[0])
                low_mi_files.append((filename, mi))
        
        self.log("")
        if len(low_mi_files) == 0:
            self.log(f"================")
            self.log(f"======== No files with Maintainability Index < {MI_TRESHOLD}")
            self.log(f"================")
        else:
            self.log(f"================")
            self.log(f"======== Files with Maintainability Index < {MI_TRESHOLD}")
            self.log(f"================")
            self.log("")
            for i, (filename, mi) in enumerate(low_mi_files):
                self.log(f"#{i + 1} File: {filename}\n\tMaintainability Index: {mi}")

    def analyze_halstead(self):
        config = Config(
            exclude=None,
            ignore=None,
        )
        harvester = HalsteadHarvester(self.paths, config)
        results = harvester.results
        halstead_metrics = []

        for r in results:
            for func in r[1]["result"].functions:
                volume = func[1].volume
                difficulty = func[1].difficulty
                bugs = func[1].bugs
                funcname = func[0]
        
                if volume > HAL_VOLUME_TRESHOLD or difficulty > HAL_DIFFICULTY_TRESHOLD or bugs > HAL_BUGS_TRESHOLD:
                    filename = self.crop_file(r[0])
                    halstead_metrics.append((filename, funcname, volume, difficulty, bugs))
        
        self.log("")
        if len(halstead_metrics) == 0:
            self.log(f"================")
            self.log(f"======== No functions with Halstead metrics grater than tresholds:")
            self.log(f"======== Volume > {HAL_VOLUME_TRESHOLD}, Difficulty > {HAL_DIFFICULTY_TRESHOLD}, Bugs > {HAL_BUGS_TRESHOLD}")
            self.log(f"================")
        else:
            self.log(f"================")
            self.log(f"======== Functions with Halstead metrics grater than tresholds:")
            self.log(f"======== Volume > {HAL_VOLUME_TRESHOLD}, Difficulty > {HAL_DIFFICULTY_TRESHOLD}, Bugs > {HAL_BUGS_TRESHOLD}")
            self.log(f"================")
            self.log("")
        for i, (filename, funcname, volume, difficulty, bugs) in enumerate(halstead_metrics):
            self.log(f"#{i + 1} File: {filename}\n\tFunction: {funcname}\n\tVolume: {volume}, Difficulty: {difficulty}, Bugs: {bugs}")

    def analyze_cohesion(self):
        file_modules = {
            filename: module.Module.from_file(filename)
            for filename in self.paths
        }
        low_cohesion = []

        for filename, file_module in file_modules.items():
            for class_name, stats in file_module.structure.items():
                if stats["cohesion"] < COHESION_TRESHOLD:
                    low_cohesion.append((self.crop_file(filename), class_name, stats["cohesion"]))
            
        self.log("")
        if len(low_cohesion) == 0:
            self.log(f"================")
            self.log(f"======== No classes with cohesion < {COHESION_TRESHOLD}%")
            self.log(f"================")
        else:
            self.log(f"================")
            self.log(f"======== Classes with cohesion < {COHESION_TRESHOLD}%")
            self.log(f"================")
            self.log("")
        for i, (filename, class_name, cohesion) in enumerate(low_cohesion):
            self.log(f"#{i + 1} File: {filename}\n\tClass: {class_name}\n\tCohesion: {cohesion}%")

    def analyze_all():
        self.analyze_complexity()
        self.analyze_mi()
        self.analyze_halstead()
        self.analyze_cohesion()
    
    
def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('-p', dest='path', default=".",
        help="Path to a file or a project")
    parser.add_argument('-o', dest='out', default=None,
        help="Output file")
    args = parser.parse_args()

    analyzer = CodeAnalyzer(args.path, args.out)
    analyzer.analyze_complexity()
    analyzer.analyze_mi()
    analyzer.analyze_halstead()
    analyzer.analyze_cohesion()
    

if __name__ == "__main__":
    sys.exit(main())
