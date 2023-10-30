package co.edu.javeriana.ctai.installer.model.tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Tools {

    private static final System.Logger LOGGER = System.getLogger(Tools.class.getName());

    // Atributo estático para almacenar la única instancia de la clase
    private static Tools instance;
    private LoggingService loggingService;

    // Constructor privado para evitar instanciación directa
    public Tools() {
        this.loggingService = new LoggingService();
    }


    public LoggingService getLoggingService() {
        return loggingService;
    }

    // Método para imprimir la salida de un proceso
    public void printProcess(Process p) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            LOGGER.log(System.Logger.Level.INFO, line);
        }
    }

    // Método para detener un proceso por su ruta
    public void stopProcess(String exePath) {
        String powerShellCommand = "Stop-Process -Path \"" + exePath + "\" -Force";

        String off="Stop-Process -Name nginx -Force";
        runPowerShellCommand(powerShellCommand);
        runPowerShellCommand(off);
    }

    // Método para iniciar un proceso por su ruta
    public void startProcess(String exePath) {
        String powerShellCommand = "Start-Process -FilePath \"" + exePath + "\"";
        runPowerShellCommand(powerShellCommand);
    }

    // Método para ejecutar comandos de PowerShell
    public void runPowerShellCommand(String command) {
        try {
            String rutaPowerShell = "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe";
            ProcessBuilder processBuilder = new ProcessBuilder(rutaPowerShell, "-Command", command);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();
        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, e.getMessage());
        }
        LOGGER.log(System.Logger.Level.INFO, "Comando PowerShell Ejecutado");
    }

    public void runCmdCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true);
            processBuilder.start();
        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, e.getMessage());
        }

        LOGGER.log(System.Logger.Level.INFO, "Comando CMD Ejecutado");
    }

    // Método para ejecutar el módulo de frontend
    public boolean frontExE(String osName, Path mainDirectory) {

        if (osName.contains("win")) {
            String exePath = mainDirectory.resolve("nginx-1.25.2").resolve("nginx.exe").toString();
            String exePath2 = mainDirectory.resolve("nginx-1.25.2").toAbsolutePath().toString();
            // Detener el proceso existente si está en ejecución
            //stopProcess(exePath);
            runCmdCommand("cd "+ exePath2 +" && nginx -s quit");

            // Esperar un tiempo prudencial para que el proceso se cierre completamente
            try {
                Thread.sleep(5000); // Esperar 5 segundos (ajusta según tus necesidades)
            } catch (InterruptedException e) {
                LOGGER.log(System.Logger.Level.ERROR, e.getMessage());
            }

            // Iniciar el proceso nuevamente
            //startProcess(exePath);
            runCmdCommand("cd "+ exePath2 +" && nginx");

            LOGGER.log(System.Logger.Level.INFO, "Proceso reiniciado");
            return true;
        } else {
            LOGGER.log(System.Logger.Level.WARNING, "No es Windows, no se puede reiniciar el proceso.");
            return false;
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

            LOGGER.log(System.Logger.Level.INFO, "Código QR generado con éxito en " + outputPath);
        } catch (Exception e) {

            LOGGER.log(System.Logger.Level.ERROR, e.getMessage());
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
            LOGGER.log(System.Logger.Level.ERROR, e.getMessage());
        }
    }

    public long frontendPID(Path mainDirectory) {
        long pid = 0; // Valor predeterminado en caso de error o si el archivo no contiene un PID válido

        //C:\Users\diana\OneDrive\Escritorio\TG\nginx-1.25.2\logs
        File nginx_PID_paht = mainDirectory.resolve("nginx-1.25.2").resolve("logs").resolve("nginx.pid").toFile();

        try {

            BufferedReader br = new BufferedReader(new FileReader(nginx_PID_paht));
            String linea;
            if ((linea = br.readLine()) != null) {
                pid = Long.parseLong(linea.trim());
            }
            br.close();

        } catch (IOException e) {

            LOGGER.log(System.Logger.Level.ERROR, e.getMessage());
        }

        return pid;
    }


}
