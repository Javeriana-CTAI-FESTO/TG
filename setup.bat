@echo off

echo Cambiando al directorio 'front'
cd front

echo Construyendo la aplicación Angular para producción
call ng build --configuration production

echo Instalando Express
call npm install express

echo Instalando PM2 globalmente
call npm install pm2 -g

echo Instalando pm2-windows-startup globalmente
call npm install pm2-windows-startup -g

echo Iniciando el servidor con PM2
call pm2 start server.js

echo Configurando PM2 para iniciar automáticamente en el arranque
call pm2-startup install

echo Guardando la lista actual de procesos de PM2
call pm2 save

echo Verificando el estado de PM2
call pm2 status