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
    @FXML private Accordion passwordAccordion;
    private String currentUser ; // Store the logged-in username

    // Called automatically after FXML loading
    public void initialize() {
        loadPasswordsForUser (currentUser );
    }

    public void setCurrentUser (String username) {
        this.currentUser  = username;
        loadPasswordsForUser (username);
    }

    private void loadPasswordsForUser (String username) {
        if (username == null || username.isEmpty()) {
            showEmptyMessage("No user logged in");
            return;
        }

        HashMap<String, ArrayList<String[]>> userPasswordMap = FailiTootlus.loeParoolid(username);
        updateAccordion(userPasswordMap);
    }

    private void updateAccordion(HashMap<String, ArrayList<String[]>> data) {
        passwordAccordion.getPanes().clear(); // Clear existing content

        if (data.isEmpty()) {
            showEmptyMessage("No password data available");
        } else {
            data.forEach((platform, credentialsList) -> {
                TitledPane pane = createPlatformPane(platform, credentialsList);
                passwordAccordion.getPanes().add(pane);
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
        passwordAccordion.getPanes().add(emptyPane);
    }
}
