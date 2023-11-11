package co.edu.javeriana.ctai.installer.controller;

import co.edu.javeriana.ctai.installer.model.tools.Tools;
import co.edu.javeriana.ctai.installer.model.Install;
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

    private static final System.Logger LOGGER = System.getLogger(Tools.class.getName());

    private Install install;

    @FXML
    Button mes4DDBBselectButton;

    @FXML
    Button exitButton;

    @FXML
    Button runButton;

    @FXML
    TextField mes4DDBBpath;

    @FXML
    private Label statusLabel;


    public void initialize() {

        //Singleton de la calse install
        this.install = Install.getInstance();
        statusLabel.setText("OS: " +
                install.getOsName() +
                "\nMain directory: " +
                install.getMainDirectory() +
                "\nCommand: " +
                install.getMvnwCommand());
    }

    @FXML
    void onExitButtonClick(ActionEvent event) {
        this.exitButton.setDisable(true);
        this.runButton.setDisable(true);
        // Detiene procesos antiguos antes de la instalación
        try {

            install.stopOlds("tgsecurity-2.5.3-prod.jar");
            install.stopOlds("tg-2.7.16-prod.jar");
            install.killFrond();
            install.exitFronEndPID();

        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, e.getMessage());
            return;
        }
        install.getTools().getLoggingService().close();
        System.exit(0);
    }

    @FXML
    void runButtonAction(ActionEvent event) {
        // Desactivar boton de ejecución
        this.exitButton.setDisable(true);
        this.runButton.setDisable(true);

        statusLabel.setText("OS: " +
                install.getOsName() +
                "\nMain directory: " +
                install.getMainDirectory() +
                "\nCommand: " +
                install.getMvnwCommand() +
                "\n Ejecutado ..."
        );

        // Detiene procesos antiguos antes de la instalación
        try {

            install.stopOlds("tgsecurity-2.5.3-prod.jar");
            install.stopOlds("tg-2.7.16-prod.jar");
            install.killFrond();


        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR,"Error al detener procesos antiguos: "+ e.getMessage());
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
            install.getTools().generateQRImagesConcurrent("CP-F-CO-Javeriana-5GHz","robotino",
                    "https://localhost:4200", "QR-Wifi.png", "QR-Url.png");

            this.exitButton.setDisable(false);
            statusLabel.setText("OS: " +
                    install.getOsName() +
                    "\nMain directory: " +
                    install.getMainDirectory() +
                    "\nCommand: " +
                    install.getMvnwCommand() +
                    "\nInstalación exitosa."
            );

            LOGGER.log(System.Logger.Level.INFO, "Instalación exitosa.");


        } else {
            this.exitButton.setDisable(false);
            statusLabel.setText("OS: " +
                    install.getOsName() +
                    "\nMain directory: " +
                    install.getMainDirectory() +
                    "\nCommand: " +
                    install.getMvnwCommand() +
                    "\nEjecutar dentro de la carpeta TG"
            );
            LOGGER.log(System.Logger.Level.WARNING, "Ejecutar dentro de la carpeta Tg");


        }
        this.exitButton.setDisable(false);
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
