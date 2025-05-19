package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage; // oota ma ei tea

    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        // lae kõigepealt login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        Parent root = loader.load();

        // Load the FXML file for the main layout
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SideBar.fxml"));
        //Parent root = loader.load();

        // Create a scene with the loaded root
        Scene scene = new Scene(root);

        // Link the CSS stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        // Set the scene to the stage and show it
        stage.setScene(scene);
        stage.setTitle("Paroolihaldur"); // Set a title for the window
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
