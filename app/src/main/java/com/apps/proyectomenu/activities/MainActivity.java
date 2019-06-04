package com.apps.proyectomenu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apps.proyectomenu.R;
import com.apps.proyectomenu.view.ConfigurationFragment;
import com.apps.proyectomenu.view.NotifictionFragment;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout porcentage;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        porcentage = (RelativeLayout) findViewById(R.id.porcentage);
        porcentage.setVisibility(View.VISIBLE);

        container = (FrameLayout) findViewById(R.id.container_fragment);
        container.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        porcentage.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);

        switch (id){
            case R.id.action_notif:
                showFragmentAnimation(new NotifictionFragment(), getString(R.string.notif_title));
                break;
            case R.id.action_Config:
                showFragmentAnimation(new ConfigurationFragment(),getString(R.string.conf_title));
                break;
            case R.id.action_close:
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showFragmentAnimation(Fragment fragment, String title) {
        getSupportActionBar().setTitle(title);

        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!getSupportActionBar().getTitle().equals(getString(R.string.app_name))){
            getSupportActionBar().setTitle(getString(R.string.app_name));
            porcentage.setVisibility(View.VISIBLE);
            container.setVisibility(View.GONE);
        }
    }
}
