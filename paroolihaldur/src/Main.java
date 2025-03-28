import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Kasutaja> kasutajad = FailiTootlus.loeKasutajad("meistrid.txt"); // loeb sisse olemasolevad kasutajad
        Scanner sc = new Scanner(System.in); // Scanner kasutaja sisendi lugemiseks
        HashMap<String, ArrayList<String[]>> sonastik = new HashMap<>();
        String kasutajanimi="";

        System.out.println("login - sisselogimine");
        System.out.println("loo - uue kasutaja loomine");
        while (true) {
            System.out.println("Valikute kuvamiseks 'abi'");
            System.out.print("-> ");
            String sisend = sc.nextLine(); // loe kasutaja sisend

            // basic funktsionaalsus
            if (sisend.equals("login")) {
                kasutajanimi=login(kasutajad, sc);
                if (kasutajanimi != null){
                    sonastik = FailiTootlus.loeParoolid(kasutajanimi);
                }
            }
            else if (sisend.equals("logout")) {
                FailiTootlus.salvestaParoolid(kasutajanimi, sonastik);
                break; // Lõpetame programmi töö
            }
            else if (sisend.equals("loo")) {
                looKasutaja(kasutajad, sc);
            }
            else if (sisend.equals("abi")) {
                abi();
            }
            else if (sisend.equals("s")){
                FailiTootlus.salvestaParoolid(kasutajanimi, sonastik);
            }


            // need sõnastiku töötlemise valikud:
            else if (sisend.equals("koik")){
                HashMapTootlus.kuvaKoik(sonastik);
            }
            else if (sisend.contains("naita")){
                String allikas;
                if (sisend.equals("naita")) {
                    System.out.print("sisesta allikas, mille parooli soovid kuvada: ");
                    allikas = sc.nextLine();
                }
                else{
                    allikas = sisend.split(" ")[1];
                }
                HashMapTootlus.kuva(sonastik, allikas);
            }
            else if (sisend.equals("muudap")){
                System.out.print("Allikas: ");
                String allikas = sc.nextLine();
                System.out.print("Kasutajanimi: ");
                String kas_nim = sc.nextLine();
                System.out.print("Uus parool: ");
                String uus_parool = sc.nextLine();
                HashMapTootlus.muudap(sonastik, allikas, kas_nim, uus_parool);
            }
            else if (sisend.equals("muudak")){
                System.out.print("Allikas: ");
                String allikas = sc.nextLine();
                System.out.print("Kasutajanimi: ");
                String kas_nim = sc.nextLine();
                System.out.print("Uus kasutajanimi: ");
                String uus_kasutaja = sc.nextLine();
                HashMapTootlus.muudak(sonastik, allikas, kas_nim, uus_kasutaja);
            }
            else if (sisend.equals("lisa")){
                System.out.print("Allikas: ");
                String allikas = sc.nextLine();
                System.out.print("Kasutajanimi: ");
                String kas_nim = sc.nextLine();
                System.out.print("Parool: ");
                String parool = sc.nextLine();
                HashMapTootlus.lisa(sonastik, allikas, kas_nim, parool);
            }
            else if (sisend.equals("kustuta")){
                System.out.print("Allikas: ");
                String allikas = sc.nextLine();
                System.out.print("Kasutajanimi: ");
                String kas_nim = sc.nextLine();
                HashMapTootlus.kustuta(sonastik, allikas, kas_nim);
            }


            // kui kasutaja teeb vea
            else {
                System.out.println("Tundmatu sisend.");
            }
        }

        sc.close(); // programmi lõppedes scanner suletakse
    }


    public static void abi() { // kasutaja valikud
        System.out.println("Valige tegevus:");
        System.out.println("abi - näitab valikuid");
        System.out.println("login - sisselogimine / konto vahetamine");
        System.out.println("logout - sulgeb programmi");
        System.out.println("loo - uue kasutaja loomine");
        System.out.println("s - muudatuste salvestamiseks");
        System.out.println("naita + (allikas) - kuvab allika kasutajanimed ja paroolid");
        System.out.println("koik - kuvab kõik salvestatud allikad, kasutajanimed, paroolid");
        System.out.println("lisa - uue parooli lisamine");
        System.out.println("kustuta - kustutab parooli");
        System.out.println("muudap - olemasoleva parooli muutmine");
        System.out.println("muudak - olemasoleva kasutajanime muutmine");
    }

    public static String login(ArrayList<Kasutaja> kasutajad, Scanner sc) { // sisselogimine
        String kasutajanimi;
        String parool;

        while (true) {
            System.out.print("Sisesta kasutajanimi: ");
            kasutajanimi = sc.nextLine();

            if (!kontrolliKasutajaOlemas(kasutajad, kasutajanimi)) {
                System.out.println("Sellist kasutajat ei leitud. Uue kasutaja loomiseks 'loo'");
                return null;
            }

            System.out.print("Sisestage peaparool: ");
            parool = sc.nextLine();

            if (kontrolliParool(kasutajad, kasutajanimi, parool)) {
                System.out.println("Sisselogimine õnnestus!");
                return kasutajanimi;
            }
            else {
                System.out.println("Vale parool! Proovige uuesti.");
            }

        }
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

    public static void looKasutaja(ArrayList<Kasutaja> kasutajad, Scanner sc) {
        System.out.print("Looge kasutajanimi: ");
        String kasutajanimi = sc.nextLine();

        if (kontrolliKasutajaOlemas(kasutajad, kasutajanimi)) { // Juba eksisteeriva kasutajanimega ei saa uut kasutajat luua
            System.out.println("Kasutaja juba eksisteerib.");
            return;
        }

        System.out.print("Looge peaparool: "); // Parooli loomisel tuleb peaparool sisestada kontrolliks mitu korda
        String parool1 = sc.nextLine();
        System.out.print("Sisestage peaparool uuesti: ");
        String parool2 = sc.nextLine();

        if (!parool1.equals(parool2)) {
            System.out.println("Kasutaja loomine ebaõnnestus, sisestati erinevad paroolid.");
            return;
        }

        // Uue kasutaja loomine
        HashMap<String, ArrayList<String[]>> sonastik = new HashMap<>();
        Kasutaja kasutaja = new Kasutaja(kasutajanimi, parool1, sonastik);
        kasutajad.add(kasutaja);
        FailiTootlus.salvestaParoolid(kasutajanimi, sonastik);
        FailiTootlus.salvestaKasutajad(kasutajad, "meistrid.txt");

        System.out.println("Kasutaja loomine õnnestus!");
    }
}