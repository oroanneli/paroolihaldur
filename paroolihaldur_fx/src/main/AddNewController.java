package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class AddNewController {
    @FXML private TextField allikas;
    @FXML private TextField kasutajanimi;
    @FXML private TextField parool;
    @FXML private Label errorLabel;

    @FXML
    private void handleCreateNew(ActionEvent event) throws IOException {
        // kasutajal tuleb sisestada andmed:
        String allikasText = allikas.getText();
        String kasutajanimiText = kasutajanimi.getText();
        String paroolText = parool.getText();

        Kasutaja aktiivneKasutaja = Sessioon.getKasutajanimi();

        if (allikasText.isEmpty() || kasutajanimiText.isEmpty() || paroolText.isEmpty()) {
            errorLabel.setText("Täida kõik väljad!");
            return;
        }

        // sõnastik kasutaja paroolidega
        HashMap<String, ArrayList<String[]>> sonastik = aktiivneKasutaja.getSonastik();
        HashMapTootlus.lisa(sonastik, allikasText, kasutajanimiText, paroolText);
        // salvestab muudatused faili, juhuks, kui kasutaja programmi sulgeb
        FailiTootlus.salvestaParoolid(aktiivneKasutaja.getKasutajanimi(), sonastik);

        // tühjendame väljad
        allikas.clear();
        kasutajanimi.clear();
        parool.clear();
    }






}
