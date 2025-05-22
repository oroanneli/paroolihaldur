package main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML private BorderPane mainBorderPane; // BorderPane kus peal asuvad menüüriba ja vaheaknad
    @FXML private Button allItems, addNew, generate, logOut, shutDown, genereeriParool; // nupud, mis seadistatakse selles klassis
    private Kasutaja kasutajaM; // sisse logitud kasutaja objekt. MainControlleri kasutajaisend
    @FXML private Spinner<Integer> GPPikkus; // genereeritud parooli pikkus
    @FXML private TextField genereeritudParool; // tekstiväli kuhu läheb genereeritud parool

    public void setCurrentUserM(Kasutaja kasutaja) throws IOException { // määrab mainControlleris kasutaja
        this.kasutajaM = kasutaja;
        loadAccordionView();
        //System.out.println(currentUser);
    }

    @FXML
    public void initialize() {
        //loadAccordionView(); // see kutsub kohe AccordionControlleri
        if (GPPikkus != null) { // ainult siis kui genereerimis lehel oleme
            GPPikkus.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 32, 12));
        }
    }

    // seadistab ülesse iga nupu funktsiooni ja laeb ülesse vahelehed
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
        } else if (event.getSource() == genereeriParool) { // genereerimise vahe akna funktsioon
            int pikkus = GPPikkus.getValue(); // loeb parooli pikkuse
            String parool = ParooliGeneraator.genereeriParool(pikkus); // kutsub välja genereeriparool funktsiooni
            genereeritudParool.setText(parool); // väljastab parooli kasutajale
        } else if (event.getSource() == shutDown) {
            Platform.exit();
        }
    }

    private void loadAccordionView() throws IOException { // laeb ülesse akkordioniga vaheakna kus on näha kõik paroolid
        FXMLLoader laeDisain = new FXMLLoader(getClass().getResource("/FXML/AccordionContent.fxml"));
        Parent view = laeDisain.load();
        AccordionController akordion = laeDisain.getController();
        //System.out.println(kasutajaM.getKasutajanimi());
        if (kasutajaM != null){
            akordion.setCurrentUserA(kasutajaM);
            mainBorderPane.setCenter(view);
        }
    }

    private void loadCenter(String fxmlPath) throws IOException { // laeb ülesse muud vaheaknad
        if (getClass().getResource(fxmlPath) == null){
            System.err.println("fxml not found");
            return;
        }
        Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
        mainBorderPane.setCenter(view);
    }

    private void handleSwitchButton() throws IOException { // vahetab stseeni login stseeni vastu
        Parent newRoot = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        Scene newScene = new Scene(newRoot);
        newScene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        Main.primaryStage.setScene(newScene);
        Main.primaryStage.setResizable(false);
        Main.primaryStage.setMaximized(false);
        Main.primaryStage.setWidth(600.0);
    }
}