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
import com.app.markeet.data.SharedPref;
import com.app.markeet.model.ListUMKM;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentListumkm extends Fragment {

    private SwipeRefreshLayout rvRefresh;
    private RecyclerView rvlistUMKM;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    private SharedPref sharedPref;
    private Spinner rvspinner;

    //private OnFragmentInteractionListener mListener;

    public FragmentListumkm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_listumkm, null);
        //rvspinner = (Spinner) fragmentView.findViewById(R.id.filterumkm);
        rvlistUMKM = (RecyclerView) fragmentView.findViewById(R.id.recyclerUMKM);
        rvRefresh = (SwipeRefreshLayout) fragmentView.findViewById(R.id.refresh_umkm);
        rvLayoutManager = new GridLayoutManager(getActivity(),2);
        //rvLayoutManager = new LinearLayoutManager(getActivity());
        rvlistUMKM.setLayoutManager(rvLayoutManager);
        ListUMKM();
        rvRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }
            void refreshItem() {
                ListUMKM();
                onItemLoad();
            }

            void onItemLoad() {
                rvRefresh.setRefreshing(false);
            }
        });
        return fragmentView;
    }
    public void ListUMKM(){
        API api = RestAdapter.createAPI();
        api.AllUMKM().enqueue(new Callback<CallbackListUMKM>() {
            @Override
            public void onResponse(Call<CallbackListUMKM> call, Response<CallbackListUMKM> response) {
                if(response.isSuccessful()){
                    List<ListUMKM> listUMKMS = response.body().getDataumkm();
                    rvAdapter = new AdapterListUMKM(listUMKMS,getActivity());
                    rvlistUMKM.setAdapter(rvAdapter);
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

//
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
