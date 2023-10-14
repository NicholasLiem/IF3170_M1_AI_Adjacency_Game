@echo off

if exist test (
    echo Clearing existing "test" folder...
    rd /s /q test
)

mkdir test

javac -d ./test src/Algorithms/DataStructure/*.java src/Algorithms/*.java src/Test/*.java src/Utils/*.java
java -cp ./test Test.TestMinMaxTree
