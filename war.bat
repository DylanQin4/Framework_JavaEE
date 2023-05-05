set c_webapps=C:\Program Files\Apache Software Foundation\Tomcat 10.0\webapps
set c_temp=D:\ITU\S4\Web Dynamique\Framework\temp
set c_lib=D:\ITU\S4\Web Dynamique\Framework\Test_Framework\lib
set c_jsp=D:\ITU\S4\Web Dynamique\Framework\Test_Framework\build\web
set c_xml=D:\ITU\S4\Web Dynamique\Framework\Test_Framework\build\web\WEB-INF
set c_java=D:\ITU\S4\Web Dynamique\Framework\Test_Framework\src\java\modele


rmdir temp /S/Q
mkdir temp
cd temp
mkdir WEB-INF
cd WEB-INF
mkdir lib
mkdir classes
cd ../../

copy "%c_jsp%\*.jsp" "%c_temp%"
copy "%c_xml%\web.xml" "%c_temp%\WEB-INF"
copy "%c_lib%\*.jar" "%c_temp%\WEB-INF\lib"
copy "%c_java%\*java" "%c_temp%\WEB-INF\classes"

cd %c_temp%\WEB-INF\classes\
javac -parameters -cp "%c_temp%\WEB-INF\lib\*" -d "%c_temp%\WEB-INF\classes" *.java
cd ../../../temp

jar cvf Test_Framework.war *
copy Test_Framework.war "%c_webapps%"
cd ../
