package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class AddNewController {
    @FXML private TextField allikas;
    @FXML private TextField kasutajanimi;
    @FXML private TextField parool;



    @FXML
    private void handleCreateNew(ActionEvent event) throws IOException {
        String allikasText = allikas.getText();
        String kasutajanimiText = kasutajanimi.getText();
        String paroolText = parool.getText();

        if (allikasText.isEmpty() || kasutajanimiText.isEmpty() || paroolText.isEmpty()) {
            System.out.println("Täida kõik väljad!");
            return;
        }

        Kasutaja aktiivneKasutaja = Sessioon.getKasutajanimi();

        if (aktiivneKasutaja == null) {
            System.out.println("Kasutaja ei ole sisse logitud. EI OLE");
            return;
        }

        // Lae olemasolevad paroolid
        HashMap<String, ArrayList<String[]>> sonastik = aktiivneKasutaja.getSonastik();

        // Lisa uus kirje
        HashMapTootlus.lisa(sonastik, allikasText, kasutajanimiText, paroolText);

        // Salvesta uuesti
        FailiTootlus.salvestaParoolid(aktiivneKasutaja.getKasutajanimi(), sonastik);

        // Soovi korral kuva edukas sõnum või tühjenda väljad
        //System.out.println("Parool lisatud edukalt!");
        allikas.clear();
        kasutajanimi.clear();
        parool.clear();
    }






}
