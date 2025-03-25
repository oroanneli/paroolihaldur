import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // loo list kasutajatega
        ArrayList<Kasutaja> kasutajad = FailiTootlus.loeKasutajad("meistrid.txt");

        login(kasutajad);
        System.out.println("Sisselogimine õnnestus!");
    }

    public static String login(ArrayList<Kasutaja> kasutajad) { // kui sisselogimine õnnestub, tagastatakse kasutajanimi
        Scanner sc = new Scanner(System.in); // Scanner avatuks kogu meetodi kestel
        String kasutajanimi;
        String parool;

        while (true) { //
            System.out.print("Sisesta kasutajanimi: ");
            kasutajanimi = sc.nextLine();

            if (!kontrolliKasutajaOlemas(kasutajad, kasutajanimi)) {
                System.out.println("Sellist kasutajat ei leitud. Proovi uuesti.");
                continue; // tagasi algusesse
            }

            System.out.print("Sisesta parool: ");
            parool = sc.nextLine();

            if (kontrolliParool(kasutajad, kasutajanimi, parool)) { // kui parool on õige siis lõpetame tsükli
                break;
            }
            else {
                System.out.println("Vale parool! Proovi uuesti.");
            }
        }

        return kasutajanimi;
    }

    public static boolean kontrolliParool(ArrayList<Kasutaja> kasutajad ,String kasutajanimi, String parool){
        for (Kasutaja kasutaja : kasutajad) {
            if (kasutaja.getKasutajanimi().equals(kasutajanimi) && kasutaja.getMaster_password().equals(parool)){
                return true;
            }
        }
        return false;
    }

    public static boolean kontrolliKasutajaOlemas(ArrayList<Kasutaja> kasutajad, String kasutajanimi){
        boolean leidub = false;
        for (Kasutaja kasutaja : kasutajad) {
            if(kasutaja.getKasutajanimi().equals(kasutajanimi)){
                leidub=true;
                break;
            }
        }
        if(!leidub){
            return false;
        }
        return true;
    }




}