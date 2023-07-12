@echo off

for %%I in ("%~dp0.") do set "currentFolder=%%~nxI"

set "url=http://localhost:8080/"
setlocal enabledelayedexpansion

set "url=!url!!currentFolder!"

REM Compile Java files
echo Compiling Java files...
javac WEB-INF\classes\*.java

REM Check for compile-time errors
if %errorlevel% neq 0 (
    echo Compile-time errors found. Please check the error messages.
    pause

) else (
    echo Compilation successful. Running another batch file...
    call ..\..\bin\shutdown.bat
    call ..\..\bin\startup.bat

    timeout /t 4 >nul

    echo Opening default browser...
    start %url%
)