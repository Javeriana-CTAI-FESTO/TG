module co.edu.javeriana.ctai.installer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens co.edu.javeriana.ctai.installer to javafx.fxml;
    exports co.edu.javeriana.ctai.installer;
}