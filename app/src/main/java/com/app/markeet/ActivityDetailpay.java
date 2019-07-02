package com.app.markeet;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.adapter.AdapterPayment;
import com.app.markeet.data.SharedPref;
import com.app.markeet.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetailpay extends AppCompatActivity {
    RecyclerView recyclerView;
//    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<String> mImageUrls = new ArrayList<>();
//    private ArrayList<String> mDesc = new ArrayList<>();
    private LinearLayout paymentone,paymenttwo,paymentmandiri;
    private TextView saldotext;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpay);
        sharedPref = new SharedPref(this);
        initToolbar();
        initPayment();

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
    private void initPayment(){
        paymentone = (LinearLayout) findViewById(R.id.payment_bank);
        paymenttwo = (LinearLayout) findViewById(R.id.payment_ez);
        paymentmandiri =(LinearLayout) findViewById(R.id.pay_mandiri);

        String saldo = sharedPref.getSaldo().toString();
        saldotext = (TextView) findViewById(R.id.saldopayment);
        saldotext.setText("Balance : IDR. "+saldo);

        paymentone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivityDetailpay.this, "Bank", Toast.LENGTH_SHORT).show();
                sharedPref.saveString(sharedPref.PAYMENT_METHOD,"Mandiri Virtual Account");
                Intent i = new Intent(ActivityDetailpay.this, ActivityCheckout.class);
                startActivity(i);
                finish();
            }
        });
        paymenttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivityDetailpay.this, "Ezpy", Toast.LENGTH_SHORT).show();
                sharedPref.saveString(sharedPref.PAYMENT_METHOD,"Ezpy Balance");
                Intent i = new Intent(ActivityDetailpay.this, ActivityCheckout.class);
                startActivity(i);
                finish();
            }
        });
        paymentmandiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivityDetailpay.this, "Mand", Toast.LENGTH_SHORT).show();
                sharedPref.saveString(sharedPref.PAYMENT_METHOD,"Mandiri Transfer");
                Intent i = new Intent(ActivityDetailpay.this, ActivityCheckout.class);
                startActivity(i);
                finish();
            }
        });
    }
}
