cd /home/artemis/Documents/GitHub/Framework/Framework/build/web/WEB-INF/classes
jar -cf framework.jar etu2000
mv ./framework.jar /home/artemis/Documents/GitHub/Framework/Test-Framework/WEB-INF/lib
cd /home/artemis/Documents/GitHub/Framework/Test-Framework/WEB-INF/classes
javac -cp ../lib/framework.jar -d . *.java
cp -r /home/artemis/Documents/GitHub/Framework/Test-Framework /home/artemis/apache-tomcat-10.1.2/webapps
/home/artemis/apache-tomcat-10.1.2/bin/shutdown.sh
/home/artemis/apache-tomcat-10.1.2/bin/startup.sh