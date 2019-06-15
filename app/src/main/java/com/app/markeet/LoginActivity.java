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
import com.app.markeet.umkm.ActivityUmkm;
import com.app.markeet.umkm.HomeActivity;
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
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = new SharedPref(this);

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
        api.loginRequest(etEmail.getText().toString(),etPassword.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               ResponseBody responseBody = response.body();
               if(responseBody!=null) {
                   //Toast.makeText(getApplicationContext(), "Isi...", Toast.LENGTH_LONG).show();
                   try{
                       JSONObject jsonObject = new JSONObject(responseBody.string());
                       String id = jsonObject.getString("id");
                       String username = String.valueOf(jsonObject.getString("email"));
                       String status = jsonObject.getString("status");
                       int cek = Integer.valueOf(status);

                       sharedPref.saveString(sharedPref.SP_IDUSER,id);
                       sharedPref.saveString(sharedPref.SP_USER,username);
                       sharedPref.saveString(sharedPref.SP_STATUS,status);
                       sharedPref.saveBoolean(sharedPref.SP_SUDAH_LOGIN,true);

                       if(cek == 2){
                           //Toast.makeText(LoginActivity.this,"UMKM "+status,Toast.LENGTH_LONG).show();
                           Intent intent = new Intent(LoginActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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
                       //Toast.makeText(LoginActivity.this,"Selamat Datang "+status,Toast.LENGTH_LONG).show();
                   }catch (JSONException e){
                       e.printStackTrace();
                   }catch (IOException e){
                       e.printStackTrace();
                   }

                   //Toast.makeText(LoginActivity.this, "isi", Toast.LENGTH_SHORT).show();
//                   try {
//
//                       JSONArray jsonArray = new JSONArray(responseBody.string());
//                       for (int i=0; i<=jsonArray.length(); i++){
//
//                           JSONObject jsonobject = jsonArray.getJSONObject(i);
//                           String id = jsonobject.getString("id");
//                           String username = String.valueOf(jsonobject.getString("email"));
//                           String status = jsonobject.getString("status");
//                           int cek = Integer.valueOf(status);
//                           Toast.makeText(LoginActivity.this,"Welcome"+id+"username"+username+" Stats :"+status,Toast.LENGTH_LONG).show();

//                           sharedPref.saveString(sharedPref.SP_IDUSER,id);
//                           sharedPref.saveString(sharedPref.SP_USER,username);
//                           sharedPref.saveString(sharedPref.SP_STATUS,status);
//                           sharedPref.saveBoolean(sharedPref.SP_SUDAH_LOGIN,true);
//                          if(cek == 2){
//                               Intent intent = new Intent(LoginActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                               startActivity(intent);
//                               finish();
//                           }
//                           else if(cek == 0){
//                               Intent intent = new Intent(LoginActivity.this,ActivityMain.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                               startActivity(intent);
//                               finish();
//                           }else if(cek == 1){
//                              Intent intent = new Intent(LoginActivity.this,ActivityAdminhome.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                              startActivity(intent);
//                              finish();
//                            }
//                           else {
//                               Toast.makeText(LoginActivity.this,"Not Registered Yet "+status,Toast.LENGTH_LONG).show();
//                           }
//                           //Toast.makeText(LoginActivity.this,"Selamat Datang "+status,Toast.LENGTH_LONG).show();
//                       }
//                   }catch (JSONException e){
//                       e.printStackTrace();
//                   }catch (IOException e){
//                       e.printStackTrace();
//                   }
               }else{
                   //loading.dismiss();
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
