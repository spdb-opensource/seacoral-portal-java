#!/bin/bash
#set -o nounset
num=$(ps -ef | grep -v grep | grep "/usr/bin/java" | wc -l)
if [ $num -ge 1 ];then
    ps -ef | grep -v grep | grep "/usr/bin/java" | awk '{print $2}' | xargs kill -9 >>/dev/null
    if [ $? -eq 0 ];then
        echo "Stop web success"
    fi
elif [ $num -eq 0 ];then
    echo "web is not running!"
fi