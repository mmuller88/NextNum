# NextNum task for interview
Implement the method nextNum() and a minimal but effective set of unit tests. Implement in the language of your choice, Python is preferred, but Java and other languages are completely fine. Make sure your code is exemplary, as if it was going to be shipped as part of a production system.
As a quick check, given Random Numbers are [-1, 0, 1, 2, 3] and Probabilities are [0.01, 0.3, 0.58, 0.1, 0.01] if we call nextNum() 100 times we may get the following results. As the results are random, these particular results are unlikely.

-1: 1 times 
0: 22 times 
1: 57 times 
2: 20 times 
3: 0 times

You may use Random.nextFloat() which returns a pseudo random number between 0 and 1.

# How to use
Run ```mvn clean install``` for running the unit tests and creating the jar file in /target .
If you want use the jar library in your local project with adding this dependency to your pom file:

```
<dependency>
    <groupId>org.interview</groupId>
    <artifactId>nextnum</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```