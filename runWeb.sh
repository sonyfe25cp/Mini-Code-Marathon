#!/bin/bash

docker run -p 80:8080 -v /Users/omar/workspace/Mini-Code-Marathon/hackathon-web/target/hackathon-web-0.1:/usr/local/tomcat/webapps/ROOT -d docker.cn/docker/tomcat:8-jre8
