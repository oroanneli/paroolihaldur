package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddNewController {
    @FXML private TextField allikas;
    @FXML private TextField kasutajanimi;
    @FXML private TextField parool;
    private void handleCreateNew(ActionEvent event) throws IOException {
        String allikas = allikas.getText();
        String kasutajanimi = kasutajanimi.getText();
        String parool = parool.getText();

}
