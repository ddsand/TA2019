package com.app.markeet;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.markeet.adapter.AdapterUnverifiedUMKM;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.connection.callbacks.CallbackUnverifiedUMKM;
import com.app.markeet.model.UnverifiedUMKM;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityUnverifiedumkm extends AppCompatActivity {
    private SwipeRefreshLayout rvRefresh;
    private RecyclerView rvlistOrder;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unverifiedumkm);
        initComponent();

    }
    public void initComponent(){
        rvlistOrder = (RecyclerView) findViewById(R.id.rvUnverified);
        rvRefresh = (SwipeRefreshLayout) findViewById(R.id.refresh_manual_unverified);
        rvLayoutManager = new GridLayoutManager(ActivityUnverifiedumkm.this,2);
        rvlistOrder.setLayoutManager(rvLayoutManager);
        listUMKM();
        rvRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }
            void refreshItem() {
                listUMKM();
                onItemLoad();
            }

            void onItemLoad() {
                rvRefresh.setRefreshing(false);
            }
        });
    }
    public void listUMKM(){
        API api = RestAdapter.createAPI();
        api.UnverfiedUMKM().enqueue(new Callback<CallbackUnverifiedUMKM>() {
            @Override
            public void onResponse(Call<CallbackUnverifiedUMKM> call, Response<CallbackUnverifiedUMKM> response) {
                if(response.isSuccessful()){
                    Toast.makeText(ActivityUnverifiedumkm.this, "tt", Toast.LENGTH_SHORT).show();
                    List<UnverifiedUMKM> unverifiedUMKMList = response.body().getListumkm();
                    rvAdapter = new AdapterUnverifiedUMKM(unverifiedUMKMList,ActivityUnverifiedumkm.this);
                    rvlistOrder.setAdapter(rvAdapter);
                }
            }

            @Override
            public void onFailure(Call<CallbackUnverifiedUMKM> call, Throwable t) {
                Toast.makeText(ActivityUnverifiedumkm.this, "error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
