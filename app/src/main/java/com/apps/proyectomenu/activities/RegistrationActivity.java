package com.apps.proyectomenu.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.proyectomenu.R;

public class RegistrationActivity extends Activity implements View.OnClickListener {

    private EditText etName;
    private EditText etEmail;
    private EditText etPasswrd;
    private EditText etConfirmPasswrd;
    private CardView btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registration);
        findViewById();
    }

    private void findViewById(){
        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPasswrd = (EditText) findViewById(R.id.et_passwrd);
        etConfirmPasswrd = (EditText) findViewById(R.id.et_confirm_passwrd);
        btnContinue = (CardView) findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (verifyData()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    private boolean verifyData(){
        if (!etName.getText().toString().isEmpty() && !etEmail.getText().toString().isEmpty()
                && !etPasswrd.getText().toString().isEmpty() && !etConfirmPasswrd.getText().toString().isEmpty()){
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()){
                if (etPasswrd.getText().toString().length() == 8  && etConfirmPasswrd.getText().toString().length() == 8){
                    if (etPasswrd.getText().toString().equals(etConfirmPasswrd.getText().toString())){
                        return true;
                    }
                    else{
                        Toast.makeText(this,"LA CONTRASEÑA NO ES IGUAL, POR FAVOR VERIFICALA",Toast.LENGTH_LONG).show();
                        return false;
                    }
                }
                else {
                    Toast.makeText(this,"LA CONTRASEÑA DEBE SER DE 8 DIGITOS, POR FAVOR VERIFICALA",Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            else{
                Toast.makeText(this,"LOS DATOS INGRESADOS EN EL CAMPO CORREO ELECTRÓNICO NO CONRRESPONDEN A UN EMAIL, POR FAVOR VERIFICALOS",Toast.LENGTH_LONG).show();
                return false;
            }
        }
        else {
            Toast.makeText(this,"TODOS LOS CAMPOS SON OBLIGATORIOS, POR FAVOR VERIFICALOS",Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
