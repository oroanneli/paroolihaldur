import java.util.HashMap;
import java.io.*;
import java.util.*;


public class FailiTootlus {
    /**
     * Loeb platformid, kasutajanimed ja paroolid failist sõnastikku
     * @param kasutajanimi, kasutaja kes on hetkel sisselogitud ja soovib oma andmeid teada
     * @return tagastab sõnastiku kasutaja andmetest
     */
    public static HashMap<String, ArrayList<String[]>> loeParoolid(String kasutajanimi){
        String failinimi = kasutajanimi+".txt";
        HashMap<String, ArrayList<String[]>> sonastik = new HashMap<>();
        try (BufferedReader luger = new BufferedReader(new FileReader(failinimi))) {
            String rida;
            while ((rida = luger.readLine()) != null) {
                String[] osad = rida.split(" %¤% ");
                if (osad.length == 2) { // kuna failis on kirjed kujul. allikas - kasutajanimi, parool
                    String võti = osad[0].trim(); // Et eemaldada - märk
                    String[] väärtused = osad[1].split(" ; "); // lahutame üksteisest kasutajanime ja parooli listi.
                    for (int i = 0; i < väärtused.length; i++) {
                        väärtused[i] = väärtused[i];
                    }
                    // Kui kasutajal juba on selle allika kasutaja salvestatud siis lisame selle allika juurde ak teise allika
                    if (sonastik.containsKey(võti)){
                        sonastik.get(võti).add(väärtused);
                    } else{ // Kui see on esimene kord kus allikasse kasutaja loome lisame selle väärtuseks ühe listi milles on kasutajanimi ja parool eraldi listis.
                        ArrayList<String[]> koik_vaartused = new ArrayList<>();
                        koik_vaartused.add(väärtused);
                        sonastik.put(võti, koik_vaartused);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sonastik;
    }
    /**
     * Loeb platformid, kasutajanimed ja paroolid sõnastikust faili
     * @param kasutajanimi, kasutaja kes on hetkel sisselogitud ja soovib oma andmeid teada
     * @param sonastik kasutaja andmeid sisaldav sõnastik
     * midagi ei tagastata, lihtsalt luuakse/salvestatakse andmed faili
     */
    public static void salvestaParoolid(String kasutajanimi, HashMap<String, ArrayList<String[]>> sonastik) {
        String failinimi = kasutajanimi + ".txt";

        try (BufferedWriter kirjutaja = new BufferedWriter(new FileWriter(failinimi))) {
            // Käime sonastiku läbi
            for (Map.Entry<String, ArrayList<String[]>> sisend : sonastik.entrySet()) {
                String võti = sisend.getKey(); // Platformi nimi nt YouTube
                ArrayList<String[]> väärtusedList = sisend.getValue(); // Kõik paroolid_kasutajanimed

                // Käime üle paroolid - kasutajanimed
                for (String[] paar : väärtusedList) {
                    // Ühenda kasutajanimi ja parool
                    String kasutajaParool = paar[0] + " ; " + paar[1];
                    // Kirjutame järgmise formaadina: "platform %¤% kasutajanimi ; parool"
                    kirjutaja.write(võti + " %¤% " + kasutajaParool);
                    kirjutaja.newLine(); // iga uue kirje jaoks loome uue rea
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loeb kasutajanimed ja master-passwordid failist
     * @param failinimi, fail milles on kasutajate nimed ja peaparoolid
     * @return tagastab listi faili põhjal loodud kasutajatest
     */
    public static ArrayList<Kasutaja> loeKasutajad(String failinimi){
        ArrayList<Kasutaja> kasutajad = new ArrayList<>();
        try (BufferedReader luger = new BufferedReader(new FileReader(failinimi))) {
            String rida;
            while ((rida = luger.readLine()) != null) {
                //System.out.println(rida);
                String[] osad = rida.split(" %¤% ");
                String kasutajanimi = osad[0];
                String master_password = osad[1];
                HashMap<String, ArrayList<String[]>> sonastik = loeParoolid(kasutajanimi);
                Kasutaja vahekasutaja = new Kasutaja(kasutajanimi, master_password, sonastik);
                kasutajad.add(vahekasutaja);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kasutajad;
    }
    /**
     * Loeb kasutajanimed ja peaparoolid faili.
     * @param kasutajad, list hetkestest kasutajate olemitest
     * @param failinimi, fail kuhu salvestatakse kasutajanimed ja nende peaparoolid
     * Ei tagastata midagi. Salvestatakse fail
     */
    public static void salvestaKasutajad(ArrayList<Kasutaja> kasutajad, String failinimi){

        try (BufferedWriter kirjutaja = new BufferedWriter(new FileWriter(failinimi))) {
            //Käime kasutajate listi läbi
            for (Kasutaja kas : kasutajad) {
                // Ühenda kasutajanimi ja parool
                String kasutajanimi= kas.getKasutajanimi();
                String parool = kas.getMaster_password();
                // Kirjutame järgmise formaadina: "kasutajanimi %¤% parool"
                //System.out.println(kasutajanimi + " %¤% " + parool);
                kirjutaja.write(kasutajanimi + " %¤% " + parool);
                kirjutaja.newLine(); // iga uue kirje jaoks loome uue rea

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
