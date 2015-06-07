@echo off
javac -d bin -classpath ..\bin src\exceptions\*.java

cd src
javac -d ..\bin -classpath ..\bin\java_cup;..\bin;..\lib\jgraph.jar;..\lib\callgraph.jar;..\lib\flowchart.jar *.java
cd ..
pause
@echo on
