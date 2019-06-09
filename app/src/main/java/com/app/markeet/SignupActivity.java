package com.app.markeet;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    TextView signin,email,username,name,password,retype,birth,addr;
    RadioGroup gender;
    RadioButton gen;
    Button regist;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (TextView) findViewById(R.id.txtEmail);
        username = (TextView) findViewById(R.id.txtUsername);
        name = (TextView) findViewById(R.id.txtName);
        password = (TextView) findViewById(R.id.txtPassword);
        retype = (TextView) findViewById(R.id.txtRetype);
        birth = (TextView) findViewById(R.id.txtBirthday);
        addr = (TextView) findViewById(R.id.txtAddr);
        signin = (TextView) findViewById(R.id.sign_in);
        regist = (Button) findViewById(R.id.buttonRegis);


        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignupActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("Signup", "onDateSet: dd-mm-yyy: " + day + "/" + month + "/" + year);

                String date = month + "/" + day + "/" + year;
                birth.setText(date);
            }
        };
        gender = (RadioGroup) findViewById(R.id.gender);
        int selectedId = gender.getCheckedRadioButtonId();

        // mencari radio button
        gen = (RadioButton) findViewById(selectedId);
        //menampilkan pesan teks / toast

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignupActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
                registeruser();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                finish();
            }
        });

    }
    private void registeruser(){
        API api = RestAdapter.createAPI();
        api.registerRequest(username.getText().toString(),password.getText().toString(),name.getText().toString(),
                email.getText().toString(),addr.getText().toString(),
                gen.getText().toString(),birth.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                if(responseBody!=null){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("success")){
                            Toast.makeText(SignupActivity.this, "Registration Succes", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        } else if(jsonRESULTS.getString("status").equals("failed")){
                            String error_message = jsonRESULTS.getString("Your Email or Username is Exist");
                            Toast.makeText(SignupActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        }else {
                            String error_message = jsonRESULTS.getString("Error Registration");
                            Toast.makeText(SignupActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(SignupActivity.this, "error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                Toast.makeText(SignupActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
