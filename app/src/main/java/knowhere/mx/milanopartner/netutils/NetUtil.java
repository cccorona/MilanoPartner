package knowhere.mx.milanopartner.netutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by cacorona on 20/07/2016.
 */
public class NetUtil {

    public static String TAG = NetUtil.class.getSimpleName();
    private static NetUtil INSTANCE ;

    private NetUtil(){

    }

    public static NetUtil getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new NetUtil();
        }
        return INSTANCE;
    }


    public boolean iHaveInternet(Context context){
        boolean isConnected = false ;
        ConnectivityManager connectivityManager =
                 (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = networkInfo.isConnected();
        return isConnected;
    }

    public boolean iHaveInternetFromFragment(Object manager){
        boolean isConnected = false ;
        ConnectivityManager connectivityManager = (ConnectivityManager)manager ;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = networkInfo.isConnected();
        return isConnected;
    }

    public int getInternetType(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
       return networkInfo.getType();
    }

}
