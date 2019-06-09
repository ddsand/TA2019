package com.app.markeet.umkm;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.ActivityCategoryDetails;
import com.app.markeet.ActivityProductDetails;
import com.app.markeet.ActivitySearch;
import com.app.markeet.ActivityShoppingCart;
import com.app.markeet.R;
import com.app.markeet.adapter.AdapterProduct;
import com.app.markeet.connection.callbacks.CallbackProduct;
import com.app.markeet.data.SharedPref;
import com.app.markeet.model.Category;
import com.app.markeet.model.Product;
import com.app.markeet.utils.Tools;

import java.util.ArrayList;

import retrofit2.Call;

public class ActivityUmkm extends AppCompatActivity {
    //private Category category;

    private Toolbar toolbar;
    private TextView username;
    private ActionBar actionBar;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umkm);
        sharedPref = new SharedPref(this);

        //parent_view = findViewById(android.R.id.content);
        //initComponent();
        username = (TextView) findViewById(R.id.usernameumkm);
        String idu=sharedPref.getSpUser().toString();
        username.setText(idu);
        initToolbar();
    }
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_umkm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        if(item_id == android.R.id.home){
            super.onBackPressed();
        } else if(item_id == R.id.action_add){
            Intent i = new Intent(this, ProductActivity.class);
            startActivity(i);
        }else if(item_id == R.id.action_transaction){
            Toast.makeText(this,"Transaction",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
