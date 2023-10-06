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


    @FXML Button mes4DDBBselectButton;

    @FXML Button exitButton;

    @FXML TextField mes4DDBBpath;

    @FXML
    private Label statusLabel;

    public void initialize() {
        this.install = Install.getInstance();
        if(install.isAppAlreadyRunning()){
            statusLabel.setText("OS: " +
                    install.getOsName() +
                    "\nMain directory: " +
                    install.getMainDirectory() +
                    "\nCommand: " +
                    install.getMvnwCommand());


        }else {
            System.out.println("Ya hay una instancia de la aplicación corriendo");
            System.exit(0);
        }



    }

    @FXML
    void onExitButtonClick(ActionEvent event) throws IOException {
       //install.detenerProceso();
        install.stopOlds("tg-0.0.1-SNAPSHOT.jar");
        install.stopOlds("tgsecurity-0.0.1-SNAPSHOT.jar");
        System.exit(0);
    }

    @FXML
    void runButtonAction(ActionEvent event) throws IOException {
        install.stopOlds("tgsecurity-0.0.1-SNAPSHOT.jar");
        install.stopOlds("tg-0.0.1-SNAPSHOT.jar");

        if (install.exec()){
            install.runTgfestoModule();
            statusLabel.setText("OS: " +
                    install.getOsName() +
                    "\nMain directory: " +
                    install.getMainDirectory() +
                    "\nCommand: " +
                    install.getMvnwCommand() +
                    "\ntg-security: En linea"  +
                    "\ntg-festo: En linea");
            install.navExE();

        } else {
            statusLabel.setText("OS: " +
                    install.getOsName() +
                    "\nMain directory: " +
                    install.getMainDirectory() +
                    "\nCommand: " +
                    install.getMvnwCommand() +
                    "\ntg-security: Falla"
            );


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
