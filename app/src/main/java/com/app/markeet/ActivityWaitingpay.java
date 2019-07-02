package com.app.markeet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

public class ActivityWaitingpay extends AppCompatActivity {
    TextView jambayar,tanggalbayar,hargatotal;
    String jbayar,tglbayar,hrgtotal;
    MaterialRippleLayout okpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingpay);

        jbayar = getIntent().getStringExtra("total_harga");
        jambayar = (TextView) findViewById(R.id.jambayar);
        tanggalbayar = (TextView) findViewById(R.id.datepay);
        hargatotal = (TextView) findViewById(R.id.hargatotal);
        hargatotal.setText(jbayar);
        okpay = (MaterialRippleLayout) findViewById(R.id.lyt_okpay);

    }
}
