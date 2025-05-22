package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LooKasutajaController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField1;
    @FXML private PasswordField passwordField2;
    @FXML private Label errorLabel;

    // uue kasutaja lisamise nupu töö
    @FXML
    private void handleCreateAccount(ActionEvent event) throws TühiVäliErind, IOException {
        String kasutajanimi = usernameField.getText();
        String parool1 = passwordField1.getText();
        String parool2 = passwordField2.getText();
        // sõnastik, kus on kõik olemasolevad kasutajad
        ArrayList<Kasutaja> kasutajad = FailiTootlus.loeKasutajad("meistrid.txt");

        try { // kontrollib kas väljad on täidetud
            kasVäljadTäidetud(kasutajanimi,parool1,parool2);
        } catch (Exception e){
            errorLabel.setText(e.getMessage());
            return;
        }

        if (LoginController.kontrolliKasutajaOlemas(kasutajad, kasutajanimi)) {
            errorLabel.setText("Kasutajanimi on juba võetud!");
            return;
        }

        if (!parool1.equals(parool2)) {
            errorLabel.setText("Paroolid ei ühti!");
            return;
        }

        // kasutaja loomine
        HashMap<String, ArrayList<String[]>> sonastik = new HashMap<>();
        Kasutaja uusKasutaja = new Kasutaja(kasutajanimi, parool1, sonastik);
        kasutajad.add(uusKasutaja);

        FailiTootlus.salvestaParoolid(kasutajanimi, sonastik);
        FailiTootlus.salvestaKasutajad(kasutajad, "meistrid.txt");

        // Tagasi login lehele
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        Main.primaryStage.setScene(scene);
        Main.primaryStage.setTitle("Paroolihaldur");
    }

    @FXML
    private void handleTagasi(ActionEvent event) throws IOException{
        // Tagasi login lehele
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        Main.primaryStage.setScene(scene);
        Main.primaryStage.setTitle("Paroolihaldur");
    }

    // abimeetod, et kontrollida, kas väljad on kõik täidetud ja tagastab erindi kui ei ole
    private void kasVäljadTäidetud(String väli1, String väli2, String väli3) throws TühiVäliErind{
        if (väli1.isEmpty() || väli2.isEmpty() || väli3.isEmpty()) {
            throw new TühiVäliErind("Täida kõik väljad!");
        }
    }
}