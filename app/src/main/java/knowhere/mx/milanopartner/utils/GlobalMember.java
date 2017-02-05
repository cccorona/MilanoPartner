package knowhere.mx.milanopartner.utils;


public class GlobalMember {

    public static boolean DEBUG = true;
    public static String TAG_EXTRA_BUNDDLE = "extra";
    private static GlobalMember INSTANCE;

    private GlobalMember() {
    }

    public static GlobalMember getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GlobalMember();
        }
        return INSTANCE;
    }


}
