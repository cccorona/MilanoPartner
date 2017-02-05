package knowhere.mx.milanopartner.widgetscoronado;

import android.app.ProgressDialog;
import android.content.Context;

import knowhere.mx.milanopartner.R;

/**
 * Created by cacorona on 26/09/2016.
 */
public class FullScreenProgressBar extends ProgressDialog {

    private static FullScreenProgressBar instance ;

    private FullScreenProgressBar(Context context) {
        super(context);
    }
    private FullScreenProgressBar(Context context, int style){
        super(context,style);
    }

    public static FullScreenProgressBar getInstance(Context context) {
        if(instance == null){
            instance = new FullScreenProgressBar(context, R.style.TrasnparentTheme);
            instance.setProgressStyle(STYLE_SPINNER);
            instance.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
        }
        return  instance ;
    }

    public void showProgress(){
        instance.show();
    }

    public void showProgress(int percent){
        instance.setProgress(percent);
    }

    public void hideProgress(){
        instance.hide();
    }

}
