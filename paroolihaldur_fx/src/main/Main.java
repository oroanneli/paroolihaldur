package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/SideBar.fxml")));
        /**
        AnchorPane anchorPane = new AnchorPane();
        BorderPane borderPane = new BorderPane();


        VBox sideBar = new VBox(0);
        sideBar.setAlignment(Pos.CENTER);
        sideBar.setPrefWidth(236);
        sideBar.setPrefHeight(601);
        sideBar.setStyle("-fx-background-color: #050517;");

        Button profile = new Button("Profile");
        profile.setPrefHeight(42);
        profile.setPrefWidth(243);
        profile.setStyle("-fx-background-color: #efc88b;");
        sideBar.getChildren().add(0,profile);

        borderPane.setLeft(sideBar);
        anchorPane.getChildren().add(borderPane);
         **/
        Scene scene = new Scene(root, 1092, 601);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
