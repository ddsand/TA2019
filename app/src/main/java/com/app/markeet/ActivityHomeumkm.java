package com.app.markeet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.app.markeet.data.SharedPref;
import com.app.markeet.fragment.FragmentHomeAdmin;
import com.app.markeet.fragment.FragmentHomeumkm;
import com.app.markeet.fragment.FragmentProfilUMKM;
import com.app.markeet.fragment.FragmentTransaksi;

public class ActivityHomeumkm extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeumkm);

        sharedPref = new SharedPref( this);
        String iduser = sharedPref.getSPIdUser().toString();
        if(iduser != ""){
            loadFragment(new FragmentHomeumkm());
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationumkm);
            navigation.setOnNavigationItemSelectedListener(this);
        }else{
            startActivity(new Intent(ActivityHomeumkm.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_homeumkm:
                fragment = new FragmentHomeumkm();
                break;
            case R.id.nav_transaksi:
                fragment= new FragmentTransaksi();
                break;
            case R.id.nav_umkmprof:
                fragment=new FragmentProfilUMKM();
                break;
        }
        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_umkm, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

}
