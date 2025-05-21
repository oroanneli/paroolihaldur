package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String kasutajanimi_siin = usernameField.getText();
        String parool = passwordField.getText();

        ArrayList<Kasutaja> kasutajad = FailiTootlus.loeKasutajad("meistrid.txt"); // loeb sisse olemasolevad kasutajad

        if (kontrolliKasutajaOlemas(kasutajad, kasutajanimi_siin)){
            if (kontrolliParool(kasutajad, kasutajanimi_siin, parool)){
            // Vaheta stseen SideBar.fxml peale
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SideBar.fxml"));
            Parent root = loader.load();

            MainController controllerM = loader.getController();
            Kasutaja leitud = null;
            for (Kasutaja kasutaja : kasutajad) {
                //System.out.println(kasutaja.getKasutajanimi());
                if (kasutaja.getKasutajanimi().equals(kasutajanimi_siin)) {
                    leitud = kasutaja;
                }
            }


            if (leitud != null){
                controllerM.setCurrentUserM(leitud);
                Sessioon.setKasutajanimi(leitud);
            } else {
                System.out.println("Ta kaotas kasutaja vahepeal ära");
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
            errorLabel.setText("Sellist kasutajat ei leitud");
        }
    }

    @FXML
    private void handleCreateAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/LooKasutaja.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        Main.primaryStage.setScene(scene);
        Main.primaryStage.setTitle("Loo uus kasutaja");
    }

    public static boolean kontrolliParool(ArrayList<Kasutaja> kasutajad, String kasutajanimi, String parool) { // returnib true, kui kasutajanimi ja parool klapivad
        for (Kasutaja kasutaja : kasutajad) {
            if (kasutaja.getKasutajanimi().equals(kasutajanimi) && kasutaja.getMaster_password().equals(parool)) {
                return true;
            }
        }
        return false;
    }

    public static boolean kontrolliKasutajaOlemas(ArrayList<Kasutaja> kasutajad, String kasutajanimi) { // returnib true kui selline kasutaja leidub
        for (Kasutaja kasutaja : kasutajad) {
            if (kasutaja.getKasutajanimi().equals(kasutajanimi)) {
                return true;
            }
        }
        return false;
    }
}