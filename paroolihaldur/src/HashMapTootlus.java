import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HashMapTootlus {

    // võtab argumendiks kasutaja sõnastiku ja allika ning prindib välja kasutajanime ja parooli (vajadusel mitu tk)
    public static void kuva(HashMap<String, ArrayList<String[]>> sonastik, String allikas){
        ArrayList<String[]> vaartused = sonastik.get(allikas); // sõnastiku väärtused
        // System.out.println(Arrays.deepToString(vaartused.toArray()));

        for (int i = 0; i < vaartused.size(); i++) { // käib läbi listi ja kuvab kõik kasutajanimed ja paroolid
            String[] list = vaartused.get(i);
            System.out.println("Kasutajanimi: " + list[0] + ", Parool: " + list[1]);
        }
    }


    public static void main(String[] args) {
        // kasutaja sõnastik
        HashMap<String, ArrayList<String[]>> test = FailiTootlus.loeParoolid("kasutaja1");

        // kuvaTest
        // kuva(test, "YouTube");



    }
}
