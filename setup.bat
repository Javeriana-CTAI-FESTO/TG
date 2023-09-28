@echo off
REM Obtiene la ruta actual
set NGINX_PATH=%~dp0
set STARTUP_PATH=%APPDATA%\Microsoft\Windows\Start Menu\Programs\Startup

REM Crea el archivo start_nginx.bat
echo @echo off > %NGINX_PATH%start_nginx.bat
echo call start "" "%NGINX_PATH%nginx.exe" >> %NGINX_PATH%start_nginx.bat
echo call exit >> %NGINX_PATH%start_nginx.bat

REM Copia el archivo start_nginx.bat a la carpeta de inicio
call copy %NGINX_PATH%start_nginx.bat %STARTUP_PATH%

call exit