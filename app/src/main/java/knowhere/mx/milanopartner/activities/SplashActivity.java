package knowhere.mx.milanopartner.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import knowhere.mx.milanopartner.MainActivity;
import knowhere.mx.milanopartner.MilanoApp;
import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.dao.DataBaseScript;
import knowhere.mx.milanopartner.pojo.MilanoPartner;
import knowhere.mx.milanopartner.pojo.MilanoState;
import knowhere.mx.milanopartner.pojo.MilanoUser;
import knowhere.mx.milanopartner.utils.GlobalMember;
import knowhere.mx.milanopartner.utils.MilanoLogger;

/**
 * Created by cacorona on 11/07/2016.
 */
public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final long SPLASH_SCREEN_DELAY = 5000;

    private Context context;
    private Intent redirectIntent;
    private MilanoState appState;
    private MilanoApp myApp;
    private MilanoPartner milanoPartner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            context = this;
            appState = DataBaseScript.getINSTANCE(context).getAppSettings();
            myApp = (MilanoApp) getApplicationContext();
            myApp.setMilanoState(appState);
            milanoPartner = DataBaseScript.getINSTANCE(context).getMilanoPartner();
            myApp.setMilanoPartner(milanoPartner);

        } catch (Exception e) {
            MilanoLogger.getINSTANCE().escribeArchivo(TAG, "onCreate", e.getMessage());
        }
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            // Activity was brought to front and not created,
            // Thus finishing this will get us to the last viewed activity
            finish();
            return;
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        Intent exit = getIntent();
        boolean ExitStatus = exit.getBooleanExtra("EXIT", false);

        if (ExitStatus) {

            finish();
        } // Fin if()

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Thread.currentThread()
                        .setName(this.getClass().getSimpleName() + ": " + Thread.currentThread().getName());


                    goToMainActivity();
                    finish();


               /* if (myApp.getMilanoState().getFirstRun() == MilanoState.FIRST_RUN_TRUE
                        ||myApp.getMilanoPartner() == null
                        || myApp.getMilanoPartner().getUser() == null
                        || ( myApp.getMilanoPartner().getUser() != null &&
                        myApp.getMilanoPartner().getUser().getIsLogged() == MilanoUser.USER_LOGED_FALSE)) {
                    gotoUnloggedActivity();
                } else {
                    goToMainActivity();
                }
                finish();*/
            }
        };

        // Simulate a Long Loading Process on Application Startup
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);



    } // Fin onCreate()

    private void gotoUnloggedActivity() {
        MilanoLogger.getINSTANCE().escribeArchivo(TAG, "gotoUnloggedActivity", TAG);
        redirectIntent = new Intent(context, UnLoggedActivity.class);
        startActivity(redirectIntent);
        finish();

    }

    private void goToMainActivity() {
        MilanoLogger.getINSTANCE().escribeArchivo(TAG, "goToMainActivity", TAG);
        redirectIntent = new Intent(context, MainActivity.class);
        startActivity(redirectIntent);
        finish();
    }

} // Fin SpashScreen.java