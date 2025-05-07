package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main extends Application {
    public void start(Stage stage) throws IOException {
        // Load sidebar
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SideBar.fxml"));

        // Access center area
        BorderPane borderPane = (BorderPane) ((AnchorPane) root).getChildren().get(0);
        VBox centerArea = (VBox) borderPane.getCenter();

        // Load accordion
        FXMLLoader accordionLoader = new FXMLLoader(getClass().getResource("/FXML/AccordionContent.fxml"));
        Parent accordionContent = accordionLoader.load();
        AccordionController controller = accordionLoader.getController();
        controller.setCurrentUser("külaline_5");

        // Add to center
        centerArea.getChildren().add(accordionContent);

        // Show stage
        Scene scene = new Scene(root, 1092, 601);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}