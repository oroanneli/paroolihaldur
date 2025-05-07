package main;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class AccordionController {
    @FXML private Accordion passwordAccordion;

    public void initializeData(HashMap<String, ArrayList<String[]>> userPasswordMap) {
        if (userPasswordMap.isEmpty()) {
            TitledPane emptyPane = new TitledPane();
            emptyPane.setText("No password data available");
            passwordAccordion.getPanes().add(emptyPane);
        } else {
            for (String platform : userPasswordMap.keySet()) {
                TitledPane pane = new TitledPane();
                pane.setText(platform);

                VBox credentialsBox = new VBox();
                ArrayList<String[]> credentialsList = userPasswordMap.get(platform);

                for (String[] credentials : credentialsList) {
                    String user = credentials[0];
                    String pass = credentials[1];
                    Text text = new Text("Username: " + user + " | Password: " + pass);
                    credentialsBox.getChildren().add(text);
                }

                pane.setContent(credentialsBox);
                passwordAccordion.getPanes().add(pane);
            }
        }
    }
}