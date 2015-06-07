# ROSE
编译原理实验——Oberon-0逆向工程工具ROSE

1简介
1.1实验目的
本实验是编译原理实验环节的一个综合型、应用型实验，其中还包含部分研究与探
索型实验内容。
本实验处理的程序设计语言是 Oberon-0，它是著名的 Pascal 和 Modula-2 语言的后
继者 Oberon 语言的一个精简子集。学生在本实验中需开发一个面向 Oberon-0 的逆
向工程工具，根据一个输入的 Oberon-0 源程序自动绘制对应的设计图，这些设计图
包括子程序调用关系图和程序控制流设计图（例如程序流程图、PAD 图、N_S 图等），本实验项目将该
工具命名为RRROOOSSSEEE，意为 Reverse Oberon Software Engineering。
本实验借助于RRROOOSSSEEE 工具的设计与实现，帮助学生通过实践深入理解和牢固掌握编译技术中的词法分析、
语法分析、语法制导翻译、自动生成工具等重要环节。
本实验的主要目标包括：
 掌握词法分析程序的工作原理与构造方法，并能够推广到对文本的串匹配搜索等其他同类型应用。
 掌握词法分析程序自动生成工具 lex 或类似工具的工作原理与使用方法，学习如何编写一个 lex 源
文件以解决词法分析或模式匹配问题，初步体会软件自动化的基本思路。
 掌握递归下降的预测分析方法以及语法制导的翻译技术，学习如何根据 BNF 语法定义和应用需求
设计一个翻译模式，并利用高级程序设计语言的递归机制实现一个翻译模式。
 掌握语法分析程序自动生成工具 yacc 或类似工具的工作原理与使用方法，学习如何编写一个 yacc
源文件以解决语法分析及语法制导翻译问题，进一步加深体会软件自动化的基本思路。
 通过加强设计空间（Design Sapce）的深入探讨以及 Java 编程风格的实践，提高对面向对象设计的
认识，养成良好的编程习惯与规范，并学会多个工程文档的组织与提交。
1.2
软件工具ROSE 基于当前主流的面向对象开发平台，编码风格遵循主流的参考规范。
1.2.1 编程语言
Java 语言，JDK 8 or above。
JDK 8 download link:
http://www.oracle.com/technetwork/java/javase/downloads/index.htm
Docs and tutorials:
http://docs.oracle.com/javase/specs/jls/se8/html/index.html
http://docs.oracle.com/javase/8/docs/index.html
http://docs.oracle.com/javase/tutorial/ 
学生可自由选择 Eclipse（推荐使用）、Netbeans、JBuilder 等 IDE 环境，也可直接采用 UltraEdit、EditPlus
等编辑器在命令行工作。但提交的实验结果必须独立于特定的 IDE，可直接运行在 JDK 上。
Eclipse 下载链接：http://www.eclipse.org/downloads/
Netbeans 下载链接：https://netbeans.org/
1.2.3 实验软装置
为让学生在实验过程中可集中精力完成对表达式语言处理的设计与实现，并便于学生和老师对实验结果
进行测试，本实验提供了辅助的实验软装置。
所谓实验软装置，是在软件设计类课程实验中相当于电子技术、计算机硬件等专业实验中的示波器、实
验箱之类的实验装置，只不过在软件设计类实验中这些装置全部以软件的形态出现。本质上，实验软装
置是一个应用框架（Application Framework），它提供了整个实验中非核心部分的代码，学生自己完成
的实验核心部分代码与这些代码结合在一起即构成了一个完整的实验结果。
此外，在实验软装置这个应用框架中通常还嵌入了负责软件测试的代码，这既可用于学生在实验过程自
己对实验结果进行测试，也便于教师对学生提交的实验结果进行评价。
1.2.4 编码规范
你需要采用面向对象风格来设计实验中的所有类，并遵循一种良好的程序设计习惯。例如，如果你将一
个程序的很多功能全部放在某个长长的 main()方法中实现，这样的设计与编码风格会被扣分。
学生在实验过程中应注意培养规范的编码风格。本实验要求所有 源代码遵循关于 Java 程序
设计语言的编码规范，参见：
 http://www.oracle.com/technetwork/java/codeconventions-150003.pdf
完成实验后，应使用 JDK 附带的文档工具 javadoc，根据源程序中的文档化注释自动生成相应的 HTML
格式的设计文档。
1.3 实验计划
ROSE 实验项目是编译原理课程的一个综合型、应用型实验，为高质量地完成这一课程实践环节，你需
要在开展实验之前精心规划自己的时间与精力投入，因而推荐你参考以下建议及时开展实验活动。
根据任课教师对往届学生完成编译原理类似实验项目的经验估计，你在语言预备实验（实验一）的投入
时间约为 1~2 天；在词法分析阶段的实验（实验二）约为 7 天；在语法分析阶段及语法制导翻译阶段的
实验（实验三和实验四）约为 10~15 天。
2 实验内容
对于一个像RRROOOSSSEEE 这样大规模、复杂实验项目而言，精心分解实验的过程对实验的执行效果会产生重要
影响。本实验分解为 4 个子实验项目，每一子实验项目都规定了明确的实验步骤、实验要求以及需要提
交的实验结果。
2.1 实验一、熟悉 Oberon‐0 语言定义
本文档第 3 部分给出了 Oberon-0 语言的完整 BNF 定义，即 Oberon-0 语言在语法方面的形式化规格说
明（Specification）；此外，第 3 部分还以自然语言的方式提供了 Oberon-0 语言的语义描述。
请仔细阅读 Oberon-0 语言的定义，并完成以下实验内容。
2.1.1 实验步骤 1.1、编写一个正确的 Oberon-0 源程序
遵循 Oberon-0 语言的 BNF 定义，编写一个正确的 Oberon-0 源程序。要求在这个源程序中，用到 Oberon-0
语言的所有语法构造，即你编写的源程序覆盖了 Oberon-0 语言提供的模块、声明（类型、常量、变量
等）、过程声明与调用、语句、表达式等各种构造。
如果有可能，你编写的 Oberon-0 源程序最好是有其实际意义的，譬如一个求阶乘的程序、一个求最大
公因子的程序、一个用加法实现乘法的程序等等，但这只是一个任选的要求。
注意，这里仅要求你编写一个词法、语法和语义符合 Oberon-0 语言定义的源程序，并未强制要求该源
程序在逻辑上是正确的。
2.1.2 实验步骤 1.2、编写上述 Oberon-0 源程序的变异程序
根据上述正确的源程序，写出若干含有词法、语法或语义错误的 Oberon-0 源程序。其做法是对原有的
正确源程序做出最小的改动，使之成为一个包含至少一个错误的源程序；为每一种可能存在的词法、语
法和语义错误构造出一个变异程序。例如，有一个变异程序中含有不合法的标识符，有另一个变异程序
中含有不合法的常量，还有一个变异程序中丢失了左括号等。在每一变异程序的第一行用注释说明该变
异产生的错误类型。
基于极限编程 XP（eXtreme Programming）中的测试驱动编程（Test-Driven Programming）思想，在进
入正式编码前先设计好测试用例将有助于把握正确的设计。而上述正确的 Oberon-0 源程序及其一批含
有错误的变异程序将在后述实验步骤中作为测试用例。
上述“程序变异”的思路源于软件测试技术中的变异测试（Mutation Testing）。变异测试的目标是筛选
更有效的测试数据，其思路是通过变异（Mutation）操作在程序中植入一个错误，变异后的程序称为变
异程序（Mutant）；通过检查测试数据能否发现变异程序中的这些错误，可判断测试数据的有效性。有
编译原理实验：Oberon-0 逆向工程工具RRROOOSSS EEE
- 5 -
兴趣的同学不妨查阅相关资料（例如：http://en.wikipedia.org/wiki/Mutation_testing）进一步学习变异测
试技术。
2.1.3 实验步骤 1.3、讨论 Oberon-0 语言的特点
在 Oberon-0 语言中，与其前辈 Pascal 语言一样区别了保留字（Reserved Word）与关键字（Keyword）
两个概念。例如，IF、THEN、ELSIF 等是保留字，而 INTEGER、WRITE、WRITELN 则是关键字。试
解释这两个概念的区别。
根据 Oberon-0 语言的 BNF 定义，Oberon-0 程序中的表达式语法规则与 Java、C/C++等常见语言的表达
式有何不同之处？试简要写出它们的差别。
2.1.4 实验步骤 1.4、讨论 Oberon-0 文法定义的二义性
根据 Oberon-0 语言的 BNF 定义，讨论 Oberon-0 程序的二义性问题，即讨论根据上述 BNF 定义的上下
文无关文法是否存在二义性。
如果你认为该文法存在二义性，则举例说明在什么地方会出现二义性，并探讨如何改造文法以消除二义
性。
如果你认为该文法没有二义性，则请解释：为何在其他高级程序设计语言中常见的那些二义性问题在
Oberon-0 语言中并未出现？
2.1.5 提交结果
将实验一的所有结果存放在子目录 xxxxxxxxNNN\ex1 中，其中 xxxxxxxx 是你的学号，NNN 是你的
中文姓名。例如，学号为“05372001”、姓名为“马婷”的同学，应将其完成的实验一全部结果存放在
“05372001 马婷\ex1”子目录中；注意学号与姓名之间不要加空格、姓与名之间亦不要加空格。
实验一最终提交的实验结果应包括：
 在 ex1 子目录中存放自述文件 readme.txt，其中给出你的姓名、学号、电子邮件、联系电
话、完成日期、以及其他补充说明。
 在 ex1 子目录中存放你的实验报告 Oberon-0.pdf（允许.doc、.txt 等格式，但不要使
用.docx、.wps 等格式；下同），其中包括 Oberon-0 语言特点介绍、文法二义性讨论、实验
心得体会等内容。
 在 ex1\testcases 子目录中存放一个词法、语法、语义均正确的 Oberon-0 程序源代码，该
文本文件的文件名由你自己根据程序的功能自由命名，但必须以.obr 为文件扩展名。
 在 ex1\testcases 子目录中存放一批含有词法、语法或语义错误的变异程序，文件名均同
语法正确的 Oberon-0 源程序，但分别以.xxx 为文件扩展名（xxx 表示序号，从 001 开始）。
2.2 实验二、自动生成词法分析程序（JFlex）
实验二要求你下载一个词法分析程序自动生成工具 JFlex，并利用该工具自动产生 Oberon-0 语言的词法
分析程序，该词法分析程序的源代码是用 Java 语言编写的。
2.2.1 实验步骤 2.1、总结 Oberon-0 语言的词汇表
根据 Oberon-0 语言的 BNF 定义和语言描述，抽取 Oberon-0 语言的词汇表以供词法分析程序设计与实
现之用。你需要将 Oberon-0 的所有单词分类，并以表格形式列出各类词汇的预定义单词；譬如，在保
留字表中列出所有的保留字，在运算符表中列出所有的运算符，等等。
请在实验报告中说明你的单词分类的理由，并解释如何处理 Oberon-0 语言中保留字和关键字两类略有
不同的单词。
2.2.2 实验步骤 2.2、抽取 Oberon-0 语言的词法规则
在 Oberon-0 语言的 BNF 定义中，既包括 Oberon-0 语言的语法定义部分，也包括 Oberon-0 语言的词法
定义部分。请将词法定义从 Oberon-0 语言的 BNF 中分离出来，并写成正则定义式的形式。
然后，在实验报告中讨论与 Pascal 、C/C++、Java 等常见高级程序设计语言的词法规则相比，Oberon-0
语言的词法规则有何异同。
2.2.3 实验步骤 2.3、下载词法分析程序自动生成工具 JFlex
实验二指定的词法分析程序自动生成工具选用由 Gerwin Klein 开发的 JFlex。这是一个类似 Unix 平台上
lex 程序的开源（Open Source）软件工具，遵循 GNU General Public License（GPL）。JFlex 本身采用 Java
语言编写，并且生成 Java 语言的词法分析程序源代码。该软件工具的前身是由美国普林斯顿大学计算
机科学系 Elliot Berk 开发、C. Scott Ananian 负责维护的 JLex。
从 http://www.jflex.de/release/jflex-1.6.1.zip 可下载该工具的最新稳定版本 1.6.1；这一压缩文件中已包含
了你在本实验中所需的各类资源，包括该工具的 Java 源代码、支持运行的库文件与脚本文件、用户文
档、输入源文件例子等。
根据你自己的安装配置，修改 JFlex 安装目录下脚本文件 bin\jflex.bat 中的两个环境变量
JFLEX_HOME 和 JAVA_HOME 的设置。然后运行 JFlex 附带的输入源文件例子，以验证你是否正确安装
并配置了 JFlex。
如果你觉得 JFlex 附带的用户手册仍不足以帮助你掌握 JFlex 的原理或用法，自己动手在网上查找其他
关于 JLex、GNU Flex、lex 等类似工具的大量电子资源。
2.2.4 实验步骤 2.4、生成 Oberon-0 语言的词法分析程序
仔细阅读 JFlex 的使用手册，根据你在实验步骤 2.1 给出的 Oberon-0 语言词法规则的正则定义式，编写
一个 JFlex 输入源文件。
以你编写的源文件作为输入运行 JFlex，得到一个 Oberon-0 语言词法分析程序的 Java 源代码；编译该源
编译原理实验：Oberon-0 逆向工程工具RRROOOSSS EEE
- 7 -
程序生成 Java 字节码。自己编写包含 main()函数的主类作为程序入口调用词法分析的代码，利用你在
实验一编写的所有 Oberon-0 源程序测试你的词法分析程序。
2.2.5 实验步骤 2.5、讨论不同词法分析程序生成工具的差异
比较以下 3 种流行的词法分析程序自动生成工具之间的差异：JFlex、JLex 和 GNU Flex。主要讨论这些
软件工具接收输入源文件时，在词法规则定义方面存在的差异。
在网站 http://www.cs.princeton.edu/~appel/modern/java/JLex/可找到关于 JLex 工具的权威资料；关于 GNU
Flex 的官方资料则位于 http://www.gnu.org/software/flex/。
2.2.6 提交结果
将实验二的所有结果存放在子目录 xxxxxxxxNNN\ex2 中，其中 xxxxxxxx 是你的学号，NNN 是你的
中文姓名，命名规则同实验一。
实验二最终提交的实验结果应包括：
 在 ex2 子目录中存放自述文件 readme.txt，其中给出你的姓名、学号、电子邮件、联系电
话、完成日期、以及其他补充说明。
 在 ex2 子目录中存放你在实验二撰写的实验报告 lexgen.pdf，其中的内容包括：
 以表格形式列出 Oberon-0 语言的词汇表。
 用正则定义式描述 Oberon-0 语言词法规则；若你使用纯文本书写正则定义式，其中的元
符号“定义为”使用“->”表示，空串使用“epsilon”表示。
 紧随 Oberon-0 语言词法规则之后，给出一段关于 Oberon-0 语言与其他高级语言的词法规
则之异同比较。
 讨论 3 种不同 lex 族软件工具的输入文件中，词法规则定义的差异或特点。
 自动生成的 Oberon-0 语言词法分析程序及其测试用例，包括：
 在 ex2 子目录中存放各种脚本文件，包括：运行 JFlex 根据输入文件生成词法分析程序的
脚本 gen.bat；编译词法分析程序的脚本 build.bat；运行词法分析程序扫描你编写
的正确 Oberon-0 例子程序的脚本 run.bat；运行所有由实验软装置提供的测试用例的脚
本 test.bat。
 在 ex2\src 子目录中存放面向 Oberon-0 语言的 JFlex 输入源文件 oberon.flex 以及你
所需的其他相应文件。
 在 ex2\src 子目录中存放由 JFlex 生成的 Oberon-0 语言词法分析程序的 Java 源程序
OberonScanner.java（注意我们不使用默认的生成名字 Yylex）。
 在 ex2\bin 子目录中存放根据 Oberon-0 词法分析程序源代码编译得到的字节码文件
OberonScanner.class 以及其他相关的字节码文件。
 在 ex2\doc 子目录中存放根据你的源程序中文档化注释自动生成的 javadoc 文档。
 在 ex2\jflex 子目录中存放你所使用的 JFlex 工具。
编译原理实验：Oberon-0 逆向工程工具RRROOOSSS EEE
- 8 -
注意，请不要提交那些由 IDE 自动生成的配置文件或备份文件（下同）。
2.3 实验三、自动生成语法分析程序（JavaCUP）
实验三要求你下载一个语法分析程序自动生成工具 JavaCUP，利用该工具自动产生一个 Oberon-0 语言
的语法分析和语法制导翻译程序；生成的程序源代码是以 Java 语言编写的。
2.3.1 实验步骤 3.1、下载自动生成工具 JavaCUP
实验三选用由美国卡内基·梅隆大学的 Scott E. Hudson 开发的一个语法分析程序自动生成工具
JavaCUP，它是一个 LALR Parser Generator。JavaCUP 是一个类似 Unix 平台上 yacc 程序的开源（Open
Source）软件工具，遵循 GNU General Public License（GPL）。JavaCUP 本身采用 Java 编写，并且生成
Java 语言的分析程序源代码。该软件工具经由美国普林斯顿大学计算机科学系 Andrew W. Appel 教授指
导 Frank Flannery 等人改进，目前由 C. Scott Ananian 负责维护。
从 http://www2.cs.tum.edu/projects/cup/, 可下载该软件工具的最新版本 CUP 11b 20150326, 该网站已包
含了你在实验中所需的各类资源，包括该工具的 Java 源代码、已编译生成的字节码、简明的用户手册、
以及一个简单的命令行计算器例子等。
2.3.2 实验步骤 3.2、配置和试用 JavaCUP
成功下载并配置后，试运行 JavaCUP 附带的输入源文件例子（一个基于命令行的简单计算器应用），以
保证你正确安装并配置了 JavaCUP。
如果你觉得 JavaCUP 附带的用户手册仍不足以帮助你掌握 JavaCUP 的原理与用法，自己动手在网上查
找其他关于 GNU Bison、yacc 等类似工具的大量电子资源。
2.3.3 实验步骤 3.3、生成 Oberon-0 语法分析和语法制导翻译程序
仔细阅读 JavaCUP 使用手册，根据 Oberon-0 语言的 BNF 定义编写一个 JavaCUP 输入源文件。
根据你的 JavaCUP 输入源文件生成的语法分析程序须完成以下功能：
1、对于一个存在词法、语法或语义错误的 Oberon-0 源程序，必须至少指出一处错误，并判断错误的类
别及产生错误的位置（错误产生的位置定位允许有偏差），并以相应的异常对象向客户程序报告找出的
错误的类别。是否支持其他功能取决于你的时间、精力与能力，譬如你可尝试从错误中恢复并继续执行
语法分析，也可生成设计图时立即中止程序的执行。在错误检查与错误恢复（指找到错误后继续执行分
析过程的能力）方面做得优秀的实验可获得更高的评分。
编译原理实验：Oberon-0 逆向工程工具RRROOOSSS EEE
- 9 -
2、对于一个词法、语法和语义完全正确的 Oberon-0 源程序，自动绘制出不同模块中多个过程之间的调
用图（Call Graph）。本文档第 4 部分详细介绍了RRROOOSSSEEE 实现的调用图的定义。
你在生成 Oberon-0 语言的语法分析程序时，可以直接使用实验二由 JFlex 生成的 Oberon-0 语言的词法
分析程序。
以你编写的源文件为输入运行 JavaCUP，得到 Oberon-0 语言语法分析程序的若干 Java 源代码；编译这
些 Java 源程序生成相应的字节码，再分别利用自己在实验一编写的 Oberon-0 源程序及其变异程序测试
生成的结果，看看你生成的语法分析和语法制导翻译程序能否正确地识别出 Oberon-0 源程序中的各类
错误，并且能否针对正确的 Oberon-0 源程序输出期望的调用图。
2.3.4 实验步骤 3.4、讨论不同生成工具的差异
比较两种流行的语法分析程序自动生成工具之间的差异：JavaCUP 和 GNU Bison，主要讨论这两种软件
工具接收输入源文件时，在语法规则定义方面存在的差异。关于 GNU Bison 工具的官方资料可在网站
http://www.gnu.org/software/bison/bison.html 找到。
2.3.5 提交结果
将实验三的所有结果存放在子目录 xxxxxxxxNNN\ex3 中，其中 xxxxxxxx 是你的学号，NNN 是你的
中文姓名，命名规则同实验一。
实验三最终提交的实验结果应包括：
 在 ex3 子目录中存放自述文件 readme.txt，其中给出你的姓名、学号、电子邮件、联系电
话、完成日期、以及其他补充说明。
 在 ex3 子目录中存放讨论两个不同 yacc 族工具语法规则定义差异的文档 yaccgen.pdf。
 自动生成的 Oberon-0 语言语法分析程序及其测试用例，包括：
 在 ex3 子目录中存放各种脚本文件，包括：运行 JavaCUP 根据输入文件生成语法分析程
序的脚本 gen.bat；编译语法分析程序的脚本 build.bat；运行语法分析程序处理你
编写的正确 Oberon-0 例子程序的脚本 run.bat；运行所有由实验软装置提供的测试用例
的脚本 test.bat。
 在 ex3\src 子目录中存放面向 Oberon-0 语言的 JavaCUP 输入文件 oberon.cup 以及你
所需的其他相应文件。
 在 ex3\src 子目录中存放由 JavaCUP 生成的 Oberon-0 语法分析程序源代码
Parser.java、Symbol.java 和其他相应的 Java 源代码（注意我们不使用默认的生成
名字 parser 和 sym 等）。
 在 ex3\bin 子目录中存放根据 Oberon-0 语法分析程序源代码编译得到的字节码文件
Parser.class 以及其他相关的字节码文件。
 在 ex3\doc 子目录中存放根据你的源程序中文档化注释自动生成的 javadoc 文档。
 在 ex3\javacup 子目录中存放你所使用的 JavaCUP 工具。
2.4 实验四、手工编写递归下降预测分析程序
实验四要求你利用 Java 语言手工编写一个 Oberon-0 语言的语法分析程序，该语法分析程序执行与实验
三自动生成的语法分析程序类似的功能，但实验三要求逆向工程工具生成的是调用图，而实验四要求生
成的是流程图（Flowchart）。
该语法分析程序采用递归下降预测分析技术，要求你遵循语法制导翻译思想，先设计 Oberon-0 语言的
翻译模式，再据此编写语法分析程序。
2.4.1 实验步骤 4.1、设计 Oberon-0 语言的翻译模式
根据 Oberon-0 语言 BNF 定义中的语法规则，以及你完成 Oberon-0 源程序排版的应用需求，为 Oberon-0
语言设计一个合适的翻译模式。
注意，由于实验四规定使用递归下降的预测分析技术，你需要改造文法以适用于这种自顶向下的分析方
法。例如，上下文无关文法中的左递归必须消除，以避免递归下降的预测分析程序进入死循环。
2.4.2 实验步骤 4.2、编写递归下降预测分析程序
根据上一步骤获得的翻译模式，利用 Java 语言设计并实现一个 Oberon-0 语言的递归下降预测分析程序。
参考编译原理理论课所学知识，从一个翻译模式设计一个递归下降预测分析程序已有比较成熟的启发式
规则，你应遵循这些规则设计你的语法分析程序。
例如：文法的每一非终结符号应对应着一个递归子程序，开始符号则对应着其中的主程序；由向前看符
号（Lookahead）决定分支动作；每一个继承属性对应一个形式参数，所有综合属性对应返回值，子结
点的每一属性对应一个局部变量；翻译模式中产生式右部的结终符号、非终结符号与语义动作分别执行
匹配、递归调用和嵌入代码等动作。
2.4.3 实验步骤 4.3、语法分析讨论：自顶向下 vs. 自底向上
通过你自己在实验三和实验四的实际体会，对递归下降预测分析技术和自底向上的 LR 分析技术这两种
不同的分析策略进行比较。
建议你在比较两种技术的各自优点和不足时，考虑（但不必局限于）以下方面：
 分析技术的简单性，包括分析程序是否易于调试。
 分析技术的通用性，即能处理的语言范围。
 是否便于表达语义动作以完成语法制导翻译。
编译原理实验：Oberon-0 逆向工程工具RRROOOSSS EEE
- 11 -
 是否易于实现出错恢复。
 若以表格驱动方式取代递归程序实现，则分析表大小的优劣如何？
 分析速度。
2.4.4 提交结果
将实验四的所有结果存放在子目录 xxxxxxxxNNN\ex4 中，其中 xxxxxxxx 是你的学号，NNN 是你的
中文姓名，命名规则同实验一。
实验四最终提交的实验结果应包括：
 在 ex4 子目录中存放自述文件 readme.txt，其中给出你的姓名、学号、电子邮件、联系电
话、完成日期、以及其他补充说明。
 在 ex4 子目录中存放描述 Oberon-0 语言翻译模式的文本文件 scheme.pdf。
 你手工编写的 Oberon-0 语言语法分析和语法制导翻译程序及其测试用例，包括：
 在 ex4 子目录中存放各种脚本文件，包括：编译语法分析程序的脚本 build.bat；运行
语法分析程序扫描你编写的正确 Oberon-0 例子程序的脚本 run.bat；运行所有由实验软
装置提供的测试用例的脚本 test.bat。
 在 ex4\src 子目录中存放 Oberon-0 语法分析程序的主程序 OberonParser.java，以
及你在考虑面向对象设计时引入的其他 Java 类的源代码；你用程序包组织的各相关类亦
存放在 ex4\src 之中的相应子目录中。
 在 ex4\bin 子目录中存放根据词法分析程序源代码编译得到的 Java 字节码文件
OberonParser.class 以及其它相关的字节码文件。
 在 ex4\doc 子目录中存放根据你的源程序中文档化注释自动生成的 javadoc 文档。
3 Oberon‐0 语言
本实验的处理对象是 Oberon-0 语言，该语言中包含了高级程序设计语言的表达式，以及结构化程序设
计中的结构化控制结构、子程序、参数传递等机制的抽象。
3.1 简介
用于编译原理实验的计算机语言应足够简单，但又不失其代表性。据此，本实验项目选择了 Oberon-0
语言为处理对象。
Oberon-0 的来历可追溯到近 50 年前，在此期间的程序设计语言发展约每 10 年就有一标志性成果。1960
年，P. Naur 等人设计了 Algol 60 语言；约 10 年后，随着结构化程序设计思想的成熟，N. Wirth 设计出
远比 Algol 68 语言成功（特别是在教育界）的 Pascal 语言；又一个 10 年过去，N. Wirth 根据程序设计
和软件工程技术的最新进展，在 Pascal 基础上设计了 Modula-2 语言；又过了约 10 年后，M. Reiser 与
N. Wirth 一起，将 Pascal 语言和 Modula-2 语言的程序设计本质精华浓缩为 Oberon 语言。
Oberon-0 语言是 Oberon 语言的一个子集，为程序员提供了良好的程序结构。在 Oberon-0 程序中，最基
本的语句是赋值语句；复合语句支持顺序、条件（if 语句）和迭代（while 语句）执行。Oberon-0 中
还支持子程序这一重要概念，包括过程声明和过程调用两个范畴，并且提供了两种不同的参数传递方式：
按值调用（值参数）和按引用调用（可变参数）。
然而 Oberon-0 的类型系统却十分简洁，仅有的基本数据类型是整数类型（INTEGER）和布尔类型
（BOOLEAN），因而可声明整数类型的常量和变量，也允许用算术运算符构造表达式；而表达式的比较
运算则产生 BOOLEAN 类型的值，并可用于逻辑运算。Oberon-0 的复合数据类型包括数组和记录，且允
许任意嵌套；但最基本的指针类型或引用类型就被省略了。
一个过程代表了由语句组成的功能单元，因而在一个过程的写法中自然会关系到名字的局部性
（Locality）问题。Oberon-0 语言支持将标识符声明为局部于某一过程，即仅在该过程本身范围内标识
符才是可见的或合法的。
由 N. Wirth 本人编著的 Theory and Techniques of Compiler Construction: An Introduction（Addison-Wesley,
1996, ISBN 0-201-40353-6，本课程教学网站提供了该教材的电子版）中，第 6 章描述了 Oberon-0 程序
设计语言的语法并给出一个样板程序。本文档的语法定义和例子程序即源于该教材，但作了少数改动，
因而本实验处理的 Oberon-0 语言应以本文档的定义为准。
3.2 词法定义
Oberon-0 语言定义了非常简单的语法规则。
编译原理实验：Oberon-0 逆向工程工具RRROOOSSS EEE
- 13 -
3.2.1 单词
与我们熟悉的 C、C++、Java 等语言不同，Oberon-0 语言是大小写无关的。譬如，保留字 WHILE、While
和 while 三种写法是等价的；而标识符 BALANCE、Balance 和 balance 是相同的标识符。
Oberon-0 的标识符长度不允许超过 24 个字符（允许 24 个字符）。
在 Oberon-0 中还支持括号风格的注释，在“(*”和“*)”之间的内容全部为注释；注意，Oberon-0 注
释不允许嵌套。
3.2.2 基本数据类型
在 Oberon-0 程序中仅支持 INTEGER 和 BOOLEAN 两种基本数据类型，可以利用 VAR 声明这两种类型的
变量。
INTEGER 类型的常量书写形式只允许 Pascal 语言的无符号整数；这些常量可以由 0 开头，但解释为八
进制常量（此时常量中不允许出现 8 和 9 两个数字），非 0 开头则解释为十进制常量。无论十进制还是
八进制整数常量，每一常量中包含数字（包括 0）的个数不可超过 12 个（从而限制了整数常量允许表
达范围的最大值）。
INTEGER 类型常量与标识符之前必须以空白符号分隔；例如，扫描 25id 时应作为一个非法整数常量
处理，而不是理解为常量 25 和标识符 id 两个单词。注意，不支持书写 BOOLEAN 类型的常量 TRUE
和 FALSE。
3.3 语法定义
本小节以 EBNF 定义了 Oberon-0 语言的形式语法。
3.3.1 扩展 BNF（EBNF）
EBNF 意即扩展的 BNF（Extended BNF），是我们在实际应用中定义一门计算机语言的形式语法的国际
标准，参见 ISO/IEC 14977: 1996(E). The Standard Metalanguage Extended BNF（本课程教学网站提供了
该标准的电子版文档）。
3.3.2 Oberon-0 语言的 EBNF 定义
module = "MODULE" identifier ";"
         declarations
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
statement = [assignment |
             procedure_call |
             if_statement |
             while_statement] ;
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
factor = identifier selector |
         number |
         "(" expression ")" | 
         "~" factor ;
number = integer ;
selector = {"." identifier | "[" expression "]"} ;
integer = digit {digit} ;
identifier = letter {letter | digit} ; 
3.4 语言描述
在 Theory and Techniques of Compiler Construction: An Introduction 中并未详细描述 Oberon-0 语言的语义
特性，因为该语言的语义可以按直观的方式参照其他程序设计语言（特别是沿 Algol 60、Pascal、
Modula-2、Oberon 这一家族的语言）来理解。
本小节特别强调了 Oberon-0 可能引起理解上二义性的几个方面，使得在本实验项目中老师和所有学生
对该语言的理解是无二义的。
3.4.1 模块声明
一个模块中可声明类型、常量、变量、过程等，也可声明该模块的主程序（程序体包括在 BEGIN 和 END
之间）。但一个模块也允许没有主程序，仅声明一些类型、常量、变量、过程等供其他模块使用（尽管
本实验项目中尚未更深入地考虑这一问题）。
一个模块声明的 END 之后的标识符必须与该模块的名字相同。
3.4.2 运算符与表达式
算术表达式支持一元运算“+”和“–”，分别表示取正和取负运算；支持二元运算“+”、“–”、“*”、“DIV”
和“MOD”，分别表示加法、减法、乘法、整除、取模运算。参与算术表达式的运算量必须是 INTEGER
类型，否则会产生类型不兼容错误；由于 Oberon-0 语言不支持实数除法运算（仅支持整除运算），因而
算术表达式的运算结果总是 INTEGER 类型。
算术表达式可参与“=”、“#”（不等于）、“<”、“<=”、“>”和“>=”等关系运算，运算结果为 BOOLEAN
类型的值。BOOLEAN 类型的值不允许参与任何关系运算，否则会产生类型不兼容错误。
但是 BOOLEAN 类型的值可能参与逻辑运算“&”、“OR”和“~”，分别表示“与”、“或”和“非”。INTEGER
类型的值不允许参加任何逻辑运算，否则会产生类型不兼容错误。
4 逆向工程工具
本实验项目要求开发的软件工具RRROOOSSSEEE 可根据一个 Oberon-0 语言源程序自动绘制出一个模块中所有过程
的调用图（实验三），并为每一过程自动绘制出表达控制流设计的流程图（实验四）。
4.1 调用图
在一个编译器执行过程间分析（Interprocedural Analysis）以实现全局优化时，调用图（Call Graph）是
开展分析的一个重要基础。
4.1.1 调用图定义
在 A. Aho 等人合著的 Compilers: Principles, Techniques, and Tools, 2nd Ed（Addison-Wesley, 2006, ISBN
0-321-48681-1）第 12 章 12.1 小节中，调用图被定义为一个由结点和有向边组成的集合，其中：
 程序中的每一过程用一个结点表示；在RRROOOSSSEEE 中采用黄色填充的矩形表示这类结点，矩形中标
注该过程的名字。
 程序中每一个调用过程的地方称为调用点（Call Site），这些调用点也用一个结点表示；在RRROOOSSSEEE
中用青色填充的矩形表示这类结点，其中标注调用点的 Id、所处的过程名字、以及发出过程
调用的那条语句。
 在某一调用点调用某一过程这种调用关系在图中用一条有向边表示。
为一个面向对象程序构造调用图并不容易，因为动态绑定（亦称动态分派，Dynamic Dispatching）使得
调用关系更加复杂；像 C 程序中允许将函数指针作为参数传递，也给生成调用图造成麻烦。幸运的是
我们在RRROOOSSSEEE 中不必考虑这一问题。
