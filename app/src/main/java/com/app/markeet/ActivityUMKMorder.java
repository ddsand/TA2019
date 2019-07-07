package com.app.markeet;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.markeet.adapter.AdapterOrderList;
import com.app.markeet.adapter.AdapterUMKMorder;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.connection.callbacks.CallbackDetailOrder;
import com.app.markeet.data.SharedPref;
import com.app.markeet.model.DetailOrder;
import com.app.markeet.model.ListOrder;
import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUMKMorder extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPref sharedPref;
    String ordercode,serialcode,iduser;
    MaterialRippleLayout materialRippleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umkmorder);
        materialRippleLayout = (MaterialRippleLayout) findViewById(R.id.lyt_add_order);
         ordercode = getIntent().getStringExtra("idorder");
        serialcode = getIntent().getStringExtra("serialorder");
        Toast.makeText(this, "toasttt "+ordercode+"skdskd "+serialcode, Toast.LENGTH_SHORT).show();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerOrderdetail);

        sharedPref = new SharedPref(ActivityUMKMorder.this);
        iduser = sharedPref.getSPIdUser().toString();

        materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityUMKMorder.this, "cek", Toast.LENGTH_SHORT).show();
            }
        });
        orderlist();
    }
    public void setOrder(){
        API api = RestAdapter.createAPI();
        api.setOrder(ordercode,iduser,serialcode).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void orderlist(){
        API api = RestAdapter.createAPI();
        api.detOrder(ordercode,iduser).enqueue(new Callback<CallbackDetailOrder>() {
            @Override
            public void onResponse(Call<CallbackDetailOrder> call, Response<CallbackDetailOrder> response) {
                if(response.isSuccessful()){
                    mLayoutManager = new LinearLayoutManager(ActivityUMKMorder.this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    List<DetailOrder> mListOrder = response.body().getDataDetailOrders();
                    Log.d("Get", "Data Kontak" + String.valueOf(mListOrder.size()));
                    mAdapter = new AdapterUMKMorder(mListOrder,ActivityUMKMorder.this);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<CallbackDetailOrder> call, Throwable t) {
                Toast.makeText(ActivityUMKMorder.this, "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
