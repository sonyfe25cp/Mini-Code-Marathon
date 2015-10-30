#!/bin/bash

for (( i=1; i<3; i++)); do
  #echo 808$i
  docker run -p 808$i:8080 -v /Users/omar/workspace/Mini-Code-Marathon/hackathon-web/target/hackathon-web-0.1:/usr/local/tomcat/webapps/ROOT -d docker.cn/docker/tomcat:8-jre8
done


