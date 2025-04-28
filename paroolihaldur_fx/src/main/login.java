package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Group juur = new Group();

        // horisontaalselt kasutajanimi: [tekstiväli]
        Label kasutajanimiSilt = new Label("kasutajanimi: ");
        juur.getChildren().add(kasutajanimiSilt);
        TextField kasutajanimiTekstivali = new TextField();
        HBox kasutajanimiHBox = new HBox();
        kasutajanimiHBox.getChildren().addAll(kasutajanimiSilt, kasutajanimiTekstivali);
        kasutajanimiHBox.setSpacing(10);


        // horisontaalselt parool: [tekstiväli]
        Label paroolSilt = new Label("parool: ");
        juur.getChildren().add(paroolSilt);
        PasswordField paroolTekstivali = new PasswordField();
        HBox paroolHBox = new HBox();
        paroolHBox.getChildren().addAll(paroolSilt, paroolTekstivali);
        paroolHBox.setSpacing(10);




        Button logiSisse = new Button("login");
        logiSisse.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String kasutajanimi = kasutajanimiTekstivali.getText();
                String parool = paroolTekstivali.getText();
                ArrayList<Kasutaja> kasutajad = FailiTootlus.loeKasutajad("meistrid.txt"); // loeb sisse olemasolevad kasutajad

                if (kontrolliKasutajaOlemas(kasutajad, kasutajanimi)){
                    if (kontrolliParool(kasutajad, kasutajanimi, parool)){
                        System.out.println("Sisselogimine õnnestus!");
                    } else {
                        System.out.println("Vale parool!");
                    }
                } else {
                    System.out.println("Sellist kasutajat ei leitud!");
                }
            }
        });

        // paigutame kasutajanime rea ja parooli rea ja nupu üksteise alla
        VBox sisselogimineVBox = new VBox();
        sisselogimineVBox.getChildren().addAll(kasutajanimiHBox, paroolHBox, logiSisse);
        sisselogimineVBox.setSpacing(10);


        // lisame sisselogimise väljad juurele
        juur.getChildren().add(sisselogimineVBox);

        Scene loginStseen = new Scene(juur);
        primaryStage.setScene(loginStseen);
        primaryStage.show();
    }

    public static boolean kontrolliParool(ArrayList<Kasutaja> kasutajad, String kasutajanimi, String parool) { // returnib true, kui kasutajanimi ja parool klapivad
        for (Kasutaja kasutaja : kasutajad) {
            if (kasutaja.getKasutajanimi().equals(kasutajanimi) && kasutaja.getMaster_password().equals(parool)) {
                return true;
            }
        }
        return false;
    }

    public static boolean kontrolliKasutajaOlemas(ArrayList<Kasutaja> kasutajad, String kasutajanimi) { // returnib true kui selline kasutaja leidub
        for (Kasutaja kasutaja : kasutajad) {
            if (kasutaja.getKasutajanimi().equals(kasutajanimi)) {
                return true;
            }
        }
        return false;
    }
}
