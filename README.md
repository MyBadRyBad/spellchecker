# spellchecker

A generic spellchecker written in Java to meet the guidelines in the "Project Guidelines" section.

Project uses english words from 
https://github.com/dwyl/english-words

and tdebatty's implementation of Levenshtein distance
https://github.com/tdebatty/java-string-similarity


Build the Project
====================================
The project can can built using [Maven](http://maven.apache.org/).

```
$ cd spellchecker
$ mvn clean package
$ java -jar target/spellchecker-1.0.jar wooord
word
$ java -jar target/spellchecker-1.0.jar peeple
people
```

You can alternatively use the compiled spellchecker.jar to run the spellchecker without building the project.

```
$ java -jar spellchecker.jar wooord
word
$ java -jar spellchecker.jar peeple
people
```

## Sample Input
```
$ java -jar target/spellchecker-1.0.jar wooord
word
$ java -jar target/spellchecker-1.0.jar peeple
people 
$ java -jar target/spellchecker-1.0.jar jjoobbb
job
$ java -jar target/spellchecker-1.0.jar CUNsperrICY
conspiracy
$ java -jar target/spellchecker-1.0.jar eeEExpPLiIrrAArR
explorer
$ java -jar target/spellchecker-1.0.jar sheeple
NO SUGGESTION
```

***

## Project Guidelines

### Overview
Write a program that reads a large list of English words (e.g. from /usr/share/dict/words on a unix system) into memory, and uses that dictionary to provide spelling suggestions for user input.

### Language

Solutions should be written in 1 of the following languages: 

* Ruby
* Javascript
* Objective-C
* Go
* Java

### Spec

Your program should be a cli that takes first argument and outputs the results to STDOUT 

Sample classes of spelling mistakes to be corrected is as follows:

* Case (upper/lower) errors: "inSIDE" => "inside" 
* Repeated letters: "jjoobbb" => "job" 
* Incorrect vowels: "weke" => "wake" 

### Other Notes

* Any combination of the above types of error in a single word should be corrected (e.g. "CUNsperrICY" => "conspiracy").
* If there are many possible corrections of an input word, program can choose one in any way you like. It just has to be an English word that is a spelling correction of the input by the above rules.
* If no correction can be found using the above rules your program should print "NO SUGGESTION"

#### Examples

```
$ ./spellchecker sheeeeep
sheep
$ ./spellchecker peepple
people
$ ./spellchecker sheeple
NO SUGGESTION
```
