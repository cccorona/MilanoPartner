package knowhere.mx.milanopartner;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import knowhere.mx.milanopartner.pojo.MilanoPartner;
import knowhere.mx.milanopartner.pojo.MilanoState;

/**
 * Created by cacorona on 13/07/2016.
 */
public class MilanoApp extends Application {

    public static String TAG = MilanoApp.class.getSimpleName();

    private MilanoPartner milanoPartner;
    private MilanoState milanoState;
    private RequestQueue mRequestQueue;

    public MilanoPartner getMilanoPartner() {
        return milanoPartner;
    }

    public void setMilanoPartner(MilanoPartner milanoPartner) {
        this.milanoPartner = milanoPartner;
    }

    public MilanoState getMilanoState() {
        return milanoState;
    }

    public void setMilanoState(MilanoState milanoState) {
        this.milanoState = milanoState;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
