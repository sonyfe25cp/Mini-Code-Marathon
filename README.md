##Mini Code Marathon2015

##Portal

![website](http://scbcode.hirede.com/Templates/Scb/Images/banner.jpg)

[花儿与编程项目](http://scbcode.hirede.com/CareerSite/ScbIndex)

##The subject in this  mini code marathon

Google-style search box. Key requirements:
•	Google-style intelligent search, after typing 3 letters, prompt and narrow down matched list (note – UI friendly so don’t overflow the screen) whilst allowing user to continue type and refine search condition.
•	You can use any demo or sample DB as the repository of match criteria… eg book names, or anything that you’ve gathered. But must have at least 1mio records to be used to demo this search functionality.
•	You can use a database of your choice. MS SQL or Oracle platform can be arranged in advance with SCB competition organiser. If you require SCB to provide development DB, pls bring DML and DDL to prepare the search data for your demo.
•	No other UI element is required. Key is the actual search box and responsiveness of the intelli-search.


##Runntime Environment

1. JDK

    JDK1.7+

2. Maven

    maven: 3.2.0+

3. Thrift

    0.9.2

##Tools used in this project

###Thrift

http://thrift.apache.org/

###Maven

http://maven.apache.org/

###Tomcat

http://tomcat.apache.org/

##Deployment

1.Compile

 1. run `./thrift_gen.sh` to get the source code about thrift
 2. run `cd hackathon-client` and exec `mvn clean install` to get the jar file about client.
 3. run `cd hackathon-server` and exec `mvn clean assembly:assembly` to get the exec jar file about server.
 4. run `cd hackathon-web` and exec `mvn clean package` to get the war file about web project.

2.Run without Docker

 1. run server with `java -jar hackathon-server/target/hackathon-server-0.1-SNAPSHOT-jar-with-dependencies.jar -f <data file absloate path>`
 2. run web project with tomcat, just cp that war file to the `webapps` folder of Tomcat.

3.Run with Docker

 1. exec `./runWeb.sh`, then you can get a web server on the port 80.
 2. if you want to run more web servers, exec `./run2Web.sh <number>`, then you can get more.


##Document

doc/google_style_search.pdf (Chinese)

##Data

sentences: 0.5mio data about reviews from amazon and 0.5mio Chinese phrases from news.

##Ranking

Our team won the second prize.

:( There are enough space to improve our algorithm about prefix searching.

##License

Licensed under the Apache license.


##Group Members

* Shuai Wang
* Lin Chen
* Jie Chen
