import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Kasutaja> kasutajad = FailiTootlus.loeKasutajad("meistrid.txt"); // loeb sisse olemasolevad kasutajad
        Scanner sc = new Scanner(System.in); // Scanner kasutaja sisendi lugemiseks



        System.out.println("login - sisselogimine");
        System.out.println("loo - uue kasutaja loomine");
        while (true) {
            System.out.println("Valikute kuvamiseks 'abi'");
            System.out.print("-> ");
            String sisend = sc.nextLine(); // loe kasutaja sisend

            if (sisend.equals("login")) {
                login(kasutajad, sc);
            }
            else if (sisend.equals("logout")) {
                break; // Lõpetame programmi töö
            }
            else if (sisend.equals("loo")) {
                looKasutaja(kasutajad, sc);
            }
            else if (sisend.equals("abi")) {
                abi();
            }
            else {
                System.out.println("Tundmatu sisend.");
            }
        }

        sc.close(); // programmi lõppedes scanner suletakse
    }


    public static void abi() {
        System.out.println("Valige tegevus:");
        System.out.println("abi - näitab valikuid");
        System.out.println("login - sisselogimine / konto vahetamine");
        System.out.println("logout - sulgeb programmi");
        System.out.println("loo - uue kasutaja loomine");
        System.out.println("naita - kuvab allika kasutajanimed ja paroolid (vaja veel lisada)");
        System.out.println("koik - kuvab kõik salvestatud allikad, kasutajanimed, paroolid (vaja veel lisada)");
        System.out.println("lisa - uue parooli lisamine (vaja veel lisada)");
        System.out.println("kustuta - kustutab parooli (vaja veel lisada)");
        System.out.println("muudap - olemasoleva parooli muutmine (vaja veel lisada)");
    }

    public static String login(ArrayList<Kasutaja> kasutajad, Scanner sc) {
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

    public static boolean kontrolliParool(ArrayList<Kasutaja> kasutajad, String kasutajanimi, String parool) {
        for (Kasutaja kasutaja : kasutajad) {
            if (kasutaja.getKasutajanimi().equals(kasutajanimi) && kasutaja.getMaster_password().equals(parool)) {
                return true;
            }
        }
        return false;
    }

    public static boolean kontrolliKasutajaOlemas(ArrayList<Kasutaja> kasutajad, String kasutajanimi) {
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