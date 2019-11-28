@echo off
echo.
echo -- Compilant : 
echo.
set BASE=%cd%
set TEST=uoc.ded.practica.Play4FunTest
set LIBS=%BASE%\lib\tads_cast.jar;%BASE%\lib\junit-4.12.jar;%BASE%\lib\hamcrest-core-1.3.jar;%BASE%\class\main\java\;%BASE%\class\test\java\;.
set OPTS=-encoding UTF-8 -g
if exist class rmdir /Q /S class 
mkdir "class\main\java"
mkdir "class\test\java"
cd %BASE%\src\main\java
javac %OPTS% -classpath %LIBS% -d %BASE%\class\main\java uoc\ded\practica\exceptions\*.java
javac %OPTS% -classpath %LIBS% -d %BASE%\class\main\java uoc\ded\practica\models\*.java
javac %OPTS% -classpath %LIBS% -d %BASE%\class\main\java uoc\ded\practica\tads\*.java
javac %OPTS% -classpath %LIBS% -d %BASE%\class\main\java uoc\ded\practica\*.java

cd %BASE%\src\test\java
javac %OPTS% -classpath %LIBS% -d %BASE%\class\test\java uoc\ded\practica\*.java
if %ERRORLEVEL%==0 (
   cd %BASE%\src\main\java
   echo.
   echo -- Executant JUnit tests : 
   echo.
   java -classpath %LIBS% org.junit.runner.JUnitCore %TEST%
   cd %BASE%
)
echo.
