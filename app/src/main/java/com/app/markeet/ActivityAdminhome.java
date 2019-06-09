package com.app.markeet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.markeet.data.SharedPref;
import com.app.markeet.fragment.FragmentAdmin;
import com.app.markeet.fragment.FragmentHomeAdmin;
import com.app.markeet.fragment.FragmentProfilAdmin;

public class ActivityAdminhome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private SharedPref sharedPref;
    private Button logout;
    TextView txtAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        sharedPref = new SharedPref( this);
        //logout = (Button) findViewById(R.id.buttonLogout);
        /*logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.saveBoolean(sharedPref.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });*/

        loadFragment(new FragmentHomeAdmin());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationadmin);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_homeadmin:
                fragment = new FragmentHomeAdmin();
                break;

            case R.id.nav_umkmm:
                fragment = new FragmentAdmin();
                break;

            case R.id.nav_profilee:
                fragment = new FragmentProfilAdmin();
                break;
        }

        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
}
