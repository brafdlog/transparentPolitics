#!/bin/bash
cd ~/transparentPolitics
echo Pulling from git
git pull
echo Running maven
mvn install
echo Copying war to tomcat dir
cp /home/ubuntu/transparentPolitics/target/trapol.war /home/ubuntu/tomcat/webapps/ROOT.war
