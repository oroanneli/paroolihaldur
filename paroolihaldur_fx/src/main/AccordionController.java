package main;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class AccordionController {
    @FXML private Accordion parooliAkordion;
    private Kasutaja kasutaja ; // Salvesatme kasutaja, kes hetkel on sisse logitud

    // FXML fail laeb üles automaatselt ka akordioni
    public void initialize() {
        //loadPasswordsForUser (kasutaja);
    }


    // seame kes on kasutaja
    public void setCurrentUserA(Kasutaja sisestatud) {
        this.kasutaja = sisestatud;
        if (kasutaja != null){
            loadPasswordsForUser(kasutaja);  // Lae paroolid alles siis, kui kasutajanimi on teada
        } else {
            valjasta_sonum("Kasutaja pole sisse logitud");
        }

    }


    // laeme kasutaja paroolid üles
    private void loadPasswordsForUser (Kasutaja kasutaja) {
        String nimi = kasutaja.getKasutajanimi();
        if (nimi == null || nimi.isEmpty()) {
            valjasta_sonum("Kasutaja pole sisse logitud");
            return;
        }
        uuendaAkordion(kasutaja.getSonastik());
    }


    // uuendab akordion
    private void uuendaAkordion(HashMap<String, ArrayList<String[]>> paroolid) {
        parooliAkordion.getPanes().clear(); // Tühjendame eelneva akordioni

        if (paroolid.isEmpty()) {
            valjasta_sonum("Paroole ei leitud");
        } else {
            paroolid.forEach((platform, credentialsList) -> {
                TitledPane pane = looPlatform(platform, credentialsList);
                parooliAkordion.getPanes().add(pane);
            });
        }
    }


    //kuvab akordioni päriselt ekraanile
    private TitledPane looPlatform(String platform, ArrayList<String[]> credentialsList) {
        TitledPane pane = new TitledPane();
        pane.setText(platform);
        pane.setCollapsible(true); // Voin uhe platfromi info sulgeda

        VBox andmeteKast = new VBox(5); // 5px vahed

        for (String[] credentials : credentialsList){
            String kasutaja1 = credentials[0];
            String parool1 = credentials[1];

            HBox sisendKast = new HBox(10); // Ümbris paroolidele/kasutajanimedele
            sisendKast.setAlignment(Pos.CENTER_LEFT);

            // kasutajanime ja parooli kirjed
            VBox tekstkast = new VBox(2);
            Text nimi = new Text(String.format("Kasutajanimi: %s", kasutaja1));
            Text parool = new Text(String.format("Parool: %s", parool1));
            tekstkast.getChildren().addAll(nimi, parool);

            // Muutmisnupp
            Button muuda = new Button("Muuda");
            muuda.setOnAction(e -> haldaMuuda(platform, kasutaja1, parool1));

            // Kustutamise nupp
            Button kustuta = new Button("Kustuta");
            kustuta.setOnAction(e -> haldaKustuta(platform, kasutaja1));

            sisendKast.getChildren().addAll(tekstkast, muuda, kustuta);
            andmeteKast.getChildren().add(sisendKast);
        }

        pane.setContent(andmeteKast);
        pane.setExpanded(false); // Akordioni avades on kõik väljad suletud

        // Laeme akordioni õige kõrgusega
        pane.prefHeightProperty().bind(Bindings.when(pane.expandedProperty())
                .then(andmeteKast.prefHeightProperty().add(30)) // Lisame ruumi tiitlile
                .otherwise(30)); // Kokkupandult kõrgus

        return pane;
    }


    // aitab errorite kirjeid väljastada
    private void valjasta_sonum(String message) {
        TitledPane tuhi = new TitledPane();
        tuhi.setText(message);
        tuhi.setCollapsible(false); // seda sonumit ei saa sulgeda
        parooliAkordion.getPanes().add(tuhi);
    }


    //tegeleb sellega, et kasutaja saaks oma paroole/kasutajaniemsid muuta
    private void haldaMuuda(String platform, String praeguneKasutajanimi, String praeguneParool) {
        // Loome doaloogi muutmise jaoks
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Muuda andmeid ");
        dialog.setHeaderText("Muuda platformi: " + platform +" andmeid");

        // Määrame nupu
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Loome kasutajanime ja parooli väljad
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField kasutajanimiVäli = new TextField(praeguneKasutajanimi);
        TextField paroolVäli = new TextField(praeguneParool);

        grid.add(new Label("Kasutajanimi: "), 0, 0);
        grid.add(kasutajanimiVäli, 1, 0);
        grid.add(new Label("Parool: "), 0, 1);
        grid.add(paroolVäli, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Keskendume default'ina kasutajanimeväljale
        Platform.runLater(kasutajanimiVäli::requestFocus);

        // Muudame sisestatud andmed kasutajanime ja parooli paariks, kui OK vajutatakse
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(kasutajanimiVäli.getText(), paroolVäli.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> tulem = dialog.showAndWait();

        tulem.ifPresent(uuedVaartused -> {
            // Uuendame parooli sõnastikus
            HashMapTootlus.muudak(kasutaja.getSonastik(), platform, praeguneKasutajanimi, uuedVaartused.getKey());
            HashMapTootlus.muudap(kasutaja.getSonastik(), platform, uuedVaartused.getKey(), uuedVaartused.getValue());

            // uuendame akordioni
            uuendaAkordion(kasutaja.getSonastik());

            //salvestame muudatused faili
            FailiTootlus.salvestaParoolid(kasutaja.getKasutajanimi(), kasutaja.getSonastik());

        });
    }


    //tegeleb sellega et kasutaja saaks oma kasutajanimesid/paroole kustutada
    private void haldaKustuta(String platform, String username) {
        // Kinnituse dialoog
        Alert teavitus = new Alert(Alert.AlertType.CONFIRMATION);
        teavitus.setTitle("Kustuta andmed");
        teavitus.setHeaderText("Kustuta " + platform + " andmeid");
        teavitus.setContentText("Kas oled kindel, et soovid kustutada kasutaja " + username + " andmed platformil " + platform + "?");

        Optional<ButtonType> tulem = teavitus.showAndWait();
        if (tulem.isPresent() && tulem.get() == ButtonType.OK) {
            // Eemaldame sonastikus
            HashMapTootlus.kustuta(kasutaja.getSonastik(), platform, username);

            // Uuendame akordioni
            uuendaAkordion(kasutaja.getSonastik());

            //salvestame muudatused faili
            FailiTootlus.salvestaParoolid(kasutaja.getKasutajanimi(), kasutaja.getSonastik());
        }
    }
}
