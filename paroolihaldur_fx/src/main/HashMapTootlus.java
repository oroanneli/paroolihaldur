package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HashMapTootlus {

    // võtab argumendiks kasutaja sõnastiku ja allika ning prindib välja kasutajanime ja parooli (vajadusel mitu tk)
    public static void kuva(HashMap<String, ArrayList<String[]>> sonastik, String allikas){
        if (!sonastik.containsKey(allikas)){
            System.out.println("Sellist allikat ei leitud");
        }
        else{
            System.out.println(allikas);
            ArrayList<String[]> vaartused = sonastik.get(allikas); // sõnastiku väärtused

            for (int i = 0; i < vaartused.size(); i++) { // käib läbi listi ja kuvab kõik kasutajanimed ja paroolid
                String[] list = vaartused.get(i);
                System.out.println("Kasutajanimi: " + list[0] + ", Parool: " + list[1]);

            }
            System.out.println(); //tühi rida disaini eesmärgil
        }

    }

    // võtab argumendiks kasutaja sõnastiku ja väljastab kõik salvestatud kasutajanimed ja paroolid
    public static void kuvaKoik(HashMap<String, ArrayList<String[]>> sonastik){
        for (String voti : sonastik.keySet()){
            kuva(sonastik, voti);
        }
    }

    public static void lisa(HashMap<String, ArrayList<String[]>> sonastik, String allikas, String kasutajanimi, String parool){
        String[] väärtused = {kasutajanimi, parool};
        if (sonastik.containsKey(allikas)){ // kui see allikas juba varem olemas
            sonastik.get(allikas).add(väärtused);
        }
        else{ // kui see on esimene kord, kui sellele allikale parool salvestatakse
            ArrayList<String[]> koik_vaartused = new ArrayList<>();
            koik_vaartused.add(väärtused);
            sonastik.put(allikas, koik_vaartused);
        }

    }

    public static void kustuta(HashMap<String, ArrayList<String[]>> sonastik, String allikas, String kasutajanimi) {
        if (sonastik.containsKey(allikas)) {
            ArrayList<String[]> kasutajad = sonastik.get(allikas); // kõik vastava allika väärtused
            Iterator<String[]> iterator = kasutajad.iterator(); // et käia list läbi
            boolean leitud = false;

            while (iterator.hasNext()) {
                String[] paar = iterator.next();
                String kasutaja = paar[0];
                String parool = paar[1];

                if (kasutaja.equals(kasutajanimi)) { // oleme õige paari leidnud ning eemaldame selle nüüd
                    iterator.remove();
                    System.out.println("Edukalt kustutatud "+allikas+" kasutaja '"+ kasutaja+"' parool '"+parool+"'.");
                    leitud = true;
                    break;
                }
            }
            if (!leitud){ // juhul kui kogu sõnastik käidi läbi ja ei leitud sobivat paari
                System.out.println("Kasutajanime '" + kasutajanimi + "' ei leitud allika " + allikas + " paroolide hulgast. Palun kontrollige õigekirja.");
            }
            if (kasutajad.isEmpty()) { // kui kustutatud parool oli viimane, eemaldame kogu kirje sõnastikust
                sonastik.remove(allikas);
            }
        }
        else{ // sonastikus ei leidu sellist võtit
            System.out.println("Allikat '" + allikas + "' ei leitud. Palun kontrollige õigekirja.");
        }
    }

    public static void muudap(HashMap<String, ArrayList<String[]>> sonastik, String allikas, String kasutajanimi, String uusparool) {

        if (sonastik.containsKey(allikas)) {
            ArrayList<String[]> kasutajad = sonastik.get(allikas); // kõik vastava allika väärtused
            Iterator<String[]> iterator = kasutajad.iterator(); // et käia list läbi
            boolean leitud = false;

            while (iterator.hasNext()) {
                String[] paar = iterator.next();
                String kasutaja = paar[0];
                String parool = paar[1];

                if (kasutaja.equals(kasutajanimi)) { // oleme õige paari leidnud
                    paar[1]=uusparool;
                    System.out.println("Edukalt muudetud "+allikas+" kasutaja '"+ kasutaja+"' parool.");
                    leitud = true;
                    break;
                }
            }
            if (!leitud){ // juhul kui kogu sõnastik käidi läbi ja ei leitud sobivat paari
                System.out.println("Kasutajanime '" + kasutajanimi + "' ei leitud allika " + allikas + " paroolide hulgast. Palun kontrollige õigekirja.");
            }
        }
        else{ // sonastikus ei leidu sellist võtit
            System.out.println("Allikat '" + allikas + "' ei leitud. Palun kontrollige õigekirja.");
        }
    }

    public static void muudak(HashMap<String, ArrayList<String[]>> sonastik, String allikas, String kasutajanimi, String uuskasutajanimi) {

        if (sonastik.containsKey(allikas)) {
            ArrayList<String[]> kasutajad = sonastik.get(allikas); // kõik vastava allika väärtused
            Iterator<String[]> iterator = kasutajad.iterator(); // et käia list läbi
            boolean leitud = false;

            while (iterator.hasNext()) {
                String[] paar = iterator.next();
                String kasutaja = paar[0];

                if (kasutaja.equals(kasutajanimi)) { // oleme õige paari leidnud
                    String vanakas = paar[0];
                    paar[0]=uuskasutajanimi;
                    System.out.println("Edukalt muudetud allika " +allikas + " kasutajanimi '" + vanakas + "' -> '" + uuskasutajanimi + "'");
                    leitud = true;
                    break;
                }
            }
            if (!leitud){ // juhul kui kogu sõnastik käidi läbi ja ei leitud sobivat paari
                System.out.println("Kasutajanime '" + kasutajanimi + "' ei leitud allika " + allikas + " paroolide hulgast. Palun kontrollige õigekirja.");
            }
        }
        else{ // sonastikus ei leidu sellist võtit
            System.out.println("Allikat '" + allikas + "' ei leitud. Palun kontrollige õigekirja.");
        }
    }


//    public static void main(String[] args) {
//        // kasutaja sõnastik
//        //HashMap<String, ArrayList<String[]>> test = FailiTootlus.loeParoolid("kasutaja1");
//
//    }
}
