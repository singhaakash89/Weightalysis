package com.app.weightalysis.oneui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.app.weightalysis.data_storage.SharedPreferenceManager;
import com.app.weightalysis.oneui.R;
import com.app.weightalysis.oneui.toast_manager.ToastManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Aakash Singh on 12-12-2016.
 */

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private static SharedPreferenceManager sharedPreferenceManager;
    public static String DISPLAY_NAME_KEY = "displayName";
    public static String EMAIL_KEY = "email";
    public static String IS_REGISTERED = "is_registered";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //**************cHANGINF STATUS BAR COLOR*******************
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));
//**********************************************************

        progressDialog = new ProgressDialog(this);
        // Configure sign-in to request the user's ID, email address, and basic profile. ID and
        // basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        setContentView(R.layout.activity_login);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                progressDialog.setMessage("Signing in...");
                progressDialog.setIndeterminate(true);
                progressDialog.show();
            }
        });
    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            progressDialog.dismiss();
            GoogleSignInAccount acct = result.getSignInAccount();
            String displayName = acct.getDisplayName();
            String email = acct.getEmail();
            sharedPreferenceManager = SharedPreferenceManager.getInstance();
            sharedPreferenceManager.putString(DISPLAY_NAME_KEY, displayName);
            sharedPreferenceManager.putString(EMAIL_KEY, email);
            sharedPreferenceManager.putBoolean(IS_REGISTERED, true);
            ToastManager.getInstance().showSimpleToastShort("Welcome " + displayName);
            startActivity(new Intent(this, BaseActivity.class));
            LoginActivity.this.finish();
        } else {
            sharedPreferenceManager = SharedPreferenceManager.getInstance();
            sharedPreferenceManager.putBoolean(IS_REGISTERED, false);
        }
    }
    // [END handleSignInResult]

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}