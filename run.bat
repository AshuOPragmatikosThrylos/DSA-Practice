@echo off
title Java Runner

:: PATH memory variable
set PROJECT_PATH=

:START

:: Ask for path ONLY if not already stored
if "%PROJECT_PATH%"=="" (
    set /p PROJECT_PATH=Enter the path to your project folder: 
)

:: Validate path
if not exist "%PROJECT_PATH%" (
    echo Directory does not exist.
    echo.
    set PROJECT_PATH=
    goto START
)

cd /d "%PROJECT_PATH%"

:: Create bin directory if missing
if not exist bin (
    mkdir bin
)

echo Compiling...
javac -d bin Main.java
if errorlevel 1 (
    echo Compilation failed.
    echo.
    goto ASK_RESTART
)

echo Running...
java -cp ".;bin;bin\**" Main

echo.
echo ---------------------------------------
echo Program finished.
echo ---------------------------------------
echo.

:ASK_RESTART
set /p REPLY=Do you want to run again (Y/N)? 
if "%REPLY%"=="" set REPLY=Y

if /I "%REPLY%"=="Y" (
    echo.
    goto START
)

if /I "%REPLY%"=="N" (
    echo Exiting...
    pause
    exit /b
)

echo Invalid choice. Please type Y or N.
echo.
goto ASK_RESTART
