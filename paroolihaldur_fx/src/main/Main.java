package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static Stage primaryStage; // isendiväli selleks et saaks "stage"'i teistest klassidest muuta

    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setResizable(false);
        Main.primaryStage.setMinWidth(600);
        Main.primaryStage.setMinHeight(400);

        // lae kõigepealt login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // ühendab css'i stseenile
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Paroolihaldur");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
