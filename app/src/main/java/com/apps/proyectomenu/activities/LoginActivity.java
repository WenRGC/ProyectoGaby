package com.apps.proyectomenu.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.proyectomenu.R;

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText etUser;
    private EditText etPassword;
    private CardView btnInit;
    private TextView btnResgis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        findViewById();
    }

    private void findViewById(){
        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnInit = (CardView) findViewById(R.id.btnInit);
        btnResgis = (TextView) findViewById(R.id.btnRegis);

        btnInit.setOnClickListener(this);
        btnResgis.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        if (v == btnInit){
            if(verifyData()){
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
        else if (v == btnResgis){
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
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

}
