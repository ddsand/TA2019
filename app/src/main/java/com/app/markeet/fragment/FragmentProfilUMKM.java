package com.app.markeet.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.markeet.ActivityHomeumkm;
import com.app.markeet.R;
import com.app.markeet.adapter.AdapterProductUMKM;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.connection.callbacks.CallbackUmkmProduct;
import com.app.markeet.data.SharedPref;
import com.app.markeet.model.ProdukUMKM;
import com.app.markeet.umkm.ProductActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentProfilUMKM extends Fragment {

    private SharedPref sharedPref;
    private Button btnTambah;
    private FloatingActionButton btnAdd;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public FragmentProfilUMKM() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_profil_umkm, null);
        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rvProduk);
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        SelectData();

        btnAdd = (FloatingActionButton) fragmentView.findViewById(R.id.addProduct);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAdd = new Intent(getActivity(), ProductActivity.class);
                goAdd.putExtra("intent_action", "insert");
                startActivity(goAdd);
            }
        });
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) fragmentView.findViewById(R.id.RefreshData);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }
            void refreshItem() {
                SelectData();
                onItemLoad();
            }

            void onItemLoad() {
                refreshLayout.setRefreshing(false);
            }
        });
        return fragmentView;
    }
    public void SelectData(){
        sharedPref = new SharedPref(getActivity());
        String iduser = sharedPref.getSPIdUser().toString();
        API api = RestAdapter.createAPI();
        api.Allproduct(iduser).enqueue(new Callback<CallbackUmkmProduct>() {
            @Override
            public void onResponse(Call<CallbackUmkmProduct> call, Response<CallbackUmkmProduct> response) {
                if(response.isSuccessful()) {
                    List<ProdukUMKM> produkUMKMS = response.body().getDataproduk();
                    Log.d("Retrofit Get", "Jumlah data: " +
                            String.valueOf(produkUMKMS.size()));
                    mAdapter = new AdapterProductUMKM(produkUMKMS,getActivity());
                    mRecyclerView.setAdapter(mAdapter);
                }else{
                    Toast.makeText(getActivity(), "Please try again, server is down", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CallbackUmkmProduct> call, Throwable t) {
                Toast.makeText(getActivity(), "Please try again, server is down onfail", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        SelectData();
    }
}
