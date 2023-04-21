cd Test_Framework/WEB-INF/classes
javac -cp ../lib/* -d . *.java
cd ../../
jar cvf Test_Framework.war *
copy Test_Framework.war "C:\Program Files\Apache Software Foundation\Tomcat 10.0\webapps"
cd ../