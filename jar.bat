cd Framework
javac -parameters -d . *.java
jar cvf framework.jar etu1792
move framework.jar ../Test_Framework/lib
copy "gson-2.8.2.jar" "../Test_Framework/lib"
cd ../
