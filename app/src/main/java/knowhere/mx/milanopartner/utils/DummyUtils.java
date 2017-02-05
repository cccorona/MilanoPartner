package knowhere.mx.milanopartner.utils;

/**
 * Created by cacorona on 13/09/2016.
 */
public class DummyUtils {

    public static String TAG = DummyUtils.class.getSimpleName();

    private static DummyUtils INSTANCE;

    private DummyUtils(){

    }

    public  static DummyUtils getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DummyUtils();
        }
        return  INSTANCE;
    }




}
