package com.apps.proyectomenu.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.proyectomenu.R;
import com.apps.proyectomenu.activities.LoginActivity;
import com.apps.proyectomenu.activities.SplashActivity;

public class SyncFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout sync, changeName, assignStorage;
    private ProgressBar progressBar;
    private View viewRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.layout_sync, container, false);

        sync = (RelativeLayout) viewRoot.findViewById(R.id.tv_op1);
        changeName = (RelativeLayout) viewRoot.findViewById(R.id.tv_op2);
        assignStorage = (RelativeLayout) viewRoot.findViewById(R.id.tv_op3);
        progressBar = (ProgressBar) viewRoot.findViewById(R.id.progressBar);

        sync.setOnClickListener(this);
        changeName.setOnClickListener(this);
        assignStorage.setOnClickListener(this);

        return viewRoot;

    }


    @Override
    public void onClick(View v) {
        if (v == sync){
            progressBar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Toast.makeText(getContext(),"EL DISPOSITIVO SE A VINCULADO EXITOSAMENTE",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }, 5000);
        }
        else if (v == changeName){
            showAlert("INGRESA EL NOMBRE DEL DISPOSITIVO:");
        }
        else if (v == assignStorage){
            showAlert("ASIGNA EL ESPACIO DE ALMACENAMIENTO EN MB:");
        }
    }


    private void showAlert(final String title){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog);

        TextView txTitle = (TextView) dialog.findViewById(R.id.txtTitle);
        txTitle.setText(title);

        Button dialogButton = (Button) dialog.findViewById(R.id.btnAcept);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (title.equals("INGRESA EL NOMBRE DEL DISPOSITIVO:"))
                    Toast.makeText(getContext(),"SE HA CAMBIADO EL NOMBRE EXITOSAMENTE",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getContext(),"SE HA ASIGNADO EL ESPACIO DE ALMACENAMIENTO EXITOSAMENTE",Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }

}
