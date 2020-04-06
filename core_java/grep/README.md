# Introduction
The `grep` app allows users to search for a spedific text pattern recursively in a given directory and output the matched lines to a file.

To achieve the goal, the basic Java libraries were used and then applied Lambda and Stream APIs to improve the project.

We also used Maven for project management and IntelliJ as our IDE.

# Usage
The program takes three arguments:
```
regex rootPath outputFile
```
1. `regex`: the patter that we  want to find in a file.
2. `rootPath`: the given directory that contian the target pattern.
3. `outputFile`: the file that the matched lines are put into.

Example: we want to search for a pattern `.*IllegalArgumentException.*` in root directory, and write the result to `grep.out` under `/tmp`.

```
.*IllegalArgutmentException.* ./src /tmp/grep.out
```

# Pseudocode
The pseudocode of `process` for this project is shown as below:
```r
matchedLines = []
for file in listFilesRecursively(rootDir)
	for line in readLines(file)
		if containsPattern(line)
			matchedLines.add(line)
writeToFile(matchedLines)
```

# Performance Issue
This program may have a memory problem if it works on large files(like 50 GB) since we read files line by line and store them into the memory. To solve this problem,  we use streams instead of loops, which is easier to read. It also condense the code without storing each line as a data structure.


# Improvement
1. Check the size of input file at first and the use the corresponding funtion to next procedure.
2. Read nultiple files at the same time by using parallelStream.
3. Output file location of the matched lines.


