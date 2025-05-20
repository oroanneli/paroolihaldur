package main;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class AccordionController {
    @FXML private Accordion parooliAkordion;
    private Kasutaja kasutaja ; // Salvesatme kasutaja, kes hetkel on sisse logitud

    // Called automatically after FXML loading
    public void initialize() {
        //loadPasswordsForUser (kasutaja);
    }

    public void setCurrentUserA(Kasutaja sisestatud) {
        this.kasutaja = sisestatud;
        if (kasutaja != null){
            loadPasswordsForUser(kasutaja);  // Lae paroolid alles siis, kui kasutajanimi on teada
        } else {
            showEmptyMessage("Kasutaja pole sisse logitud");
        }

    }

    private void loadPasswordsForUser (Kasutaja kasutaja) {
        String nimi = kasutaja.getKasutajanimi();
        if (nimi == null || nimi.isEmpty()) {
            showEmptyMessage("Kasutaja pole sisse logitud");
            return;
        }
        uuendaAkordion(kasutaja.getSonastik());
    }

    private void uuendaAkordion(HashMap<String, ArrayList<String[]>> paroolid) {
        parooliAkordion.getPanes().clear(); // Clear existing content

        if (paroolid.isEmpty()) {
            showEmptyMessage("Paroole ei leitud");
        } else {
            paroolid.forEach((platform, credentialsList) -> {
                TitledPane pane = createPlatformPane(platform, credentialsList);
                parooliAkordion.getPanes().add(pane);
            });
        }
    }

    private TitledPane createPlatformPane(String platform, ArrayList<String[]> credentialsList) {
        TitledPane pane = new TitledPane();
        pane.setText(platform);
        pane.setCollapsible(true); // Allow collapsing

        VBox credentialsBox = new VBox(5); // 5px spacing between entries
        credentialsList.forEach(credentials -> {
            String user = credentials[0];
            String pass = credentials[1];
            Text userText = new Text(String.format("Username: %s", user));
            Text passText = new Text(String.format("Password: %s", pass));
            credentialsBox.getChildren().addAll(userText, passText);
        });

        pane.setContent(credentialsBox);
        pane.setExpanded(false); // Start collapsed

        // Bind the preferred height to the content
        pane.prefHeightProperty().bind(Bindings.when(pane.expandedProperty())
                .then(credentialsBox.prefHeightProperty().add(30)) // Add some height for the title
                .otherwise(30)); // Collapsed height

        return pane;
    }

    private void showEmptyMessage(String message) {
        TitledPane emptyPane = new TitledPane();
        emptyPane.setText(message);
        emptyPane.setCollapsible(false); // Can't collapse this message
        parooliAkordion.getPanes().add(emptyPane);
    }
}
