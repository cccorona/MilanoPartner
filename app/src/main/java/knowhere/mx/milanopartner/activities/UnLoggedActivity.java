package knowhere.mx.milanopartner.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import knowhere.mx.milanopartner.MainActivity;
import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.utils.GlobalMember;

/**
 * Created by cacorona on 11/07/2016.
 */
public class UnLoggedActivity extends Activity {

    private Button loginButton;
    private TextView linkaApp;
    private Context context;
    private String appStoreMarket = "market://details?id=";
    private String appStoreLink = "https://play.google.com/store/apps/details?id=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlogged_activity_layout);
        context  = this ;
        init();
        startListening();
    }

    public void init(){
        loginButton = (Button)findViewById(R.id.login_button);
        linkaApp = (TextView)findViewById(R.id.login_client_version);
    }

    public  void startListening(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GlobalMember.DEBUG){
                    Intent loginIntent = new Intent(context,MainActivity.class);
                    startActivity(loginIntent);
                }else{
                    Intent loginIntent = new Intent(context,LoginActivity.class);
                    startActivity(loginIntent);
                }

            }
        });

        linkaApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appStoreMarket + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appStoreLink + appPackageName)));
                }
            }
        });
    }
}
