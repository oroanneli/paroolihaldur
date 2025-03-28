public class ParooliGeneraator extends Main{
    /**
     * Genereerib soovitud pikkusele parooli milles esineb vähemalt:
     * 1 väike täht, 1 suur täht, 1 number ja 1 sümbol
     * @param pikkus parooli soovitud pikkus
     * @return tagastab vähemalt 4 elementi pika parooli
     */
    public static String genereeritudParool(int pikkus){
         String väiksedTähed = "abcdefghijklmnopqrstuvwxyzöäüõ";
         String suuredTähed = "ABCDEFGHIJKLMNOPQRSTUVWXYZÖÄÜÕ";
         String numbrid = "0123456789";
         String sümbolid = "^$*.[]{}()?-\"!@#%&/\\,><':;|_~`";
         String kõik = "abcdefghijklmnopqrstuvwxyzöäüõABCDEFGHIJKLMNOPQRSTUVWXYZÖÄÜÕ0123456789^$*.[]{}()?-\"!@#%&/\\,><':;|_~`";

        if (pikkus < 4){ // tagastab alati vähemalt parooli pikkusega 4, selleks et
            pikkus = 4;  // parool vastaks nõuetele: 1 väike täht, 1 suur täht, 1 number ja 1 sümbol
        }
        char[] parool = new char[pikkus]; // loob massivi kus iga element on parooli üks sümbol
        // täidab parooli nõuded
        parool[0] = väiksedTähed.charAt((int) (Math.random()*väiksedTähed.length()));
        parool[1] = suuredTähed.charAt((int) (Math.random()*suuredTähed.length()));
        parool[2] = numbrid.charAt((int) (Math.random()*numbrid.length()));
        parool[3] = sümbolid.charAt((int) (Math.random()*sümbolid.length()));

        for (int i = 4; i < pikkus; i++) { // lisab suvalisi sümboleid kuni parool vastab nõutud pikkusele
            parool[i] = kõik.charAt((int) (Math.random()*kõik.length()));
        }

        for (int i = 0; i < parool.length; i++) { // segab parooli elemendid omavahel
            int suvalineIndeks = (int)  (Math.random()*(parool.length-1));
            char mälu = parool[i];
            parool[i] = parool[suvalineIndeks];
            parool[suvalineIndeks] = mälu;
        }

        return new String(parool);
    }
}