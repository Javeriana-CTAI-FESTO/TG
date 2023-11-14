module co.edu.javeriana.ctai.installer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires com.google.zxing;
    requires java.logging;

    opens co.edu.javeriana.ctai.installer to javafx.fxml;
    exports co.edu.javeriana.ctai.installer;
    exports co.edu.javeriana.ctai.installer.model;
    opens co.edu.javeriana.ctai.installer.model to javafx.fxml;
    exports co.edu.javeriana.ctai.installer.controller;
    opens co.edu.javeriana.ctai.installer.controller to javafx.fxml;
    exports co.edu.javeriana.ctai.installer.tools;
    opens co.edu.javeriana.ctai.installer.tools to javafx.fxml;
}