@echo off
set "JAVA_HOME=C:\Program Files\Amazon Corretto\jdk17.0.18_9"
set "PATH=%JAVA_HOME%\bin;%PATH%"
.\gradlew.bat assembleDebug
pause
