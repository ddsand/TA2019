package com.app.markeet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

public class ActivityDetailTransfer extends AppCompatActivity {
    String jbayar,totalfees;
    TextView totpay;
    MaterialRippleLayout btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtransfer);
        jbayar = getIntent().getStringExtra("nominalbank");
        totalfees = getIntent().getStringExtra("totalfees");

        btnPay = (MaterialRippleLayout) findViewById(R.id.lyt_payy);
        totpay = (TextView) findViewById(R.id.total_pay);
        totpay.setText(totalfees);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityDetailTransfer.this,ActivityWaitingpay.class);
                i.putExtra("total_harga",jbayar);
                startActivity(i);
                finish();
            }
        });

    }
}
