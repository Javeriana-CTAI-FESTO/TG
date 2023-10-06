package co.edu.javeriana.ctai.installer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Install {

    private final Path mainDirectory;
    private final String osName;
    private String mvnwCommand;
    private String mes4DDBBpath;

    //Guardar los pid de los procesos
    private Long pidTgSecurity;
    private Long pidTgFesto;

    public Install() {
        // Sistema operativo
        System.out.println("Sistema operativo: " + System.getProperty("os.name"));
        this.osName = System.getProperty("os.name").toLowerCase();
        // Idioma del sistema
        System.out.println("Idioma del sistema: " + System.getProperty("user.language"));
        // Directorio de trabajo
        System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));
        this.mainDirectory = Paths.get(System.getProperty("user.dir"));
        setMvnwCommand();
        this.mes4DDBBpath = "FestoMES_be.accdb";
    }

    public String getOsName() {
        return osName;
    }

    public String getMvnwCommand() {
        return mvnwCommand;
    }

    public String getMainDirectory() {
        return mainDirectory.toString();
    }

    public void setMvnwCommand() {
        // Detectar sistema operativo y ajustar el comando de compilación
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

    public void execSecurityModule(){

        Path securityModuloJarPath = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("componet-security").resolve("tgsecurity").resolve("target").resolve("tgsecurity-0.0.1-SNAPSHOT.jar");
        System.out.println("Ruta del jar: " + securityModuloJarPath);

        String[] runComandSecurityModule = {"java", "-jar", securityModuloJarPath.toString()};

        Thread executionThread = new Thread(() -> {
            try {
                // Inyecta el servicio y llama a su método para ejecutar el módulo JAR
                ProcessBuilder processBuilder = new ProcessBuilder(runComandSecurityModule);

                // Seteando Variables de entorno
                Path variableEntornoRUTA_MODULO_JAR = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("Festo").resolve("target").resolve("tg-0.0.1-SNAPSHOT.jar");
                Path variableEntornoSERVER_P12 = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("componet-security").resolve("server.p12");

                processBuilder.environment().put("RUTA_MODULO_JAR", variableEntornoRUTA_MODULO_JAR.toString());
                processBuilder.environment().put("SERVER_P12", variableEntornoSERVER_P12.toString());
                processBuilder.environment().put("DATABASE_FILE", this.mes4DDBBpath);
                Process process = processBuilder.start();
                this.pidTgSecurity = process.pid();
                System.out.println("Modulo Security. Encendido Codigo ");

                printProcess(process);

                // Espera a que el proceso secundario finalice
                int exitCode = process.waitFor();
                System.out.println("Modulo Security. DETENIDO Codigo: " + exitCode);

            } catch (IOException e) {
                System.err.println("Error al ejecutar el módulo JAR: " + e.getMessage());
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura la bandera de interrupción
                System.err.println("El hilo de ejecución se interrumpió: " + e.getMessage());
                e.printStackTrace();
            }
        });

        // Iniciar el hilo
        executionThread.start();
    }

    public void printProcess(Process p) throws IOException {
        // Imprime la salida del proceso secundario en la consola
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public void stopOlds(String processName) throws IOException {
        String rutaPowerShell = "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe";

        // Crear el proceso de PowerShell
        ProcessBuilder processBuilder = new ProcessBuilder(rutaPowerShell, "-Command", "jps");

        // Redirigir la salida estándar y la salida de errores
        processBuilder.redirectErrorStream(true);

        // Iniciar el proceso de PowerShell
        Process procesoPowerShell = processBuilder.start();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(procesoPowerShell.getInputStream()));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(" ");
            if (parts.length >= 2 && parts[1].equals(processName)) {
                long pid = Long.parseLong(parts[0]); // Obtener el ID del proceso
                if (processName.equals("tgsecurity-0.0.1-SNAPSHOT.jar")) {
                    pidTgSecurity = pid;
                    pidTgFesto = pid;
                    stopProcesses();
                    System.out.println("kill tgsecurity"+ pid);
                }
                if (processName.equals("tg-0.0.1-SNAPSHOT.jar")) {
                    pidTgFesto = pid;
                    pidTgSecurity = pid;
                    stopProcesses();
                    System.out.println("kill tg-0.0.1-SNAPSHOT.jar" + pid);

                }
            }
        }

        reader.close();
    }
    public void stopProcesses() {

    //usa jps
        // Ruta completa al ejecutable de PowerShell
        String rutaPowerShell = "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe";

        Long pidActual = ProcessHandle.current().pid();
        System.out.println("Proceso actual: " + pidActual.toString());

        // se detiene el proceso de tg-security y tg-festo
        if(this.pidTgSecurity != null && this.osName.contains("mac")){
            try {
                ProcessBuilder killProcessBuilder = new ProcessBuilder("kill", "-9", this.pidTgSecurity.toString());
                killProcessBuilder.start();
                System.out.println("Proceso detenido: " + this.pidTgSecurity.toString());
            } catch (IOException e) {
                System.err.println("Error al detener el proceso anterior: " + e.getMessage());
                e.printStackTrace();
            }
        }
        if (this.pidTgFesto != null && this.osName.contains("mac")){
            try {
                ProcessBuilder killProcessBuilder = new ProcessBuilder("kill", "-9", this.pidTgFesto.toString());
                killProcessBuilder.start();
                System.out.println("Proceso detenido: " + this.pidTgFesto.toString());
            } catch (IOException e) {
                System.err.println("Error al detener el proceso anterior: " + e.getMessage());
                e.printStackTrace();
            }
        }
        if (this.pidTgSecurity != null && this.osName.contains("win")) {
            try {
                String pid = this.pidTgSecurity.toString();
                // Crear el proceso de PowerShell
                ProcessBuilder processBuilder = new ProcessBuilder(rutaPowerShell, "-Command", "Stop-Process","-Id",pid, "-Force");

                // Redirigir la salida estándar y la salida de errores
                processBuilder.redirectErrorStream(true);

                // Iniciar el proceso de PowerShell
                processBuilder.start();
                System.out.println("Proceso detenido W: " + pid);
            } catch (IOException e) {
                System.err.println("Error al detener el proceso anterior: " + e.getMessage());
                e.printStackTrace();
            }
        }
        if(this.pidTgFesto != null && this.osName.contains("win")){
            try {
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
                e.printStackTrace();
            }
        }
        System.out.println("Proceso detenido: " + pidActual.toString());
    }






    public void setMes4DDBBpath(String mes4DDBBpath) {
        this.mes4DDBBpath = mes4DDBBpath;
    }

    public void runTgfestoModule() {


        // Define las rutas a los directorios de los módulos
        String festoModule = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("Festo").resolve("target").resolve("tg-0.0.1-SNAPSHOT.jar").toString();

        // Comando para ejecutar el módulo JAR
        String[] runComandSecurityModule = {"java", "-jar","-Dspring.profiles.active=dev", festoModule};
        // Iniciar el proceso en un hilo aparte
        Thread executionThread = new Thread(() -> {
            try {
                // Inyecta el servicio y llama a su método para ejecutar el módulo JAR
                ProcessBuilder processBuilder = new ProcessBuilder(runComandSecurityModule);

                processBuilder.environment().put("DATABASE_FILE", this.mes4DDBBpath);
                Process process = processBuilder.start();
                this.pidTgFesto = process.pid();
                System.out.println("Modulo FESTO. Encendido Codigo ");
                printProcess(process);

                // Espera a que el proceso secundario finalice
                int exitCode = process.waitFor();
                System.out.println("Modulo FESTO. DETENIDO Codigo: " + exitCode);

            } catch (IOException e) {
                System.err.println("Error al ejecutar el módulo FESTO JAR: " + e.getMessage());
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura la bandera de interrupción
                System.err.println("El hilo de ejecución se interrumpió: " + e.getMessage());
                e.printStackTrace();
            }
        });

        // Iniciar el hilo
        executionThread.start();

    }
}
