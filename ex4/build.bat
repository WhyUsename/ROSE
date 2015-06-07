@echo off
@echo javac -d bin -classpath ..\bin src\exceptions\*.java


cd src
javac -d ..\bin -classpath ..\bin;..\lib\jgraph.jar;..\lib\callgraph.jar;..\lib\flowchart.jar *.java
cd ..
pause
@echo on
