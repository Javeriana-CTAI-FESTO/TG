package co.edu.javeriana.ctai.installer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Install {

    // Variables de instancia
    private static Install instance;
    private final Path mainDirectory;
    private final String osName;
    private String mvnwCommand;
    private String mes4DDBBpath;

    // Variables para guardar los PIDs de los procesos
    private Long pidTgSecurity;
    private Long pidTgFesto;

    // Constructor
    public Install() {
        // Detectar sistema operativo
        System.out.println("Sistema operativo: " + System.getProperty("os.name"));
        this.osName = System.getProperty("os.name").toLowerCase();

        // Directorio de trabajo actual
        System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));
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

    // Configurar el comando de compilación (mvnw o ./mvnw)
    public void setMvnwCommand() {
        if (this.osName.contains("win")) {
            mvnwCommand = "mvnw.cmd";
            System.out.println("Sistema operativo Windows detectado.");
        } else if (this.osName.contains("nix") || this.osName.contains("nux") || this.osName.contains("mac")) {
            mvnwCommand = "./mvnw";
            System.out.println("Sistema operativo Linux o Mac detectado.");
        } else {
            System.out.println("Sistema operativo no compatible.");
        }
    }
    public boolean isAppAlreadyRunning() {
        Path lockFilePath = Paths.get(System.getProperty("user.home"), ".TG9.lock");
        File lockFile = lockFilePath.toFile();

        if (lockFile.exists()) {
            return false;
        } else {
            try {
                File lockFileCreated = new File(lockFilePath.toString());
                lockFileCreated.createNewFile();
                return true;
            } catch (IOException e) {
                System.out.println("Unable to create lock file");
                return false;
            }
        }
    }

    // Método para ejecutar la instalación
    public boolean exec() {
        if (this.mainDirectory.endsWith("TG")) {
            System.out.println("Ejecutando instalador...");
            execSecurityModule();
            return true;
        } else {
            System.out.println("No se encuentra en el directorio raíz del proyecto.");
            return false;
        }
    }


    // Método para ejecutar el módulo de seguridad
    public void execSecurityModule() {
        // Ruta del archivo JAR del módulo de seguridad
        Path securityModuloJarPath = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("componet-security").resolve("tgsecurity").resolve("target").resolve("tgsecurity-0.0.1-SNAPSHOT.jar");
        System.out.println("Ruta del jar: " + securityModuloJarPath);

        // Comando para ejecutar el módulo de seguridad
        String[] runComandSecurityModule = {"java", "-jar", securityModuloJarPath.toString()};

        // Crear un hilo para la ejecución del proceso
        Thread executionThread = new Thread(() -> {
            try {
                // Crear el proceso
                ProcessBuilder processBuilder = new ProcessBuilder(runComandSecurityModule);

                // Configurar variables de entorno
                Path variableEntornoRUTA_MODULO_JAR = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("Festo").resolve("target").resolve("tg-0.0.1-SNAPSHOT.jar");
                Path variableEntornoSERVER_P12 = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("componet-security").resolve("server.p12");

                processBuilder.environment().put("RUTA_MODULO_JAR", variableEntornoRUTA_MODULO_JAR.toString());
                processBuilder.environment().put("SERVER_P12", variableEntornoSERVER_P12.toString());
                processBuilder.environment().put("DATABASE_FILE", this.mes4DDBBpath);

                // Iniciar el proceso
                Process process = processBuilder.start();
                this.pidTgSecurity = process.pid();
                System.out.println("Modulo Security. Encendido Codigo ");

                // Imprimir la salida del proceso
                printProcess(process);

                // Esperar a que el proceso secundario finalice
                int exitCode = process.waitFor();
                System.out.println("Modulo Security. DETENIDO Codigo: " + exitCode);

            } catch (IOException e) {
                System.err.println("Error al ejecutar el módulo JAR: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El hilo de ejecución se interrumpió: " + e.getMessage());
            }
        });

        // Iniciar el hilo
        executionThread.start();
    }

    // Método para imprimir la salida de un proceso
    public void printProcess(Process p) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
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
                if (processName.equals("tgsecurity-0.0.1-SNAPSHOT.jar") || processName.equals("Process")){
                    pidTgSecurity = pid;
                    pidTgFesto = pid;
                    stopProcesses();
                    System.out.println("kill tgsecurity" + pid);
                }
                if (processName.equals("tg-0.0.1-SNAPSHOT.jar") || processName.equals("Process")) {
                    pidTgFesto = pid;
                    pidTgSecurity = pid;
                    stopProcesses();
                    System.out.println("kill tg-0.0.1-SNAPSHOT.jar" + pid);
                }
            }
        }
        reader.close();

        // Eliminar el archivo de bloqueo
        Path lockFilePath = Paths.get(System.getProperty("user.home"), ".TG9.lock");
        File lockFile = lockFilePath.toFile();
        if (lockFile.exists()) {
            lockFile.delete();
        }
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
                System.out.println("Proceso detenido: " + this.pidTgSecurity.toString());


            } catch (IOException e) {
                System.err.println("Error al detener el proceso anterior: " + e.getMessage());
            }
        }
        if (this.pidTgFesto != null && this.osName.contains("mac")){
            try {
                ProcessBuilder killProcessBuilder = new ProcessBuilder("kill", "-9", this.pidTgFesto.toString());
                killProcessBuilder.start();
                System.out.println("Proceso detenido: " + this.pidTgFesto.toString());


            } catch (IOException e) {
                System.err.println("Error al detener el proceso anterior: " + e.getMessage());
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
                System.out.println("Proceso detenido W: " + pid);


            } catch (IOException e) {
                System.err.println("Error al detener el proceso anterior: " + e.getMessage());
            }
        }
        if(this.pidTgFesto != null && this.osName.contains("win")){
            try {
                assert this.pidTgSecurity != null;
                String pid = this.pidTgSecurity.toString();
                // Crear el proceso de PowerShell
                ProcessBuilder processBuilder = new ProcessBuilder(rutaPowerShell, "-Command", "Stop-Process","-Id",pid, "-Force");

                // Redirigir la salida estándar y la salida de errores
                processBuilder.redirectErrorStream(true);

                // Iniciar el proceso de PowerShell
                processBuilder.start();
                System.out.println("Proceso detenido W: " + this.pidTgFesto.toString());


            } catch (IOException e) {
                System.err.println("Error al detener el proceso anterior: " + e.getMessage());
            }
        }


    }

    // Establecer la ruta de la base de datos MES
    public void setMes4DDBBpath(String mes4DDBBpath) {
        this.mes4DDBBpath = mes4DDBBpath;
    }

    // Ejecutar el módulo de FESTO
    public void runTgfestoModule() {
        // Definir las rutas a los directorios de los módulos
        String festoModule = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("Festo").resolve("target").resolve("tg-0.0.1-SNAPSHOT.jar").toString();

        // Comando para ejecutar el módulo JAR
        String[] runComandSecurityModule = {"java", "-jar","-Dspring.profiles.active=dev", festoModule};

        // Iniciar el proceso en un hilo aparte
        Thread executionThread = new Thread(() -> {
            try {
                // Crear el proceso
                ProcessBuilder processBuilder = new ProcessBuilder(runComandSecurityModule);

                processBuilder.environment().put("DATABASE_FILE", this.mes4DDBBpath);

                // Iniciar el proceso
                Process process = processBuilder.start();
                this.pidTgFesto = process.pid();
                System.out.println("Modulo FESTO. Encendido Codigo ");
                printProcess(process);

                // Esperar a que el proceso secundario finalice
                int exitCode = process.waitFor();
                System.out.println("Modulo FESTO. DETENIDO Codigo: " + exitCode);

            } catch (IOException e) {
                System.err.println("Error al ejecutar el módulo FESTO JAR: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El hilo de ejecución se interrumpió: " + e.getMessage());
            }
        });

        // Iniciar el hilo
        executionThread.start();
    }
}
