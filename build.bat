@echo off
mkdir bin

javac -d bin -cp ./src --module-path "./javafx-sdk-21/lib" --add-modules=javafx.controls,javafx.fxml src/*.java

java -cp ./bin --module-path "./javafx-sdk-21/lib" --add-modules=javafx.controls,javafx.fxml Main