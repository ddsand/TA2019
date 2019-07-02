package com.app.markeet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.data.SharedPref;
import com.app.markeet.model.BuyerProfile;
import com.app.markeet.umkm.ActivityUmkm;
import com.app.markeet.umkm.HomeActivity;
import com.app.markeet.utils.Tools;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button buttonScan;
    private TextView signup,etEmail,etPassword;
    ProgressDialog loading;
    SharedPref sharedPref;
    private BuyerProfile buyerProfile;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = new SharedPref(this);
        buyerProfile = sharedPref.getBuyerProfile();

        buttonScan = (Button) findViewById(R.id.buttonScann);
        etEmail = (TextView) findViewById(R.id.etEmail);
        etPassword = (TextView) findViewById(R.id.etPassword);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loading = ProgressDialog.show(mContext,null,"Wait....",true,false);
                Toast.makeText(getApplicationContext(), "Harap Tunggu...", Toast.LENGTH_LONG).show();
                requestLogin();
            }
        });
        signup = (TextView) findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
                finish(); // kill current activity
            }
        });
    }

    private void requestLogin(){
        API api = RestAdapter.createAPI();
        String serial = Tools.getDeviceID(this);
        api.loginRequest(etEmail.getText().toString(),etPassword.getText().toString(),serial).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               ResponseBody responseBody = response.body();
               if(response.isSuccessful()) {
                   //Toast.makeText(getApplicationContext(), "Isi...", Toast.LENGTH_LONG).show();
                   try{
                       JSONObject jsonObject = new JSONObject(responseBody.string());
                       String id = jsonObject.getString("id");
                       String email = String.valueOf(jsonObject.getString("email"));
                       String status = jsonObject.getString("status");
                       String msg= jsonObject.getString("msg");
                       String name = jsonObject.getString("name");
                       String addres = jsonObject.getString("addr");
                       int cek = Integer.valueOf(status);
                       if(msg.equals("Gagal")){
                           Toast.makeText(mContext, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                       }else if(msg.equals("Sukses")){
                           sharedPref.saveString(sharedPref.SP_IDUSER,id);
                           sharedPref.saveString(sharedPref.SP_USER,email);
                           sharedPref.saveString(sharedPref.SP_STATUS,status);
                           sharedPref.saveString(sharedPref.SP_NAME,name);
                           sharedPref.saveBoolean(sharedPref.SP_SUDAH_LOGIN,true);

                           buyerProfile = new BuyerProfile();
                           buyerProfile.name = name;
                           buyerProfile.email = email;
                           buyerProfile.address = addres;
                           sharedPref.setBuyerProfile(buyerProfile);

                           if(cek == 2){
                               //Toast.makeText(LoginActivity.this,"UMKM "+status,Toast.LENGTH_LONG).show();
                               //Intent intent = new Intent(LoginActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                               Intent intent = new Intent(LoginActivity.this,ActivityHomeumkm.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(intent);
                               finish();
                           }
                           else if(cek == 0){
                               //Toast.makeText(LoginActivity.this,"Buyer "+status,Toast.LENGTH_LONG).show();
                               Intent intent = new Intent(LoginActivity.this,ActivityMain.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(intent);
                               finish();
                           }else if(cek == 1){
                               //Toast.makeText(LoginActivity.this,"Admin "+status,Toast.LENGTH_LONG).show();
                               Intent intent = new Intent(LoginActivity.this,ActivityAdminhome.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(intent);
                               finish();
                           }
                           else {
                               Toast.makeText(LoginActivity.this,"Not Registered Yet "+status,Toast.LENGTH_LONG).show();
                           }
                       }else{
                           Toast.makeText(mContext, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                       }
                   }catch (JSONException e){
                       e.printStackTrace();
                   }catch (IOException e){
                       e.printStackTrace();
                   }
               }else{
                   Toast.makeText(LoginActivity.this, "Not Registered Yet", Toast.LENGTH_SHORT).show();
               }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //loading.dismiss();
                Log.e("debug","OnFailure: ERROR >"+t.toString());
            }
        });

    }
}
