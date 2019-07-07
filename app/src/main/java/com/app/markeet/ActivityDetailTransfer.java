package com.app.markeet;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.utils.CallbackDialog;
import com.app.markeet.utils.DialogUtils;
import com.app.markeet.utils.Tools;
import com.balysv.materialripple.MaterialRippleLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailTransfer extends AppCompatActivity {
    String jbayar,totalfees,kodetransaksi;
    TextView totpay;
    MaterialRippleLayout btnPay;
    EditText accNumber,accName;
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtransfer);
        initComponent();
        initToolbar();
    }

    private void initToolbar() {
        ActionBar actionBar;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Detail Transfer");
        Tools.systemBarLolipop(this);
    }
    public void initComponent(){
        progressDialog = new ProgressDialog(ActivityDetailTransfer.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(R.string.title_please_wait);
        progressDialog.setMessage("Submit your payment....");

        jbayar = getIntent().getStringExtra("nominalbank");
        totalfees = getIntent().getStringExtra("totalfees");
        kodetransaksi = getIntent().getStringExtra("kodetransaksi");

        accNumber = (EditText) findViewById(R.id.nomorrekening);
        accName = (EditText) findViewById(R.id.pemelikrekening);

        btnPay = (MaterialRippleLayout) findViewById(R.id.lyt_payy);
        totpay = (TextView) findViewById(R.id.total_pay);
        totpay.setText(totalfees);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                ManualPay(kodetransaksi,jbayar,accNumber.getText().toString(),accName.getText().toString());
            }
        });
    }
    public void ManualPay(String transaksi,String jumlahharga,String akun,String namaakun){
        API api = RestAdapter.createAPI();
        api.ManualPayment(transaksi,jumlahharga,akun,namaakun).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                if(response!=null){
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String besok = jsonObject.getString("tomorrow");
                        //Toast.makeText(ActivityDetailTransfer.this, "rekening "+accNumber.getText().toString(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ActivityDetailTransfer.this,ActivityWaitingpay.class);
                        i.putExtra("total_harga",jbayar);
                        i.putExtra("nomorrekening",accNumber.getText().toString());
                        i.putExtra("namarekening",accName.getText().toString());
                        i.putExtra("besok",besok);
                        startActivity(i);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else{
                    progressDialog.dismiss();
                    dialogNoInternet();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                dialogServerNotConnect();

            }
        });
    }
    public void dialogNoInternet() {
        Dialog dialog = new DialogUtils(this).buildDialogWarning(R.string.title_no_internet, R.string.msg_no_internet, R.string.TRY_AGAIN, R.string.CLOSE, R.drawable.img_no_internet, new CallbackDialog() {
            @Override
            public void onPositiveClick(Dialog dialog) {
                dialog.dismiss();
                ManualPay(kodetransaksi,jbayar,accNumber.getText().toString(),accName.getText().toString());
            }

            @Override
            public void onNegativeClick(Dialog dialog) {
                finish();
            }
        });
        dialog.show();
    }
    public void dialogServerNotConnect() {
        Dialog dialog = new DialogUtils(this).buildDialogWarning(R.string.title_unable_connect, R.string.msg_unable_connect, R.string.TRY_AGAIN, R.string.CLOSE, R.drawable.img_no_connect, new CallbackDialog() {
            @Override
            public void onPositiveClick(Dialog dialog) {
                dialog.dismiss();
                ManualPay(kodetransaksi,jbayar,accNumber.getText().toString(),accName.getText().toString());
            }

            @Override
            public void onNegativeClick(Dialog dialog) {
                finish();
            }
        });
        dialog.show();
    }

}
