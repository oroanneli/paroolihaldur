package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;


public class AddNewController {
    @FXML private TextField allikas;
    @FXML private TextField kasutajanimi;
    @FXML private TextField parool;
    @FXML private Label errorLabel;

    @FXML
    private void handleCreateNew(ActionEvent event) throws TühiVäliErind {
        // kasutajal tuleb sisestada andmed:
        String allikasText = allikas.getText();
        String kasutajanimiText = kasutajanimi.getText();
        String paroolText = parool.getText();

        Kasutaja aktiivneKasutaja = Sessioon.getKasutajanimi();

        try { // kontrollib kas väljad on täidetud
            kasVäljadTäidetud(allikasText,kasutajanimiText,paroolText);
        } catch (Exception e){
            errorLabel.setText(e.getMessage());
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

    //abimeetod mis kontrollib kas väljad on täidetud ja tagastab erindi juhul kui ei ole täidetud
    private void kasVäljadTäidetud(String väli1, String väli2, String väli3) throws TühiVäliErind{
        if (väli1.isEmpty() || väli2.isEmpty() || väli3.isEmpty()) {
            throw new TühiVäliErind("Täida kõik väljad!");
        }
    }
}
