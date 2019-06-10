package com.apps.proyectomenu.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.proyectomenu.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private EditText etUser;
    private EditText etPassword;
    private CardView btnInit;
    private TextView btnResgis;
    private GoogleApiClient apiClient;
    private SignInButton btnGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        findViewById();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("AIzaSyCffW8K-nYzMt8ZeD_YpRg0ZE_oRiHQRMg")
                .requestEmail().build();

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
    }

    private void findViewById(){
        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnInit = (CardView) findViewById(R.id.btnInit);
        btnResgis = (TextView) findViewById(R.id.btnRegis);
        btnGoogle = (SignInButton) findViewById(R.id.btnGoogle);
        btnGoogle.setSize(SignInButton.SIZE_WIDE);
        btnGoogle.setColorScheme(SignInButton.COLOR_DARK);

        btnInit.setOnClickListener(this);
        btnResgis.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        if (v == btnInit){
            if(verifyData()){
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
        else if (v == btnResgis){
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
        else if (v == btnGoogle){
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
            startActivityForResult(intent,111);
        }
    }

    private boolean verifyData(){
        if ((!etUser.getText().toString().isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(etUser.getText().toString()).matches()) &&
                (!etPassword.getText().toString().isEmpty() && etPassword.getText().toString().length() == 8)){
            return true;
        }
        else {
            Toast.makeText(this,"LOS DATOS SON INVALIDOS, POR FAVOR VERIFICALOS",Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 111){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        if (result.isSuccess()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else
            Toast.makeText(this,"No es posible iniciar sesión", Toast.LENGTH_LONG).show();

    }
}
