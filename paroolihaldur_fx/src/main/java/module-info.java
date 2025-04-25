module com.example.paroolihaldur_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.paroolihaldur_fx to javafx.fxml;
    exports com.example.paroolihaldur_fx;
}