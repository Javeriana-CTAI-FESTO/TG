package co.edu.javeriana.ctai.installer.model;

import co.edu.javeriana.ctai.installer.model.tools.LoggingService;
import co.edu.javeriana.ctai.installer.model.tools.Tools;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Install {

    //private static final System.Logger LOGGER = System.getLogger(Install.class.getName());


    // Variables de instancia
    private static Install instance;
    private final Path mainDirectory;
    private final String osName;
    private String mvnwCommand;
    private String mes4DDBBpath;

    // Variables para guardar los PIDs de los procesos
    private Long pidTgSecurity;
    private Long pidTgFesto;

    private Tools tools;

    // Constructor
    public Install() {

        this.tools = new Tools();

        // Detectar sistema operativo
        //LOGGER.log(System.Logger.Level.INFO, "Sistema operativo: " + System.getProperty("os.name"));
        tools.getLoggingService().logInfo("Sistema operativo: " + System.getProperty("os.name"));
        this.osName = System.getProperty("os.name").toLowerCase();

        // Directorio de trabajo actual
        //LOGGER.log(System.Logger.Level.INFO, "Directorio de trabajo: " + System.getProperty("user.dir"));
        this.tools.getLoggingService().logInfo("Directorio de trabajo: " + System.getProperty("user.dir"));
        this.mainDirectory = Paths.get(System.getProperty("user.dir"));

        // Configurar el comando de compilación dependiendo del sistema operativo
        setMvnwCommand();

        // Ruta por defecto de la base de datos
        this.mes4DDBBpath = "FestoMES_be.accdb";
    }

    public static Install getInstance() {
        if (instance == null) {
            instance = new Install();
        }
        return instance;
    }

    // Obtener el nombre del sistema operativo
    public String getOsName() {
        return osName;
    }

    // Obtener el comando de compilación mvnw
    public String getMvnwCommand() {
        return mvnwCommand;
    }

    // Obtener el directorio principal
    public String getMainDirectory() {
        return mainDirectory.toString();
    }

    // Establecer la ruta de la base de datos MES
    public void setMes4DDBBpath(String mes4DDBBpath) {
        this.mes4DDBBpath = mes4DDBBpath;
    }

    // Configurar el comando de compilación (mvnw o ./mvnw)
    public void setMvnwCommand() {
        if (this.osName.contains("win")) {
            mvnwCommand = "mvnw.cmd";

            //LOGGER.log(System.Logger.Level.INFO, "Sistema operativo Windows detectado.");
            this.tools.getLoggingService().logInfo("Sistema operativo Windows detectado.");
        } else if (this.osName.contains("nix") || this.osName.contains("nux") || this.osName.contains("mac")) {
            mvnwCommand = "./mvnw";
            //LOGGER.log(System.Logger.Level.INFO, "Sistema operativo Linux o Mac detectado.");
            this.tools.getLoggingService().logInfo("Sistema operativo Linux o Mac detectado.");
        } else {
            //LOGGER.log(System.Logger.Level.ERROR, "Sistema operativo no soportado.");
            this.tools.getLoggingService().logError("Sistema operativo no soportado.");
        }
    }

    public Tools getTools() {
        return tools;
    }


    // Método para ejecutar el módulo de seguridad
    public void execSecurityModule() {
        // Ruta del archivo JAR del módulo de seguridad
        Path securityModuloJarPath = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("componet-security").resolve("tgsecurity").resolve("target").resolve("tgsecurity-2.5.3-prod.jar");
        //LOGGER.log(System.Logger.Level.INFO, "Ruta del jar: " + securityModuloJarPath);
        this.tools.getLoggingService().logInfo("Ruta del jar: " + securityModuloJarPath);

        // Comando para ejecutar el módulo de seguridad
        String[] runComandSecurityModule = {"java", "-jar","-Dspring.profiles.active=prod", securityModuloJarPath.toString()};

        // Crear un hilo para la ejecución del proceso
        Thread executionThread = new Thread(() -> {
            try {
                // Crear el proceso
                ProcessBuilder processBuilder = new ProcessBuilder(runComandSecurityModule);

                // Iniciar el proceso
                Process process = processBuilder.start();
                this.pidTgSecurity = process.pid();

                //LOGGER.log(System.Logger.Level.INFO, "Modulo Security. Encendido Codigo ");
                this.tools.getLoggingService().logInfo("Modulo Security. Encendido Codigo ");

                // Imprimir la salida del proceso
                tools.printProcess(process);

                // Esperar a que el proceso secundario finalice
                int exitCode = process.waitFor();

                //LOGGER.log(System.Logger.Level.INFO, "Modulo Security. DETENIDO Codigo: " + exitCode);
                this.tools.getLoggingService().logInfo("Modulo Security. DETENIDO Codigo: " + exitCode);

            } catch (IOException e) {

                //LOGGER.log(System.Logger.Level.ERROR, "Error al ejecutar el módulo JAR: " + e.getMessage());
                this.tools.getLoggingService().logError("Error al ejecutar el módulo JAR: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                //LOGGER.log(System.Logger.Level.ERROR, "El hilo de ejecución se interrumpió: " + e.getMessage());
                this.tools.getLoggingService().logError("El hilo de ejecución se interrumpió: " + e.getMessage());
            }
        });

        // Iniciar el hilo
        executionThread.start();
    }

    // Ejecutar el módulo de FESTO
    public void runTgfestoModule() {

        // Extraer Ruta de modulo tg
        String festoModule = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("CP_Factory").resolve("target").resolve("tg-2.7.16-prod.jar").toString();

        // Comando para ejecutar el módulo JAR
        String[] runComandSecurityModule = {"java", "-jar","-Dspring.profiles.active=prod", festoModule};

        // Iniciar el proceso en un hilo aparte
        Thread executionThread = new Thread(() -> {
            try {
                // Crear el proceso para ejecutar modulo tg
                ProcessBuilder processBuilder = new ProcessBuilder(runComandSecurityModule);
                // Seteando las variables de entorno correspondientes al modulo tg
                processBuilder.environment().put("DATABASE_FILE", this.mes4DDBBpath);

                // Iniciar el proceso del modulo tg
                Process process = processBuilder.start();
                this.pidTgFesto = process.pid();

                //LOGGER.log(System.Logger.Level.INFO, "Modulo FESTO. Encendido Codigo ");
                this.tools.getLoggingService().logInfo("Modulo FESTO. Encendido Codigo ");
                tools.printProcess(process);

                // Esperar a que el proceso que ejecuta el modulo tg finalice
                int exitCode = process.waitFor();

                //LOGGER.log(System.Logger.Level.INFO, "Modulo FESTO. DETENIDO Codigo: " + exitCode);
                this.tools.getLoggingService().logInfo("Modulo FESTO. DETENIDO Codigo: " + exitCode);

            } catch (IOException e) {

                //LOGGER.log(System.Logger.Level.ERROR, "Error al ejecutar el módulo FESTO JAR: " + e.getMessage());
                tools.getLoggingService().logError("Error al ejecutar el módulo FESTO JAR: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                //LOGGER.log(System.Logger.Level.ERROR, "El hilo de ejecución se interrumpió: " + e.getMessage());
                tools.getLoggingService().logError("El hilo de ejecución se interrumpió: " + e.getMessage());
            }
        });

        // Iniciar el hilo que ejecuta procesos tg
        executionThread.start();
    }

    // Método para obtener el proceso de listado de procesos según el sistema operativo
    private Process getProceso() throws IOException {
        ProcessBuilder processBuilder = null;

        if(this.osName.contains("win")){
            // Solo funciona en Windows
            // Ruta completa al ejecutable de PowerShell
            String rutaPowerShell = "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe";
            processBuilder = new ProcessBuilder(rutaPowerShell, "-Command", "jps");
            // Redirigir la salida estándar y la salida de errores
            processBuilder.redirectErrorStream(true);
        }
        if (this.osName.contains("mac")){
            processBuilder = new ProcessBuilder("jps");
            // Redirigir la salida estándar y la salida de errores
            processBuilder.redirectErrorStream(true);
        }

        // Iniciar el proceso
        assert processBuilder != null;
        return processBuilder.start();
    }

    // Método para detener procesos antiguos
    public void stopOlds(String processName) throws IOException {

        Process proceso = getProceso();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(" ");
            if (parts.length >= 2 && parts[1].equals(processName)) {
                long pid = Long.parseLong(parts[0]); // Obtener el ID del proceso
                if (processName.equals("tgsecurity-2.5.3-prod.jar") || processName.equals("Process")){
                    pidTgSecurity = pid;
                    pidTgFesto = pid;
                    stopProcesses();

                    //LOGGER.log(System.Logger.Level.INFO, "kill tgsecurity-0.0.1-SNAPSHOT.jar " + pid);
                    tools.getLoggingService().logInfo("kill tgsecurity-2.5.3-prod.jar " + pid);
                }
                if (processName.equals("tg-2.7.16-prod.jar") || processName.equals("Process")) {
                    pidTgFesto = pid;
                    pidTgSecurity = pid;
                    stopProcesses();

                    //LOGGER.log(System.Logger.Level.INFO, "kill tg-2.7.16-prod.jar " + pid);
                    tools.getLoggingService().logInfo("kill tg-2.7.16-prod.jar " + pid);
                }
            }
        }
        reader.close();
    }

    // Método para ejecutar el navegador
    public void navExE(){
        // Ejecutar Frondend
        if(!tools.frontExE(this.osName, this.mainDirectory)){

            //LOGGER.log(System.Logger.Level.ERROR, "Error al ejecutar el Frondend.");
            tools.getLoggingService().logError("Error al ejecutar el Frondend.");
        }

        String url = "http://localhost:4200"; // Reemplaza esto con la URL de la página que deseas abrir
        try {
            // Verifica si el soporte de escritorio está disponible
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                // Asegúrar de que el navegador web predeterminado esté registrado en el sistema
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    // Abre la página web en el navegador predeterminado
                    desktop.browse(new URI(url));
                } else {

                    //LOGGER.log(System.Logger.Level.WARNING, "El navegador web no está soportado en este sistema.");
                    tools.getLoggingService().logWarning("El navegador web no está soportado en este sistema.");
                }
            } else {
                //LOGGER.log(System.Logger.Level.WARNING, "La funcionalidad del escritorio no está soportada en este sistema.");
                tools.getLoggingService().logWarning("La funcionalidad del escritorio no está soportada en este sistema.");
            }


        } catch (Exception e) {
            System.out.println("Shortcut de navegador Fallido");
        }

    }

    // Método para detener procesos
    public void stopProcesses() {

        // Ruta completa al ejecutable de PowerShell (solo para Windows)
        String rutaPowerShell = "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe";

        long pidActual = ProcessHandle.current().pid();
        System.out.println("Proceso actual: " + pidActual);

        // Detener el proceso de tg-security y tg-festo (solo para macOS)
        if(this.pidTgSecurity != null && this.osName.contains("mac")){
            try {
                ProcessBuilder killProcessBuilder = new ProcessBuilder("kill", "-9", this.pidTgSecurity.toString());
                killProcessBuilder.start();

                //LOGGER.log(System.Logger.Level.INFO, "Proceso detenido tg-s: " + this.pidTgSecurity.toString());
                tools.getLoggingService().logInfo("Proceso detenido tg-s: " + this.pidTgSecurity.toString());


            } catch (IOException e) {
                //LOGGER.log(System.Logger.Level.ERROR, "Error al detener el proceso anterior: " + e.getMessage());
                tools.getLoggingService().logError("Error al detener el proceso anterior: " + e.getMessage());
            }
        }
        if (this.pidTgFesto != null && this.osName.contains("mac")){
            try {
                ProcessBuilder killProcessBuilder = new ProcessBuilder("kill", "-9", this.pidTgFesto.toString());
                killProcessBuilder.start();
                //LOGGER.log(System.Logger.Level.INFO, "Proceso detenido tg: " + this.pidTgFesto.toString());
                tools.getLoggingService().logInfo("Proceso detenido tg: " + this.pidTgFesto.toString());


            } catch (IOException e) {
                //LOGGER.log(System.Logger.Level.ERROR, "Error al detener el proceso anterior: " + e.getMessage());
                tools.getLoggingService().logError("Error al detener el proceso anterior: " + e.getMessage());
            }
        }

        // Detener el proceso (para Windows)
        if (this.pidTgSecurity != null && this.osName.contains("win")) {
            try {
                String pid = this.pidTgSecurity.toString();
                // Crear el proceso de PowerShell
                ProcessBuilder processBuilder = new ProcessBuilder(rutaPowerShell, "-Command", "Stop-Process","-Id", pid , "-Force");

                // Redirigir la salida estándar y la salida de errores
                processBuilder.redirectErrorStream(true);

                // Iniciar el proceso de PowerShell
                processBuilder.start();
                //LOGGER.log(System.Logger.Level.INFO, "Proceso detenido TG-S: " + pid);
                tools.getLoggingService().logInfo("Proceso detenido TG-S: " + pid);


            } catch (IOException e) {
                //LOGGER.log(System.Logger.Level.ERROR, "Error al detener el proceso anterior: " + e.getMessage());
                tools.getLoggingService().logError("Error al detener el proceso anterior: " + e.getMessage());
            }
        }
        if(this.pidTgFesto != null && this.osName.contains("win")){
            try {
                String pid = this.pidTgFesto.toString();
                // Crear el proceso de PowerShell
                ProcessBuilder processBuilder = new ProcessBuilder(rutaPowerShell, "-Command", "Stop-Process","-Id",pid, "-Force");

                // Redirigir la salida estándar y la salida de errores
                processBuilder.redirectErrorStream(true);

                // Iniciar el proceso de PowerShell
                processBuilder.start();

                //LOGGER.log(System.Logger.Level.INFO, "Proceso detenido TG: " + pid);
                tools.getLoggingService().logInfo("Proceso detenido TG: " + pid);


            } catch (IOException e) {

                //LOGGER.log(System.Logger.Level.ERROR, "Error al detener el proceso anterior: " + e.getMessage());
                tools.getLoggingService().logError("Error al detener el proceso anterior: " + e.getMessage());
            }
        }

    }
    public void killFrond(){
        if (this.osName.contains("win")) {
            String exePath = this.mainDirectory.resolve("nginx-1.25.2").resolve("nginx.exe").toFile().toString();
            String exePath2 = this.mainDirectory.resolve("nginx-1.25.2").toAbsolutePath().toString();
            // Detener el proceso existente si está en ejecución
            tools.stopProcess(exePath);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                //LOGGER.log(System.Logger.Level.ERROR, "HILO SLEEP Error al detener el proceso anterior: " + e.getMessage());
                tools.getLoggingService().logError("HILO SLEEP Error al detener el proceso anterior: " + e.getMessage());
            }

            tools.runCmdCommand("cd "+ exePath2 +" && nginx -s quit");

            //LOGGER.log(System.Logger.Level.INFO, "Proceso detenido FrondEnd: " + exePath);
            tools.getLoggingService().logInfo("Proceso detenido FrondEnd: " + exePath);
        } else {
            //LOGGER.log(System.Logger.Level.WARNING, "No es Windows, no se puede reiniciar el proceso.");
            tools.getLoggingService().logWarning("No es Windows, no se puede reiniciar el proceso.");
        }
    }

    public void exitFronEndPID(){

        if(this.osName.contains("win")){
            String rutaPowerShell = "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe";
            String pid = String.valueOf(this.tools.frontendPID(this.mainDirectory));
            try {

                // Crear el proceso de PowerShell
                ProcessBuilder processBuilder = new ProcessBuilder(rutaPowerShell, "-Command", "Stop-Process","-Id",pid, "-Force");
                // Redirigir la salida estándar y la salida de errores
                processBuilder.redirectErrorStream(true);
                // Iniciar el proceso de PowerShell
                processBuilder.start();

            } catch (IOException e) {

                //LOGGER.log(System.Logger.Level.ERROR, "Error al detener el proceso anterior: " + e.getMessage());
                tools.getLoggingService().logError("Error al detener el proceso anterior: " + e.getMessage());
            }
            //LOGGER.log(System.Logger.Level.INFO, "FronEnd: " + pid + " DETENIDO");
            tools.getLoggingService().logInfo("FronEnd: " + pid + " DETENIDO");
        }
    }


}