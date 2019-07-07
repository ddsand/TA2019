package com.app.markeet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.markeet.data.SharedPref;
import com.app.markeet.fragment.FragmentAdmin;
import com.app.markeet.fragment.FragmentHomeAdmin;
import com.app.markeet.fragment.FragmentInProduct;
import com.app.markeet.fragment.FragmentListumkm;
import com.app.markeet.fragment.FragmentProfilAdmin;

public class ActivityAdminhome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private SharedPref sharedPref;
    private Button logout;
    TextView txtAdmin;
    private ActionBar actionBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        sharedPref = new SharedPref( this);
        String iduser = sharedPref.getSPIdUser().toString();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar();
        if(iduser != ""){
            loadFragment(new FragmentHomeAdmin());
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationadmin);
            navigation.setOnNavigationItemSelectedListener(this);
        }else{
            startActivity(new Intent(ActivityAdminhome.this, LoginActivity.class));
            finish();
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setTitle(R.string.app_name);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_homeadmin:
                actionBar.setTitle("Home");
                setSupportActionBar(toolbar);
                fragment = new FragmentHomeAdmin();
                break;
            case R.id.nav_umkmm:
                actionBar.setTitle("List UMKM");
                setSupportActionBar(toolbar);
                fragment = new FragmentListumkm();
                break;
            case R.id.nav_profilee:
                actionBar.setTitle("Profil");
                setSupportActionBar(toolbar);
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
