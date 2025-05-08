package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main extends Application {
/*
    private BorderPane borderPane;

    @FXML
    public Button allItems, addNew, generate, logOut, shutDown;

 */

    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SideBar.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        /*
        // Load sidebar
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SideBar.fxml"));
        // Access center area
        borderPane = (BorderPane) ((AnchorPane) root).getChildren().get(0);
        VBox centerArea = (VBox) borderPane.getCenter();

        // Load accordion
        FXMLLoader accordionLoader = new FXMLLoader(getClass().getResource("/FXML/AccordionContent.fxml"));
        Parent accordionContent = accordionLoader.load();
        AccordionController controller = accordionLoader.getController();
        controller.setCurrentUser("külaline_5");

        // Add to center
        centerArea.getChildren().add(accordionContent);

        // Show stage
        Scene scene = new Scene(root);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

         */
    }

    public static void main(String[] args) {
        launch();
    }
}