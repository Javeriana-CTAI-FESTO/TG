module co.edu.javeriana.ctai.installer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires com.google.zxing;

    opens co.edu.javeriana.ctai.installer to javafx.fxml;
    exports co.edu.javeriana.ctai.installer;
}