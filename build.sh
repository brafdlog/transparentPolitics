#!/bin/bash
cd ~/transparentPolitics
echo Pulling from git
git pull
echo Running maven
mvn install
echo Shutting down tomcat
shutdown_tomcat
echo Deleting old tomcat dir
rm -rf ~/tomcat/webapps/ROOT
mkdir ~/tomcat/webapps/ROOT
echo Copying war to tomcat dir
cp ~/transparentPolitics/target/trapol.war ~/tomcat/webapps/ROOT/trapol.war
echo Extracting war
cd ~/tomcat/webapps/ROOT/
jar xvf ~/tomcat/webapps/ROOT/trapol.war
echo Deleting war file
rm -r ~/tomcat/webapps/ROOT/trapol.war
echo Copy dist file and servlet context.xml to exploded war folder
cp -r -u ~/transparentPolitics/WebContent/dist ~/tomcat/webapps/ROOT/dist
cp ~/transparentPolitics/WebContent/WEB-INF/servlet-context.xml ~/tomcat/webapps/ROOT/WEB-INF/
echo Starting tomcat
cd ~/tomcat/bin
sh startup.sh
