package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel; // kuvab errori teksti

    // sisselogimis nupu töö
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        // kasutaja sisestab väljadele andmed
        String kasutajanimi_siin = usernameField.getText();
        String parool = passwordField.getText();

        ArrayList<Kasutaja> kasutajad = FailiTootlus.loeKasutajad("meistrid.txt"); // loeb sisse olemasolevad kasutajad

        if (kontrolliKasutajaOlemas(kasutajad, kasutajanimi_siin)){
            if (kontrolliParool(kasutajad, kasutajanimi_siin, parool)){ // kui kasutaja on olemas ja parool on õige siis
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SideBar.fxml")); // vaheta stseen SideBar.fxml peale
            Parent root = loader.load();
            MainController controllerM = loader.getController(); // ütleb maincontrollerile kes on kasutaja
            // otsib kasutaja
            Kasutaja leitud = null;
            for (Kasutaja kasutaja : kasutajad) {
                if (kasutaja.getKasutajanimi().equals(kasutajanimi_siin)) {
                    leitud = kasutaja;
                }
            }
            if (leitud != null){
                controllerM.setCurrentUserM(leitud);
                Sessioon.setKasutajanimi(leitud);
            }

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
            Main.primaryStage.setResizable(true);
            Main.primaryStage.setScene(scene);
            Main.primaryStage.setTitle("Paroolihaldur");
            } else {
                errorLabel.setText("Vale kasutajanimi või parool!");
            }
        } else {
            errorLabel.setText("Sellist kasutajat ei leitud.");
        }
    }

    // uue kasutaja loomise nupu töö - näitab uut akent
    @FXML
    private void handleCreateAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/LooKasutaja.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        Main.primaryStage.setScene(scene);
        Main.primaryStage.setTitle("Loo uus kasutaja");
    }

    /**
     * Meetod, mis kontrollib, kas kasutajanimi ja parool klapivad
     * @param kasutajad sõnastik, kus on kõik kasutajad
     * @param kasutajanimi kasutaja sisestatud kasutajanimi
     * @param parool kasutaja sisestatud parool
     * @return true, kui klapivad, false kui vale parool
     */
    public static boolean kontrolliParool(ArrayList<Kasutaja> kasutajad, String kasutajanimi, String parool) {
        for (Kasutaja kasutaja : kasutajad) {
            if (kasutaja.getKasutajanimi().equals(kasutajanimi) && kasutaja.getMaster_password().equals(parool)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Meetod kontrollib, kas selline kasutajanimi on juba kasutuses
     * @param kasutajad kõikide kasutajate sõnastik
     * @param kasutajanimi kasutaja sisestatud kasutajanimi
     * @return true, kui selline kasutaja on olemas, false kui ei ole
     */
    public static boolean kontrolliKasutajaOlemas(ArrayList<Kasutaja> kasutajad, String kasutajanimi) {
        for (Kasutaja kasutaja : kasutajad) {
            if (kasutaja.getKasutajanimi().equals(kasutajanimi)) {
                return true;
            }
        }
        return false;
    }
}