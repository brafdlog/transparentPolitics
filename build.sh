#!/bin/bash
cd ~/transparentPolitics
git pull
mvn install
cp /home/ubuntu/transparentPolitics/target/trapol.war /home/ubuntu/apache-tomcat-7.0.59/webapps/ROOT.war
