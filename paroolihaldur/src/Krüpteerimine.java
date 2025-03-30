public class Krüpteerimine {
    /**
     * Krüpteerib ette antud parooli
     * @param parool parool mida soovitakse krüpteerida
     * @return tagastab krüpteeritud koodi
     */
    public static String krüpteeri(String parool){
        String kõik = "xYM@ghy[RTÖSF9~_^GjPk!#ufmcblA2WÄ1/)\"0zZ?(]HEns`BKöV.QO<õe6,4&r;NCvä{3|I*:Ü}8iL>%XJot7a'$ü-D5Upq\\Õwd";
        String krüpteeritud = "";
        char[] paroolCharArray = parool.toCharArray();

        for (char sümbol : paroolCharArray){ // liigutab iga elemendi vastavalt sõne jadale "kõik" vaates
            if (kõik.indexOf(sümbol) > 96){  // 3 väärtust EDASI, kui jõuab indeksini >96 alustab algusest
                krüpteeritud += kõik.charAt(kõik.indexOf(sümbol)-97);
            } else krüpteeritud += kõik.charAt(kõik.indexOf(sümbol)+3);
        }
        return krüpteeritud.toString(); // tagastab krüpteeritud parooli
    }

    /**
     * Dekrüpteerib failist loetud parooli
     * @param parool parool mida dekrüpteerida
     * @return tagastab dekrüptitud parooli
     */
    public static String dekrüpteeri(String parool){
        String kõik = "xYM@ghy[RTÖSF9~_^GjPk!#ufmcblA2WÄ1/)\"0zZ?(]HEns`BKöV.QO<õe6,4&r;NCvä{3|I*:Ü}8iL>%XJot7a'$ü-D5Upq\\Õwd";
        String dekrüpteeritud = "";
        char[] paroolCharArray = parool.toCharArray();

        for (char sümbol : paroolCharArray){ // liigutab iga elemendi vastavalt sõne jadale "kõik" vaates
            if (kõik.indexOf(sümbol) < 3){   // 3 väärtust TAGASI, kui jõuab indeksini <3 alustab lõpust
                dekrüpteeritud += kõik.charAt(kõik.indexOf(sümbol)+97);
            } else dekrüpteeritud += kõik.charAt(kõik.indexOf(sümbol)-3);
        }
        return dekrüpteeritud.toString();
    }
    /*
    public static void main(String[] args) {
        System.out.println(krüpteeri("karupoegpuhh"));
        System.out.println(dekrüpteeri(krüpteeri("karupoegpuhh")));
    }
    */
}
