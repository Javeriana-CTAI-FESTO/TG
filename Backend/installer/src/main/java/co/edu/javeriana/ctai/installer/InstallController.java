package co.edu.javeriana.ctai.installer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class InstallController {

    private Install install;


    @FXML
    Button mes4DDBBselectButton;

    @FXML
    Button exitButton;

    @FXML
    TextField mes4DDBBpath;

    @FXML
    private Label statusLabel;

    public void initialize() {

        //Singleton de la calse install
        this.install = Install.getInstance();

        // Verifica para no permitir dobles instancias.
        if (install.isAppAlreadyRunning()) {
            statusLabel.setText("OS: " +
                    install.getOsName() +
                    "\nMain directory: " +
                    install.getMainDirectory() +
                    "\nCommand: " +
                    install.getMvnwCommand());

        } else {
            //En caso que no sea posible levantar el servicio por que hay una instancia corriendo.
            System.out.println("Ya hay una instancia de la aplicación corriendo");
            System.exit(0);
        }


    }

    @FXML
    void onExitButtonClick(ActionEvent event) throws IOException {
        // Detiene procesos antiguos antes de la instalación
        try {
            //Elimina el archivo de bloqueo
            install.blockFileDelete();
            install.stopOlds("tgsecurity-0.0.1-SNAPSHOT.jar");
            install.stopOlds("tg-0.0.1-SNAPSHOT.jar");
            install.killFrond();
        } catch (IOException e) {
            System.err.println("Error al detener procesos antiguos: " + e.getMessage());
            return;
        }

        System.exit(0);
    }

    @FXML
    void runButtonAction(ActionEvent event) throws IOException {


        // Verifica si la aplicación ya se está ejecutando
        if (install.isAppAlreadyRunning()) {
            statusLabel.setText("OS: " +
                    install.getOsName() +
                    "\nMain directory: " +
                    install.getMainDirectory() +
                    "\nCommand: " +
                    install.getMvnwCommand() +
                    "\n La aplicación ya está en ejecución"
            );
            System.out.println("La aplicación ya está en ejecución.");
            return;
        } else {
            statusLabel.setText("OS: " +
                        install.getOsName() +
                        "\nMain directory: " +
                        install.getMainDirectory() +
                        "\nCommand: " +
                        install.getMvnwCommand() +
                        "\n Ejecutado ..."
            );



        }

        // Detiene procesos antiguos antes de la instalación
        try {
            install.stopOlds("tgsecurity-0.0.1-SNAPSHOT.jar");
            install.stopOlds("tg-0.0.1-SNAPSHOT.jar");
            install.killFrond();
        } catch (IOException e) {
            System.err.println("Error al detener procesos antiguos: " + e.getMessage());
            return;
        }

        // Realiza la instalación
        if (install.getMainDirectory().endsWith("TG")) {

            // Ejecuta el módulo de seguridad
            install.execSecurityModule();
            // Ejecuta el módulo de FESTO
            install.runTgfestoModule();
            // Abre el navegador web y genera códigos QR
            install.navExE();
            /**
             * WIFI:S:CP-F-CO-Javeriana-5GHz;T:WPA2;P:robotino;;
             * URL:https://localhost:4200
             */
            install.generateQRImagesConcurrent("CP-F-CO-Javeriana-5GHz","robotino",
                    "https://localhost:4200", "QR-Wifi.png", "QR-Url.png");

            statusLabel.setText("OS: " +
                    install.getOsName() +
                    "\nMain directory: " +
                    install.getMainDirectory() +
                    "\nCommand: " +
                    install.getMvnwCommand() +
                    "\nInstalación exitosa."
            );


            System.out.println("Instalación exitosa.");
        } else {

            statusLabel.setText("OS: " +
                    install.getOsName() +
                    "\nMain directory: " +
                    install.getMainDirectory() +
                    "\nCommand: " +
                    install.getMvnwCommand() +
                    "\nEjecutar dentro de la carpeta TG"
            );
            System.out.println("Ejecutar dentro de la carpeta Tg");
        }



    }



    @FXML
    void onMes4DDBBselectButtonClick(ActionEvent event) {

        // Muestra el diálogo y obtiene el directorio seleccionado
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo de base de datos");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Access Database", "*.accdb")
        );
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            install.setMes4DDBBpath(selectedFile.getAbsolutePath());
            mes4DDBBpath.setText(selectedFile.getAbsolutePath());

        }
    }
}
