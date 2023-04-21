cd Framework
javac -d . *.java
jar cvf framework.jar etu2043
move framework.jar ../Test_Framework/WEB-INF/lib
cd ../