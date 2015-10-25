##Mini Code Marathon2015

##官网

![website](http://scbcode.hirede.com/Templates/Scb/Images/banner.jpg)

[花儿与编程项目](http://scbcode.hirede.com/CareerSite/ScbIndex)

##题目

Google-style search box. Key requirements:
•	Google-style intelligent search, after typing 3 letters, prompt and narrow down matched list (note – UI friendly so don’t overflow the screen) whilst allowing user to continue type and refine search condition.
•	You can use any demo or sample DB as the repository of match criteria… eg book names, or anything that you’ve gathered. But must have at least 1mio records to be used to demo this search functionality.
•	You can use a database of your choice. MS SQL or Oracle platform can be arranged in advance with SCB competition organiser. If you require SCB to provide development DB, pls bring DML and DDL to prepare the search data for your demo.
•	No other UI element is required. Key is the actual search box and responsiveness of the intelli-search.


##环境配置

1. JDK

    JDK1.7+

2. Maven

    maven: 3.2.0+

3. Thrift

    0.9.2

##开源工具

###Thrift

http://thrift.apache.org/

###Maven

http://maven.apache.org/

###Tomcat

http://tomcat.apache.org/

##部署

1.编译代码

 1. 在项目根目录下执行 `./thrift_gen.sh`，用于生成thrift代码和基础类文件
 2. 编译clint项目。进入hackathon-client目录，执行 `mvn clean install`。
 3. 编译server项目。进入hackathon-server目录，执行 `mvn clean assembly:assembly`，在target下生成hackathon-server-0.1-SNAPSHOT-jar-with-dependencies.jar文件
 4. 编译web项目。进入hackathon-web目录，执行`mvn clean package`，在target目录下生成hackathon-web-0.1.war文件。

2.运行项目

 1. 运行server，在项目根目录下执行 `java -jar hackathon-server/target/hackathon-server-0.1-SNAPSHOT-jar-with-dependencies.jar -f <data file absloate path>`
    以开发环境为例：数据文件为sentences文件，执行命令：
    `java -jar hackathon-server/target/hackathon-server-0.1-SNAPSHOT-jar-with-dependencies.jar -f /Users/omar/workspace/zada-hackathon/sentences`
 2. 部署web项目。将hackathon-web-0.1.war拷贝到tomcat文件夹中webapps目录中，并重命名为ROOT.war。
 3. 启动web项目。在tomcat文件夹下bin目录中执行`startup.sh`

##文档

见 doc/

##数据

sentences:100w条中英文混合文本，按行分割。

##License

Licensed under the Apache license.


