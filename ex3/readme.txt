src中,OberonScanner.java是由JFlex产生的词法分析器,由以下步骤生成:
①可以将src\oberon拷贝至和lib/JFex.jar同个目录下;
②进入该目录,运行"java -jar JFlex.jar oberon.flex"

gen.bat	根据oberon.cup定义的语义规则,产生一个语义分析器Parser.java
build.bat	编译所有的java代码
doc.bat	为java代码生成javadoc文档
run.bat	测试src\testcases\factorialGcd.*的代码的语义是否正确,正确的话生成函数调用关系图
test.bat	测试src\testcases\sample.*的代码的语法是否正确,正确的话生成函数调用关系图




ps:
This flex file "oberon.flex" in ex3 is different from the one in the ex2.This one is used with cup, that one is not.