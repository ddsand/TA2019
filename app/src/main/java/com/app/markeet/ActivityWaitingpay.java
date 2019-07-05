package com.app.markeet;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.markeet.utils.Tools;
import com.balysv.materialripple.MaterialRippleLayout;

public class ActivityWaitingpay extends AppCompatActivity {
    TextView jambayar,tanggalbayar,hargatotal;
    String jbayar,tglbayar;
    MaterialRippleLayout okpay;
    ImageButton salin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingpay);

        jbayar = getIntent().getStringExtra("total_harga");
        jambayar = (TextView) findViewById(R.id.nomorrek);
        tanggalbayar = (TextView) findViewById(R.id.datepay);
        hargatotal = (TextView) findViewById(R.id.hargatotal);
        hargatotal.setText(jbayar+" IDR");
        tanggalbayar.setText(getIntent().getStringExtra("besok"));
        salin = (ImageButton) findViewById(R.id.copyrek);

        salin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.copyToClipboard(getApplicationContext(), jambayar.getText().toString());
            }
        });
        okpay = (MaterialRippleLayout) findViewById(R.id.lyt_okpay);
        okpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityWaitingpay.this,ActivityMain.class));
            }
        });
        initToolbar();
    }
    private void initToolbar() {
        ActionBar actionBar;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Process Payment");
        Tools.systemBarLolipop(this);
    }
}
