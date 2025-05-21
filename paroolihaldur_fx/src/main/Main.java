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
        primaryStage.setResizable(false);
        Main.primaryStage.setMinWidth(600);
        Main.primaryStage.setMinHeight(400);

        // lae kõigepealt login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        Parent root = loader.load();

        // Create a scene with the loaded root
        Scene scene = new Scene(root);

        // Link the CSS stylesheet
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        // Set the scene to the stage and show it
        primaryStage.setScene(scene);
        primaryStage.setTitle("Paroolihaldur"); // Set a title for the window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
