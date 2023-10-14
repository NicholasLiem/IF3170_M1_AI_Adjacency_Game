@echo off
mkdir test

javac -d ./test src/Algorithms/DataStructure/*.java src/Test/*.java
java -cp ./test Test.TestNodeAndTree
