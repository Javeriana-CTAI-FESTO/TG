package co.edu.javeriana.ctai.installer;

import java.io.IOException;

public class Tools {

    // Atributo estático para almacenar la única instancia de la clase
    private static Tools instance;

    // Constructor privado para evitar instanciación directa
    private Tools() {
    }

    // Método estático para obtener la única instancia de la clase
    public static Tools getInstance() {
        if (instance == null) {
            instance = new Tools();
        }
        return instance;
    }

    // Método para detener un proceso por su ruta
    public void stopProcess(String exePath) {
        String powerShellCommand = "Stop-Process -Path \"" + exePath + "\" -Force";
        runPowerShellCommand(powerShellCommand);
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
            System.out.println("Error al ejecutar el comando de PowerShell: " + e.getMessage());
        }
    }
}
