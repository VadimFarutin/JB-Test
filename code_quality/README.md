# Code Quality

Script uses [radon](https://github.com/rubik/radon) and [cohesion](https://github.com/mschwager/cohesion) libraries to analyze Python code.  
Calculated metrics: Cyclomatic Complexity, Maintainability Index, Halstead metrics (volume, difficulty, bugs), Cohesion.

## Install dependencies
```
pip install radon
python -m pip install cohesion
```

## Usage
```
python ./main.py -p INPUT_PATH -o OUTPUT_FILE
```
`-p` -- Path to a file or a project to analyze. Default value: `"."`  
`-o` -- Path to a file to print results to. If `OUTPUT_FILE=None`, then results are printed on the screen. Default value: `None`

## Examples
Results for one file and Python project are saved in `output_main.txt` and `output_radon.txt`.

### Download projects to test on
I have evaluated script on radon project itself.
```
mkdir repos
cd repos
git clone https://github.com/rubik/radon.git
cd ..
```

### Run analyzer
```
python ./main.py -p ./repos/radon -o output_radon.txt
python ./main.py -p ./main.py -o output_main.txt
```