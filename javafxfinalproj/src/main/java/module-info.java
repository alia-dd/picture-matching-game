module com.example.javafxfinalproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxfinalproj to javafx.fxml;
    exports com.example.javafxfinalproj;
}