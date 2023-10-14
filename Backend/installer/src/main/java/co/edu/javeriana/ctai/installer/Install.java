package co.edu.javeriana.ctai.installer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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

    private final Tools tools;

    // Constructor
    public Install() {
        // Detectar sistema operativo
        System.out.println("Sistema operativo: " + System.getProperty("os.name"));
        this.osName = System.getProperty("os.name").toLowerCase();

         this.tools = Tools.getInstance();

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
                return lockFileCreated.createNewFile();
            } catch (IOException e) {
                System.out.println("Fallo en crear el archivo de bloqueo");
                return false;
            }
        }
    }

    public void blockFileDelete(){
        // Eliminar el archivo de bloqueo
        Path lockFilePath = Paths.get(System.getProperty("user.home"), ".TG9.lock");
        File lockFile = lockFilePath.toFile();

        if (lockFile.exists()) {
            if (lockFile.delete()){
                System.out.println(".TG9.lock Eliminado");
            }
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
                    System.out.println("kill tgsecurity-0.0.1-SNAPSHOT.jar " + pid);
                }
                if (processName.equals("tg-0.0.1-SNAPSHOT.jar") || processName.equals("Process")) {
                    pidTgFesto = pid;
                    pidTgSecurity = pid;
                    stopProcesses();
                    System.out.println("kill tg-0.0.1-SNAPSHOT.jar " + pid);
                }
            }
        }
        reader.close();
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

    public void killFrond(){
        if (this.osName.contains("win")) {
            String exePath = this.mainDirectory.resolve("Frontend").resolve(".\\nginx.exe").toFile().toString();

            // Detener el proceso existente si está en ejecución
            tools.stopProcess(exePath);

            // Esperar un tiempo prudencial para que el proceso se cierre completamente
            try {
                Thread.sleep(5000); // Esperar 5 segundos (ajusta según tus necesidades)
            } catch (InterruptedException e) {
                System.out.println("O");
            }

            System.out.println("Proceso FronEnd Pausado");
        } else {
            System.out.println("No es Windows, no se puede reiniciar el proceso.");
        }
    }

    // Establecer la ruta de la base de datos MES
    public void setMes4DDBBpath(String mes4DDBBpath) {
        this.mes4DDBBpath = mes4DDBBpath;
    }

    // Ejecutar el módulo de FESTO
    public void runTgfestoModule() {

        // Extraer Ruta de modulo tg
        String festoModule = this.mainDirectory.resolve("Backend").resolve("CTAI").resolve("Festo").resolve("target").resolve("tg-0.0.1-SNAPSHOT.jar").toString();

        // Comando para ejecutar el módulo JAR
        String[] runComandSecurityModule = {"java", "-jar","-Dspring.profiles.active=dev", festoModule};

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
                System.out.println("Modulo FESTO. Encendido Codigo ");
                printProcess(process);

                // Esperar a que el proceso que ejecuta el modulo tg finalice
                int exitCode = process.waitFor();
                System.out.println("Modulo FESTO. DETENIDO Codigo: " + exitCode);

            } catch (IOException e) {
                System.err.println("Error al ejecutar el módulo FESTO JAR: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El hilo de ejecución se interrumpió: " + e.getMessage());
            }
        });

        // Iniciar el hilo que ejecuta procesos tg
        executionThread.start();
    }

    // Método para ejecutar el módulo de frontend
    public boolean frontExE(){

        if (this.osName.contains("win")) {
            String exePath = this.mainDirectory.resolve("nginx-1.25.2").resolve("nginx.exe").toString();

            // Detener el proceso existente si está en ejecución
            tools.stopProcess(exePath);

            // Esperar un tiempo prudencial para que el proceso se cierre completamente
            try {
                Thread.sleep(5000); // Esperar 5 segundos (ajusta según tus necesidades)
            } catch (InterruptedException e) {
                System.out.println("Falla al dormir 5ms el hilo");
            }

            // Iniciar el proceso nuevamente
            tools.startProcess(exePath);

            System.out.println("Proceso reiniciado");
            return true;
        } else {
            System.out.println("No es Windows, no se puede reiniciar el proceso.");
            return false;
        }


    }

    // Método para ejecutar el navegador
    public void navExE(){
        // Ejecutar Frondend
        if(!frontExE()){
            System.out.println("Error al ejecutar el Frondend.");
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
                    System.out.println("El navegador web no está soportado en este sistema.");
                }
            } else {
                System.out.println("La funcionalidad del escritorio no está soportada en este sistema.");
            }


        } catch (Exception e) {
            System.out.println("Shortcut de navegador Fallido");
        }

    }


    /**
     * WIFI:S:CP-F-CO-Javeriana-5GHz;T:WPA2;P:robotino;;
     * URL:https://localhost:4200
     */
    public void generateQRCode(String wifiSSID, String wifiPassword, String websiteURL, String outputPath) {
        try {
            int width = 300;
            int height = 300;

            // Configurar la información de la red Wi-Fi y la URL
            String wifiConfig = "WIFI:S:" + wifiSSID + ";T:WPA2;P:" + wifiPassword + ";;";
            String urlConfig = "URL:" + websiteURL;

            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            Writer writer = new QRCodeWriter();
            BitMatrix bitMatrix = writer.encode(wifiSSID.isEmpty() ? urlConfig : wifiConfig, BarcodeFormat.QR_CODE, width, height, hints);

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            File outputFile = new File(outputPath);
            ImageIO.write(image, "png", outputFile);

            System.out.println("Código QR generado con éxito en " + outputPath);
        } catch (Exception e) {
            System.out.println("Código QR no generado");
        }
    }

    public void generateQRImagesConcurrent(String wifiSSID, String wifiPassword, String websiteURL, String outputPath1, String outputPath2) {
        Thread thread1 = new Thread(() -> generateQRCode(wifiSSID, wifiPassword, "", outputPath1));
        Thread thread2 = new Thread(() -> generateQRCode("", "", websiteURL, outputPath2));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Falla en generacion QR");
        }
    }
}