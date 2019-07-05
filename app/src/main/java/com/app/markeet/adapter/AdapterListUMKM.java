package com.app.markeet.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.markeet.R;
import com.app.markeet.model.ListUMKM;
import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;

public class AdapterListUMKM extends RecyclerView.Adapter<AdapterListUMKM.ViewHolder>{
    List<ListUMKM> mlistUMKMS;
    private Context ctx;

    public AdapterListUMKM(List<ListUMKM> mlistUMKMS, Context ctx) {
        this.mlistUMKMS = mlistUMKMS;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listumkm, parent, false);
        AdapterListUMKM.ViewHolder mViewHolder = new AdapterListUMKM.ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListUMKM mydata = mlistUMKMS.get(position);
        holder.mNamaUMKM.setText(mlistUMKMS.get(position).getName());
        holder.mAlamatUMKM.setText(mlistUMKMS.get(position).getAddress());
        holder.mStatusUMKM.setText(mlistUMKMS.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mlistUMKMS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNamaUMKM, mAlamatUMKM, mStatusUMKM;
        public ImageView mFotoUMKM;
        public MaterialRippleLayout mParent;

        public ViewHolder(View itemView) {
            super(itemView);
            mNamaUMKM = (TextView) itemView.findViewById(R.id.nama_umkm);
            mAlamatUMKM = (TextView) itemView.findViewById(R.id.alamat_umkm);
            mStatusUMKM = (TextView) itemView.findViewById(R.id.status_umkm);
            mParent = (MaterialRippleLayout) itemView.findViewById(R.id.lyt_listumkm);
        }
    }
}
