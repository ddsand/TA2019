package com.app.markeet.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.markeet.R;
import com.app.markeet.adapter.AdapterListUMKM;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.connection.callbacks.CallbackListUMKM;
import com.app.markeet.connection.callbacks.CallbackUmkmProduct;
import com.app.markeet.data.SharedPref;
import com.app.markeet.model.ListUMKM;
import com.app.markeet.utils.Tools;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentAdmin extends Fragment {
    private View root_view;
    private SwipeRefreshLayout swipe_refresh;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Spinner filter_UMKM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       root_view =  inflater.inflate(R.layout.fragment_admin, null);
       //filter_UMKM =  (Spinner) root_view.findViewById(R.id.filterUMKM);
       mRecyclerView = (RecyclerView) root_view.findViewById(R.id.recycler_viewumkm);
       ListUMKM();
       swipe_refresh = (SwipeRefreshLayout) root_view.findViewById(R.id.refreshUMKM);
       swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               refreshItem();
           }
           void refreshItem() {
                ListUMKM();
                onItemLoad();
           }

           void onItemLoad() {
               swipe_refresh.setRefreshing(false);
           }
       });
       return root_view;
    }
    public void ListUMKM(){
        API api = RestAdapter.createAPI();
        api.AllUMKM().enqueue(new Callback<CallbackListUMKM>() {
            @Override
            public void onResponse(Call<CallbackListUMKM> call, Response<CallbackListUMKM> response) {
                if(response.isSuccessful()){
                    mLayoutManager = new GridLayoutManager(getActivity(),2);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    List<ListUMKM> listUMKMS = response.body().getDataumkm();
                    mAdapter = new AdapterListUMKM(listUMKMS,getActivity());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<CallbackListUMKM> call, Throwable t) {
                Toast.makeText(getContext(), "error :"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        ListUMKM();
    }

}
