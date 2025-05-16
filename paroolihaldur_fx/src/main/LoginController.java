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
        String kasutajanimi = usernameField.getText();
        String parool = passwordField.getText();

        ArrayList<Kasutaja> kasutajad = FailiTootlus.loeKasutajad("meistrid.txt"); // loeb sisse olemasolevad kasutajad

        if (kontrolliKasutajaOlemas(kasutajad, kasutajanimi)){
            if (kontrolliParool(kasutajad, kasutajanimi, parool)){
            // Vaheta stseen SideBar.fxml peale
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SideBar.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            Main.primaryStage.setScene(scene);
            Main.primaryStage.setTitle("Paroolihaldur");
            } else {
                errorLabel.setText("Vale kasutajanimi või parool!");
            }
        } else {
            errorLabel.setText("Sellist kasutajat ei leitud");
        }
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