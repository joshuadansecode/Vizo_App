@echo off
set "LOCAL_GRADLE=C:\Users\Hack_Josh\.gradle\wrapper\dists\gradle-8.10.2-bin\1hcd121un22v4njep37c8ls6j\gradle-8.10.2\bin\gradle.bat"
if exist "%LOCAL_GRADLE%" (
  echo Using local gradle...
  "%LOCAL_GRADLE%" app:installDebug
) else (
  echo Gradle not found
)
