package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML private BorderPane mainBorderPane;
    @FXML private VBox centerContainer;

    @FXML private Button allItems, addNew, generate, logOut, shutDown;

    private String currentUserM;

    public void setCurrentUserM(String kasutajanimi) throws IOException {
        this.currentUserM = kasutajanimi;
        loadAccordionView();
        //System.out.println(currentUser);
    }

    @FXML
    public void initialize() throws IOException {
        //loadAccordionView(); // see kutsub kohe AccordionControlleri
    }

    @FXML
    public void handleButtonClick(ActionEvent event) throws IOException {
        if (event.getSource() == allItems) {
            loadAccordionView();
        } else if (event.getSource() == addNew) {
            loadCenter("/FXML/Add-New.fxml");
        } else if (event.getSource() == generate) {
            loadCenter("/FXML/Generate.fxml");
        } else if (event.getSource() == logOut) {
            handleSwitchButton();
        }
    }

    private void loadAccordionView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AccordionContent.fxml"));
        Parent view = loader.load();
        AccordionController controllerA = loader.getController();
        controllerA.setCurrentUserA(this.currentUserM);

        mainBorderPane.setCenter(view);
    }

    private void loadCenter(String fxmlPath) throws IOException {
        if (getClass().getResource(fxmlPath) == null){
            System.err.println("fxml not found");
            return;
        }
        Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
        mainBorderPane.setCenter(view);
    }

    private void handleSwitchButton() throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        Scene newScene = new Scene(newRoot);
        newScene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        Main.primaryStage.setScene(newScene);
        Main.primaryStage.setMinWidth(500);
        Main.primaryStage.setMinHeight(300);
        Main.primaryStage.setResizable(false);
    }
}