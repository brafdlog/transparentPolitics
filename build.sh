#!/bin/bash
echo Start > buidLog 2>&1
# The line below will cause the script to exit if there was an error in any line
set-e
cd ~/transparentPolitics
echo Pulling from git
git pull >> buidLog 2>>&1
echo Running maven
mvn install >> buidLog 2>>&1
echo Building ember
cd ~/transparentPolitics/trpo/
ember build >> buidLog 2>>&1
echo Shutting down tomcat
shutdown_tomcat >> buidLog 2>>&1
echo Deleting old tomcat dir
rm -rf ~/tomcat/webapps/ROOT
mkdir ~/tomcat/webapps/ROOT
echo Copying war to tomcat dir
cp ~/transparentPolitics/target/trapol.war ~/tomcat/webapps/ROOT/trapol.war
echo Extracting war
cd ~/tomcat/webapps/ROOT/
jar xvf ~/tomcat/webapps/ROOT/trapol.war >> buidLog 2>>&1
echo Deleting war file
rm -r ~/tomcat/webapps/ROOT/trapol.war
echo Copy dist file and servlet context.xml to exploded war folder
cp -r -u ~/transparentPolitics/trpo/dist ~/tomcat/webapps/ROOT/dist
cp ~/transparentPolitics/WebContent/WEB-INF/servlet-context.xml ~/tomcat/webapps/ROOT/WEB-INF/
echo Starting tomcat
cd ~/tomcat/bin
sh startup.sh >> buidLog 2>>&1
echo Finished!!
