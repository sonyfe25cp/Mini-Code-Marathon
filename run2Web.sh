#!/bin/bash

#0. 命令检测
#check the docker command
command -v docker>/dev/null 2>&1 || { echo >&2 "Docker is not installed in this machine"; exit 1; }

command -v mvn>/dev/null 2>&1 || { echo >&2 "Maven is not installed in this machine"; exit 1; }

#1. 编译项目
(cd hackathon-web && mvn clean package )

webpath="hackathon-web/target/hackathon-web-0.1"
#2. 检查target下是否有正确的jar包
if [ -d $webpath ]; then
  echo "web项目打包成功"
else
  echo "web端没有打包成功, 请检查mvn的结果"
  exit
fi

abspath=`pwd`/$webpath

for (( i=1; i<3; i++)); do
  docker run -p 808$i:8080 -v $abspath:/usr/local/tomcat/webapps/ROOT -d docker.cn/docker/tomcat:8-jre8
  echo "启动tomcat，端口：808"$i
done


