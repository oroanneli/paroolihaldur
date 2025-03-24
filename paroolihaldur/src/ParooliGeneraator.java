public class ParooliGeneraator extends Main{
    public static String genereeritudParool(int pikkus){
        String väiksedTähed = "abcdefghijklmnopqrstuvwxyzöäüõ";
         String suuredTähed = "ABCDEFGHIJKLMNOPQRSTUVWXYZÖÄÜÕ";
         String numbrid = "0123456789";
         String sümbolid = "^$*.[]{}()?-\\\"!@#%&/,><':;|_~`";
         String kõik = "abcdefghijklmnopqrstuvwxyzöäüõABCDEFGHIJKLMNOPQRSTUVWXYZÖÄÜÕ0123456789^$*.[]{}()?-\\\"!@#%&/,><':;|_~`";

        if (pikkus < 4){
            pikkus = 4;
        }
        char[] parool = new char[pikkus];

        parool[0] = väiksedTähed.charAt((int) (Math.random()*väiksedTähed.length()));
        parool[1] = suuredTähed.charAt((int) (Math.random()*suuredTähed.length()));
        parool[2] = numbrid.charAt((int) (Math.random()*numbrid.length()));
        parool[3] = sümbolid.charAt((int) (Math.random()*sümbolid.length()));

        for (int i = 4; i < pikkus; i++) {
            parool[i] = kõik.charAt((int) (Math.random()*kõik.length()));
        }

        for (int i = 0; i < parool.length; i++) {
            int suvalineIndeks = (int)  (Math.random()*(parool.length-1));
            char mälu = parool[i];
            parool[i] = parool[suvalineIndeks];
            parool[suvalineIndeks] = mälu;
        }

        return new String(parool);
    }
}