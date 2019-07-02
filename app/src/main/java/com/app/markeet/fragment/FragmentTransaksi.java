package com.app.markeet.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.R;
import com.app.markeet.adapter.AdapterOrderList;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.connection.callbacks.CallbackListOrder;
import com.app.markeet.data.SharedPref;
import com.app.markeet.model.ListOrder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentTransaksi extends Fragment {

    private SwipeRefreshLayout swipe_refresh;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPref sharedPref;
    private Spinner spinner;
    private View lyt_no_item;
    String isi;
    public FragmentTransaksi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_transaksi, null);
        spinner = (Spinner) fragmentView.findViewById(R.id.filterorder);
        initspinnerfooter();
        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.recyclerTransaksi);
        swipe_refresh = (SwipeRefreshLayout) fragmentView.findViewById(R.id.RefreshOrder);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }
            void refreshItem() {
                OrderList();
                onItemLoad();
            }

            void onItemLoad() {
                swipe_refresh.setRefreshing(false);
            }
        });

        lyt_no_item = (View) fragmentView.findViewById(R.id.lyt_no_item);
        return fragmentView;
    }
    private void initspinnerfooter() {
        String[] items = new String[]{
                "WAITING","PROCESSED","ACCEPTED"
        };

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                //Toast.makeText(getContext(), "item "+adapter.getItem(position), Toast.LENGTH_SHORT).show();
                isi = adapter.getItem(position);
                OrderList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void OrderList(){
        sharedPref = new SharedPref(getActivity());
        String iduser = sharedPref.getSPIdUser().toString();
        API api = RestAdapter.createAPI();
        api.checkOrder(iduser,isi).enqueue(new Callback<CallbackListOrder>() {
            @Override
            public void onResponse(Call<CallbackListOrder> call, Response<CallbackListOrder> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("failed")){
                        lyt_no_item.setVisibility(View.VISIBLE);
                    }else {
                        mLayoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        List<ListOrder> mListOrder = response.body().getDataorder();
                        Log.d("Get", "Data Kontak" + String.valueOf(mListOrder.size()));
                        mAdapter = new AdapterOrderList(mListOrder, getActivity());
                        mRecyclerView.setAdapter(mAdapter);
                        lyt_no_item.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<CallbackListOrder> call, Throwable t) {

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        OrderList();
    }
}
