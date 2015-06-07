@echo off
javac -d bin -classpath ..\bin src\exceptions\*.java
javac -d bin -classpath ..\bin src\Lexical.java
cd bin
javac -d . ..\src\OberonScanner.java
javac -d . ..\src\Main.java
cd ..
pause