#!/bin/bash
# Overwrite previous log file
echo Start > buildLog
# The line below will cause the script to exit if there was an error in any line
set -e
cd ~/transparentPolitics
echo Pulling from git
git pull >> buildLog
echo Running maven
mvn clean install >> buildLog
echo Building ember
cd ~/transparentPolitics/trpo/
ember build --environment=production >> buildLog
cd ~
echo Shutting down tomcat
# Failing to shutdown tomcat should not break build (will happen if tomcat was already shutdown)
set +e
sh ~/tomcat/bin/shutdown.sh >> buildLog
set -e
echo Deleting old tomcat dir
rm -rf ~/tomcat/webapps/ROOT
mkdir ~/tomcat/webapps/ROOT
echo Copying war to tomcat dir
cp ~/transparentPolitics/target/trapol.war ~/tomcat/webapps/ROOT/trapol.war
echo Extracting war
cd ~/tomcat/webapps/ROOT/
jar xvf ~/tomcat/webapps/ROOT/trapol.war >> buildLog
echo Deleting war file
rm -r ~/tomcat/webapps/ROOT/trapol.war
echo Copy dist file and servlet context.xml to exploded war folder
cp -r -u ~/transparentPolitics/trpo/dist ~/tomcat/webapps/ROOT/dist
cp ~/transparentPolitics/WebContent/WEB-INF/servlet-context.xml ~/tomcat/webapps/ROOT/WEB-INF/
echo Starting tomcat
cd ~/tomcat/bin
sh startup.sh >> buildLog
echo Finished!!
