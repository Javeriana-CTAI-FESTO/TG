package co.edu.javeriana.ctai.installer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PowershellComandsEXE {

    public void comand(String comandoPowerShell){
        try {
            // Comando de ejemplo para ejecutar en PowerShell
            // = "Get-Process";

            // Ruta completa al ejecutable de PowerShell
            String rutaPowerShell = "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe";

            // Crear el proceso de PowerShell
            ProcessBuilder processBuilder = new ProcessBuilder(rutaPowerShell, "-Command", comandoPowerShell);

            // Redirigir la salida estándar y la salida de errores
            processBuilder.redirectErrorStream(true);

            // Iniciar el proceso de PowerShell
            Process procesoPowerShell = processBuilder.start();

            // Obtener la salida del proceso
            InputStream inputStream = procesoPowerShell.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            char[] buffer = new char[1024];
            int len;
            StringBuilder salida = new StringBuilder();

            while ((len = reader.read(buffer)) != -1) {
                salida.append(buffer, 0, len);
            }

            // Esperar a que el proceso termine
            int exitCode = procesoPowerShell.waitFor();

            // Imprimir la salida y el código de salida
            System.out.println("Salida del comando: ");
            System.out.println(salida.toString());
            System.out.println("Código de salida: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
