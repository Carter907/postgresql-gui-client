module org.carte.dbmsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.sql;

    requires org.controlsfx.controls;
    requires atlantafx.base;
    requires com.google.gson;

    opens org.carte.dbmsapp.controllers to com.google.gson, javafx.fxml;
    opens org.carte.dbmsapp to javafx.fxml;
    exports org.carte.dbmsapp;
}