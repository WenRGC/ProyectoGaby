package com.apps.proyectomenu.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.apps.proyectomenu.Adapters.HistoryAdapter;
import com.apps.proyectomenu.Models.Backup;
import com.apps.proyectomenu.R;

import java.util.ArrayList;

public class ConfigurationFragment extends Fragment {
    private View viewRoot;
    private TextView tvNameDevice;
    private TextView tvStorage;
    private TextView tvTime;
    private Button btn_history;
    private RecyclerView rvHistory;
    private HistoryAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.layout_configurations, container, false);
        initDate();
        return viewRoot;

    }

    private void initDate(){
        tvNameDevice = (TextView) viewRoot.findViewById(R.id.tv_name_device);
        tvStorage = (TextView) viewRoot.findViewById(R.id.tv_storage);
        tvTime = (TextView) viewRoot.findViewById(R.id.tv_time);
        btn_history = (Button) viewRoot.findViewById(R.id.btn_history);
        rvHistory = (RecyclerView) viewRoot.findViewById(R.id.rv_history);

        tvNameDevice.setText("Dispositivo Gabriel");
        tvStorage.setText("8 GB");
        tvTime.setText("Cada viernes");
        showListHistory();
        rvHistory.setVisibility(View.GONE);

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rvHistory.getVisibility() == View.GONE)
                    rvHistory.setVisibility(View.VISIBLE);
                else
                    rvHistory.setVisibility(View.GONE);
            }
        });

    }

    private void showListHistory(){
        ArrayList<Backup> backup = new ArrayList<>();
        backup.add(new Backup("6to semestre", "20/05/2019"));
        backup.add(new Backup("Evidencias", "21/05/2019"));
        backup.add(new Backup("Proyecto", "22/05/2019"));
        backup.add(new Backup("Fotos mayo", "23/05/2019"));

        rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistoryAdapter(getContext(), backup);
        rvHistory.setAdapter(adapter);
    }
}
