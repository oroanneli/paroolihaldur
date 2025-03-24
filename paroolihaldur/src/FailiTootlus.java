import java.util.HashMap;
import java.io.*;
import java.util.*;

public class FailiTootlus {
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

    public static ArrayList<Kasutaja> loeKasutajad(String failinimi){
        ArrayList<Kasutaja> kasutajad = new ArrayList<>();
        try (BufferedReader luger = new BufferedReader(new FileReader(failinimi))) {
            String rida;
            while ((rida = luger.readLine()) != null) {
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

    public static void salvestaKasutajad(ArrayList<Kasutaja> kasutajad, String failinimi){

        try (BufferedWriter kirjutaja = new BufferedWriter(new FileWriter(failinimi))) {
            //Käime kasutajate listi läbi
            for (Kasutaja kas : kasutajad) {
                // Ühenda kasutajanimi ja parool
                String kasutajanimi= kas.getKasutajanimi();
                String parool = kas.getMaster_password();
                // Kirjutame järgmise formaadina: "kasutajanimi %¤% parool"
                kirjutaja.write(kasutajanimi + " %¤% " + parool);
                kirjutaja.newLine(); // iga uue kirje jaoks loome uue rea

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
