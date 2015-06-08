#Oberon‐0 语言

本实验的处理对象是 Oberon-0 语言，该语言中包含了高级程序设计语言的表达式，以及结构化程序设
计中的结构化控制结构、子程序、参数传递等机制的抽象。<br>

##1简介

用于编译原理实验的计算机语言应足够简单，但又不失其代表性。据此，本实验项目选择了 Oberon-0
语言为处理对象。<br>

Oberon-0 的来历可追溯到近 50 年前， 在此期间的程序设计语言发展约每 10 年就有一标志性成果。 1960
年， P. Naur 等人设计了 Algol 60 语言；约 10 年后，随着结构化程序设计思想的成熟， N. Wirth 设计出
远比 Algol 68 语言成功（特别是在教育界）的 Pascal 语言；又一个 10 年过去， N. Wirth 根据程序设计
和软件工程技术的最新进展，在 Pascal 基础上设计了 Modula-2 语言；又过了约 10 年后， M. Reiser 与
N. Wirth 一起，将 Pascal 语言和 Modula-2 语言的程序设计本质精华浓缩为 Oberon 语言。<br>

Oberon-0 语言是 Oberon 语言的一个子集，为程序员提供了良好的程序结构。在 Oberon-0 程序中，最基
本的语句是赋值语句；复合语句支持顺序、条件（ `if` 语句）和迭代（ `while` 语句）执行。 Oberon-0 中
还支持子程序这一重要概念，包括过程声明和过程调用两个范畴，并且提供了两种不同的参数传递方式：
按值调用（值参数）和按引用调用（可变参数）。<br>

然而 Oberon-0 的类型系统却十分简洁，仅有的基本数据类型是整数类型（ `INTEGER`）和布尔类型
（ `BOOLEAN`），因而可声明整数类型的常量和变量，也允许用算术运算符构造表达式；而表达式的比较
运算则产生 `BOOLEAN` 类型的值，并可用于逻辑运算。 Oberon-0 的复合数据类型包括数组和记录，且允
许任意嵌套；但最基本的指针类型或引用类型就被省略了。<br>

一个过程代表了由语句组成的功能单元，因而在一个过程的写法中自然会关系到名字的局部性
（ Locality）问题。 Oberon-0 语言支持将标识符声明为局部于某一过程，即仅在该过程本身范围内标识
符才是可见的或合法的。<br>

由 N. Wirth 本人编著的 Theory and Techniques of Compiler Construction: An Introduction（ Addison-Wesley,
1996, ISBN 0-201-40353-6，本课程教学网站提供了该教材的电子版）中，第 6 章描述了 Oberon-0 程序
设计语言的语法并给出一个样板程序。本文档的语法定义和例子程序即源于该教材，但作了少数改动，
因而本实验处理的 Oberon-0 语言应以本文档的定义为准。<br>

##2 词法定义

Oberon-0 语言定义了非常简单的语法规则。

###2.1 单词

与我们熟悉的 C、 C++、 Java 等语言不同， Oberon-0 语言是大小写无关的。 譬如， 保留字 `WHILE`、 `While`
和 `while` 三种写法是等价的；而标识符 `BALANCE`、 `Balance` 和 `balance` 是相同的标识符。<br>

Oberon-0 的标识符长度不允许超过 24 个字符（允许 24 个字符）。<br>

在 Oberon-0 中还支持括号风格的注释，在“ (*”和“ *)”之间的内容全部为注释；注意， Oberon-0 注
释不允许嵌套。<br>

###2.2基本数据类型

在 Oberon-0 程序中仅支持 `INTEGER` 和 `BOOLEAN` 两种基本数据类型，可以利用 `VAR` 声明这两种类型的
变量。<br>

`INTEGER` 类型的常量书写形式只允许 Pascal 语言的无符号整数；这些常量可以由 0 开头，但解释为八
进制常量（此时常量中不允许出现 8 和 9 两个数字），非 0 开头则解释为十进制常量。无论十进制还是
八进制整数常量，每一常量中包含数字（包括 0）的个数不可超过 12 个（从而限制了整数常量允许表
达范围的最大值）。<br>

`INTEGER` 类型常量与标识符之前必须以空白符号分隔；例如，扫描 25id 时应作为一个非法整数常量
处理，而不是理解为常量 25 和标识符 id 两个单词。注意，不支持书写 `BOOLEAN` 类型的常量 `TRUE`
和 `FALSE`。<br>

##3 语法定义

本小节以 EBNF 定义了 Oberon-0 语言的形式语法。<br>

###3.1 扩展BNF（EBNF）

EBNF 意即扩展的 BNF（ Extended BNF），是我们在实际应用中定义一门计算机语言的形式语法的国际
标准，参见 ISO/IEC 14977: 1996(E). The Standard Metalanguage Extended BNF。<br>

###3.2 Oberon-0 语言的 EBNF 定义

```
module = "MODULE" identifier ";"
			declarations```
         ["BEGIN"
			statement_sequence]
         "END" identifier "." ;
		 
declarations = ["CONST" {identifier "=" expression ";"}]
               ["TYPE" {identifier "=" type ";"}]
               ["VAR" {identifier_list ":" type ";"}]
               {procedure_declaration ";"} ;
			   
procedure_declaration = procedure_heading ";"
                        procedure_body ;
						
procedure_body = declarations
                 ["BEGIN"
					statement_sequence]
                 "END" identifier ;
				 
procedure_heading = "PROCEDURE" identifier [formal_parameters] ;

formal_parameters = "(" [fp_section {";" fp_section}] ")" ;

fp_section = ["VAR"] identifier_list ":" type ;

type = identifier | array_type | record_type ;

record_type = "RECORD"
				field_list
				{";" field_list}
              "END" ;
			  
field_list = [identifier_list ":" type] ;

array_type = "ARRAY" expression "OF" type ;

identifier_list = identifier {"," identifier} ;

statement_sequence = statement {";" statement} ;

statement = [assignment 
            | procedure_call 
            | if_statement 
            | while_statement] ;
			
while_statement = "WHILE" expression "DO"
					statement_sequence
                  "END" ;
				  
if_statement = "IF" expression "THEN"
					statement_sequence
               {"ELSIF" expression "THEN"
					statement_sequence}
			   ["ELSE"
					statement_sequence]
			   "END" ;
			   
procedure_call = identifier [actual_parameters] ;

actual_parameters = "(" [expression {"," expression}] ")" ;

assignment = identifier selector ":=" expression ;

expression = simple_expression
			[("=" | "#" | "<" | "<=" | ">" | ">=")
			simple_expression] ;
			
simple_expression = ["+" | "-"] term {("+" | "-" | "OR") term} ;

term = factor {("*" | "DIV" | "MOD" | "&") factor} ;

factor = identifier selector 
       | number 
       | "(" expression ")" 
	   | "~" factor ;
	   
number = integer ;

selector = {"." identifier | "[" expression "]"} ;

integer = digit {digit} ;

identifier = letter {letter | digit} ;

```

##4 语言描述

在 Theory and Techniques of Compiler Construction: An Introduction 中并未详细描述 Oberon-0 语言的语义
特性，因为该语言的语义可以按直观的方式参照其他程序设计语言（特别是沿 Algol 60、 Pascal、
Modula-2、 Oberon 这一家族的语言）来理解。<br>

本小节特别强调了 Oberon-0 可能引起理解上二义性的几个方面，使得在本实验项目中老师和所有学生
对该语言的理解是无二义的。<br>

###4.1 模块声明

一个模块中可声明类型、常量、变量、过程等，也可声明该模块的主程序（程序体包括在 `BEGIN` 和 `END`
之间）。但一个模块也允许没有主程序，仅声明一些类型、常量、变量、过程等供其他模块使用（尽管
本实验项目中尚未更深入地考虑这一问题）。<br>

一个模块声明的 `END` 之后的标识符必须与该模块的名字相同。<br>

###4.2运算符与表达式

算术表达式支持一元运算“ +”和“ –”，分别表示取正和取负运算；支持二元运算“ +”、“ –”、“ *”、“ `DIV`”
和“ `MOD`”，分别表示加法、减法、乘法、整除、取模运算。参与算术表达式的运算量必须是 `INTEGER`
类型，否则会产生类型不兼容错误；由于 Oberon-0 语言不支持实数除法运算（仅支持整除运算），因而
算术表达式的运算结果总是 `INTEGER` 类型。<br>

算术表达式可参与“ =”、“ #”（不等于）、“ <”、“ <=”、“ >”和“ >=”等关系运算，运算结果为 `BOOLEAN`
类型的值。 `BOOLEAN` 类型的值不允许参与任何关系运算，否则会产生类型不兼容错误。<br>

但是 `BOOLEAN` 类型的值可能参与逻辑运算“ &”、“ `OR`”和“ ~”， 分别表示“与”、“或”和“非”。 `INTEGER`
类型的值不允许参加任何逻辑运算，否则会产生类型不兼容错误。<br>

###4.3 类型声明

Oberon-0 允许类型声明，即以一个标识符重命名一个基本数据类型或复合数据类型。例如，<br>

```
TYPE
	UserId = NTEGER;
	VisitRecord = RECORD
		user: UserId;
		visits: INTEGER
	END;
```

此后，即可用这些标识符作为类型，以声明其他变量。例如，<br>

```
VAR
	id1, id2: UserId;
	rec: VisitRecord;
```

###4.4 选择符

Oberon-0 语言支持两种选择符：“ .”用于访问记录中的一个域；“ [ ]”用于以下标表达式访问一个数组
的元素。这两种选择符均可以嵌套使用，但必须保证：“ .”左边的标识符指称的是一个记录，右边的标
识符是该记录中的一个域；“ [”左边的标识符指称的是一个数组，“ [ ]”中间是一个求值结果为 `INTEGER`
的表达式。<br>

例如，以下选择符的使用是合法的：<br>

```
VAR
	accountList: ARRAY 100 OF RECORD
		account: INTEGER;
		balance: INTEGER
	END;
BEGIN
	accountList[1].account := 101;
	accountList[1].balance := 8500;
	...
```

###4.5 作用域

Oberon-0 语言是一个块（ Block）结构语言；凡在一个块中声明的所有标识符，其作用域仅局限在该块
之中。<br>

例如，在一个 `MODULE` 中声明的所有类型、常量和变量（参见语法单位 declarations）在该模块中
是可见的，包括该模块定义的所有块（过程声明）中都是可见的；而在一个 `PROCEDURE` 中声明的所有
类型、常量和变量（参见语法单位 declarations 和 FormalParameters）则仅在该过程中是可见
的，其他过程中不可见之。<br>

###4.6 过程声明与参数传递

如果一个过程声明的某个形式参数之前声明有保留字 `VAR`，则该参数称为一个可变参数，将采用按引
用传递（ Call by Reference）的参数传递方式，因而该过程可能有副作用；否则，该参数称为一个值参
数，将采用按值传递（ Call by Value）的参数传递方式，因而该过程不会产生副作用。严格的语义检查
应保证可变参数必须是一个左值（ L-value）。<br>

一个过程声明的 `END` 之后的标识符必须与该过程的名字相同。<br>

###4.7 预定义函数
Oberon-0 提供了 3 种预定义函数： `read()`、 `write()`和 `writeln`，分别表示控制台输入和输出，其
含义与 Pascal 语言中的相同。