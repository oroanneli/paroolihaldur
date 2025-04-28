package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Kasutaja {
    private String kasutajanimi;
    private String master_password;
    private HashMap<String, ArrayList<String[]>> sonastik;

    public Kasutaja(String kasutajanimi, String master_password, HashMap<String, ArrayList<String[]>> sonastik) {
        this.kasutajanimi = kasutajanimi;
        this.master_password = master_password;
        this.sonastik = sonastik;
    }

    public String getKasutajanimi() {
        return kasutajanimi;
    }

    public void setKasutajanimi(String kasutajanimi) {
        this.kasutajanimi = kasutajanimi;
    }

    public String getMaster_password() {
        return master_password;
    }

    public void setMaster_password(String master_password) {
        this.master_password = master_password;
    }

    public HashMap<String, ArrayList<String[]>> getSonastik() {
        return sonastik;
    }

    public void setSonastik(HashMap<String, ArrayList<String[]>> sonastik) {
        this.sonastik = sonastik;
    }

    @Override
    public String toString() {
        return "Kasutaja{" +
                "kasutajanimi='" + kasutajanimi + '\'' +
                ", master_password='" + master_password + '\'' +
                '}';
    }
}
