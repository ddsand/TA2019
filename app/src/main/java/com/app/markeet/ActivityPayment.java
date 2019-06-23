package com.app.markeet;

import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.markeet.utils.Tools;

import org.w3c.dom.Text;

public class ActivityPayment extends AppCompatActivity {
    private TextView nomorva,totalharga;
    private ImageButton salin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initToolbar();
        final String billkey= getIntent().getStringExtra("bill_key");
        final String billcode= getIntent().getStringExtra("bill_code");
        //String status = getIntent().getStringExtra("status");
        //String time = getIntent().getStringExtra("time");
        String total = getIntent().getStringExtra("totalfees");

        nomorva = (TextView) findViewById(R.id.nomorva);
        totalharga = (TextView) findViewById(R.id.totalpay);
        salin = (ImageButton) findViewById(R.id.copyva);

        nomorva.setText(billkey); totalharga.setText(total);
        salin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.copyToClipboard(getApplicationContext(), billkey);
            }
        });

    }
    private void initToolbar() {
        ActionBar actionBar;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Payment Method");
        Tools.systemBarLolipop(this);
    }
}
