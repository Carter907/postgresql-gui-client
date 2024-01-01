module org.carte.dbmsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.sql;

    requires org.controlsfx.controls;

    opens org.carte.dbmsapp.controllers to javafx.fxml;
    opens org.carte.dbmsapp to javafx.fxml;
    exports org.carte.dbmsapp;
}