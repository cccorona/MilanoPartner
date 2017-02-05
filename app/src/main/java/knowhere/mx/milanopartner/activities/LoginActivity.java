package knowhere.mx.milanopartner.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import knowhere.mx.milanopartner.MainActivity;
import knowhere.mx.milanopartner.MilanoApp;
import knowhere.mx.milanopartner.R;
import knowhere.mx.milanopartner.dao.DataBaseScript;
import knowhere.mx.milanopartner.fragments.NoInternetFragment;
import knowhere.mx.milanopartner.interfaces.MilanoPartnerLoginInterface;
import knowhere.mx.milanopartner.netutils.NetUtil;
import knowhere.mx.milanopartner.pojo.MilanoPartner;
import knowhere.mx.milanopartner.pojo.MilanoState;
import knowhere.mx.milanopartner.pojo.MilanoUser;
import knowhere.mx.milanopartner.utils.MilanoEnums;
import knowhere.mx.milanopartner.utils.MilanoLogger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity{

    public static final String BASE_URL = "http://192.241.211.179:8000";
    public static final String TAG = LoginActivity.class.getSimpleName();
    public static final String HTTP_OK ="OK";

    private UserLoginTask loginTask = null;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mUserNameView;
    private View mProgressView;
    private Button loginButton;
    private Context context;
    private MilanoPartnerLoginInterface loginService;
    private Retrofit requestManager;
    private MilanoApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        context = this ;
        init();
        startListening();
        initRequestManager();


    }


    private void init(){
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mProgressView = findViewById(R.id.login_progress_bar);
        loginButton = (Button)findViewById(R.id.email_sign_in_button);
        mUserNameView= (EditText)findViewById(R.id.text_user_name);
        myApp = (MilanoApp) getApplicationContext();
    }

    private  void startListening(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    attemptLogin();
                }catch (Exception e){
                    MilanoLogger.getINSTANCE().escribeArchivo(TAG, "attemptLogin", e.getMessage());
                    //Show dialog
                }

            }
        });


    }

    private void initRequestManager(){

         requestManager = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginService = requestManager.create(MilanoPartnerLoginInterface.class);
    }


    private void attemptLogin() {

        mEmailView.setError(null);
        mPasswordView.setError(null);
        final String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        final String userName = mUserNameView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (TextUtils.isEmpty(userName)) {
            mUserNameView.setError(getString(R.string.error_field_required));
            focusView = mUserNameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return ;
        }if(!NetUtil.getINSTANCE().iHaveInternet(context)){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.no_internetFragmentView,new NoInternetFragment()).commit();
            return;
        }else {
            MilanoUser user= new MilanoUser(userName,email,password);
            Call<MilanoUser> loginCall = loginService.loginUser(user);
            showProgress(true);
            loginCall.enqueue(new Callback<MilanoUser>() {
                @Override
                public void onResponse(Call<MilanoUser> call, Response<MilanoUser> response) {
                    showProgress(false);
                    Toast.makeText(context,response.toString(),Toast.LENGTH_LONG).show();
                    MilanoPartner partner = new MilanoPartner();
                    MilanoUser user;
                    boolean loginSuccess = false;
                    if(response.code() == MilanoEnums.HttpCode.OK.getOpCode() && response.isSuccessful()){
                        user = response.body();
                        user.setEmail(email);
                        user.setUserName(userName);
                        partner.setUser(user);
                        MilanoPartner lastLoginParther = DataBaseScript.getINSTANCE(context).getMilanoPartner();
                           if(lastLoginParther == null){
                               partner.setUser(user);
                              if( DataBaseScript.getINSTANCE(context).addMilanoPartner(partner) > 0){
                                  myApp.setMilanoPartner(partner);
                                  loginSuccess = true ;
                              }

                           }else {
                               lastLoginParther.setUser(user);
                               if( DataBaseScript.getINSTANCE(context).updateMilanoPartner(partner) > 0){
                                   myApp.setMilanoPartner(partner);
                                   loginSuccess = true ;
                               }
                           }
                        if(loginSuccess){
                            myApp.getMilanoState().setFirstRun(MilanoState.FIRST_RUN_FALSE);
                            DataBaseScript.getINSTANCE(context).updateAppSetings(myApp.getMilanoState());
                            myApp.setMilanoState(DataBaseScript.getINSTANCE(context).getAppSettings());
                            Intent loginSuccesIntent = new Intent(context, MainActivity.class);
                            startActivity(loginSuccesIntent);
                            finish();

                        }
                    }else{
                        //something went wrong
                    }

                }

                @Override
                public void onFailure(Call<MilanoUser> call, Throwable t) {
                    showProgress(false);
                    MilanoLogger.getINSTANCE().escribeArchivo(TAG,"OnFailure",t.getMessage());
                    Toast.makeText(context,"checar datos error",Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }







    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress(true);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            //ConsumeWebService

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            loginTask = null;
            showProgress(false);
            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            loginTask = null;
            showProgress(false);
        }
    }

    public void showProgress(boolean showProgressBar){
        if(showProgressBar){
            mProgressView.setVisibility(View.VISIBLE);
        }else{
            mProgressView.setVisibility(View.GONE);
        }
    }
}

