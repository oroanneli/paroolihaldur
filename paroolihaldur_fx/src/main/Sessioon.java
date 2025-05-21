package main;
// vajalik klass, et addnew teaks, kes on kasutaja
public class Sessioon {
    private static Kasutaja kasutaja;

    public static Kasutaja getKasutajanimi() {
        return kasutaja;
    }

    public static void setKasutajanimi(Kasutaja nimi) {
        kasutaja = nimi;
    }
}