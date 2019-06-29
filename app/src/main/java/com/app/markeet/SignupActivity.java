package com.app.markeet;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.model.BuyerProfile;
import com.app.markeet.utils.Tools;

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
    TextInputEditText email,name,password,birth,addr;
    TextView signin;
    RadioGroup gender;
    RadioButton gen;
    Button regist;

    private View parent_view;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        parent_view = findViewById(android.R.id.content);

        email = (TextInputEditText) findViewById(R.id.txtEmail);
        name = (TextInputEditText) findViewById(R.id.txtName);
        password = (TextInputEditText) findViewById(R.id.txtPassword);

        birth = (TextInputEditText) findViewById(R.id.txtBirthday);
        addr = (TextInputEditText) findViewById(R.id.txtAddr);
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
                cal.add(Calendar.YEAR,-15);
                dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
                cal.add(Calendar.YEAR,-50);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("Signup", "onDateSet: dd-M-yyy: " + day + "/" + month + "/" + year);
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
                String emstr = email.getText().toString().trim();
                String namestr = name.getText().toString().trim();
                String passtr = password.getText().toString().trim();
                String birthstr = birth.getText().toString().trim();
                String addstr = addr.getText().toString().trim();
                String ps = password.getText().toString();

                if(emstr.isEmpty()){
                    Snackbar.make(parent_view,"Please Fill Your Name", Snackbar.LENGTH_SHORT).show();
                }else if(namestr.isEmpty()){
                    Snackbar.make(parent_view,"Please Fill Your Name", Snackbar.LENGTH_SHORT).show();
                }else if(passtr.isEmpty()){
                    Snackbar.make(parent_view,"Please Fill the Password", Snackbar.LENGTH_SHORT).show();
                }else if(birthstr.isEmpty()){
                    Snackbar.make(parent_view,"Please Fill Your Born date", Snackbar.LENGTH_SHORT).show();
                }else if(addstr.isEmpty()){
                    Snackbar.make(parent_view,"Please Fill Your Address", Snackbar.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignupActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
                    registeruser();
                }

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
        api.registerRequest(password.getText().toString(),name.getText().toString(),
                email.getText().toString(),addr.getText().toString(),
                gen.getText().toString(),birth.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                if(response.isSuccessful()){
                    try {
                        //JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String status = jsonObject.getString("status");
                        //Toast.makeText(SignupActivity.this, "status" + status, Toast.LENGTH_SHORT).show();
                        if (status.equals("success")){
                            Toast.makeText(SignupActivity.this, "Registration Succes", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        } else if(status.equals("failed")){
                            String error_message = jsonObject.getString("Your Email or Username is Exist");
                            Toast.makeText(SignupActivity.this, "Duplicate Data", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignupActivity.this,"Failed to Create Account", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SignupActivity.this, "Internet COnnecion Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
