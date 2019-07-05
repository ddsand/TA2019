package com.app.markeet;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.markeet.adapter.AdapterManualOrder;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.connection.callbacks.CallbackManualOrder;
import com.app.markeet.model.ManualOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityManualorder extends AppCompatActivity {
    private SwipeRefreshLayout rvRefresh;
    private RecyclerView rvlistOrder;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualorder);
        initComponent();
    }
    public void initComponent(){
        rvlistOrder = (RecyclerView) findViewById(R.id.rvOrderManual);
        rvRefresh = (SwipeRefreshLayout) findViewById(R.id.refresh_manual_order);
        rvLayoutManager = new LinearLayoutManager(this);
        rvlistOrder.setLayoutManager(rvLayoutManager);
        listorder();
        rvRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }
            void refreshItem() {
                listorder();
                onItemLoad();
            }

            void onItemLoad() {
                rvRefresh.setRefreshing(false);
            }
        });
    }
    public void listorder(){
        API api = RestAdapter.createAPI();
        api.AllOrderManual().enqueue(new Callback<CallbackManualOrder>() {
            @Override
            public void onResponse(Call<CallbackManualOrder> call, Response<CallbackManualOrder> response) {
                if(response.isSuccessful()){
                    List<ManualOrder> manualOrderList = response.body().getManualOrders();
                    rvAdapter = new AdapterManualOrder(manualOrderList,ActivityManualorder.this);
                    rvlistOrder.setAdapter(rvAdapter);
                }
            }
            @Override
            public void onFailure(Call<CallbackManualOrder> call, Throwable t) {

            }
        });
    }
}
