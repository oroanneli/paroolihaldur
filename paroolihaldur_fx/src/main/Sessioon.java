package main;
import main.Kasutaja;

public class Sessioon {
    private static Kasutaja kasutaja;

    public static Kasutaja getKasutajanimi() {
        return kasutaja;
    }

    public static void setKasutajanimi(Kasutaja nimi) {
        kasutaja = nimi;
    }
}