package com.app.markeet.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.markeet.ActivityMain;
import com.app.markeet.ActivityManualorder;
import com.app.markeet.ActivityUnverifiedumkm;
import com.app.markeet.LoginActivity;
import com.app.markeet.R;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.data.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentProfilAdmin extends Fragment {

    FloatingActionButton verifyumkm,logout_btn,listumkm,listorder;
    private SharedPref sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profil_admin, null);
        sharedPref = new SharedPref(this.getActivity());

        listumkm = (FloatingActionButton) fragmentView.findViewById(R.id.list_umkm);
        verifyumkm = (FloatingActionButton) fragmentView.findViewById(R.id.btn_verifikasi);
        logout_btn = (FloatingActionButton) fragmentView.findViewById(R.id.keluaradmin);
        listorder = (FloatingActionButton) fragmentView.findViewById(R.id.list_order);

        listorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ActivityManualorder.class);
                startActivity(i);
            }
        });
        listumkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "list Umkm", Toast.LENGTH_SHORT).show();
            }
        });
        verifyumkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), ActivityUnverifiedumkm.class);
                startActivity(i);
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConfirmRegist();
            }
        });
        return  fragmentView;
    }
    public void dialogConfirmRegist() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.confirmation);
        builder.setMessage(getString(R.string.logout_info));
        builder.setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LogoutProcess();
            }
        });
        builder.setNegativeButton(R.string.NO, null);
        builder.show();
    }
    private void LogoutProcess(){
        final String idu =sharedPref.getSPIdUser().toString();
        API api = RestAdapter.createAPI();
        api.logoutRequest(idu).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                if(responseBody!=null){
                    try{
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String status = jsonObject.getString("success");
                        if(status.equals("Berhasil Log Out")){
                            sharedPref.saveBoolean(sharedPref.SP_SUDAH_LOGIN, false);
                            startActivity(new Intent(getActivity(), LoginActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
