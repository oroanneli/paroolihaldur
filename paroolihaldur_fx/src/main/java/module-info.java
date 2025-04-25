module com.example.paroolihaldur_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.paroolihaldur_fx to javafx.fxml;
    exports com.example.paroolihaldur_fx;
}