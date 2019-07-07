package com.app.markeet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.data.Constant;
import com.app.markeet.utils.Tools;
import com.balysv.materialripple.MaterialRippleLayout;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailumkm extends AppCompatActivity {
    ImageView imgumkm;
    MaterialRippleLayout addverify;
    TextView numkm,ownerum,deskripsiumkm;
    String foto,owner,deskripsi,namaumkm,iduser,serial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailumkm);

        foto = getIntent().getStringExtra("fotoktp");
        owner = getIntent().getStringExtra("pemilik");
        deskripsi = getIntent().getStringExtra("deskripsi");
        namaumkm = getIntent().getStringExtra("namausaha");
        iduser = getIntent().getStringExtra("iduser");
        serial = getIntent().getStringExtra("serial");

        numkm = (TextView) findViewById(R.id.namaumkmm);
        ownerum = (TextView) findViewById(R.id.ownerumkm);
        deskripsiumkm = (TextView) findViewById(R.id.descriptionumkm);
        imgumkm = (ImageView) findViewById(R.id.img_umkm);

        numkm.setText(namaumkm); ownerum.setText(owner); deskripsiumkm.setText(deskripsi);

        Tools.displayImageOriginal(ActivityDetailumkm.this,imgumkm, Constant.getURLimgUmkm(foto));
        //verifyData();
        addverify = (MaterialRippleLayout) findViewById(R.id.lyt_add_umkm);
        addverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyData();
            }
        });
    }

    public void verifyData(){
        API api = RestAdapter.createAPI();
        api.verifyUMKM("2",iduser,serial).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ActivityDetailumkm.this, "Success", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ActivityDetailumkm.this, "error :"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
