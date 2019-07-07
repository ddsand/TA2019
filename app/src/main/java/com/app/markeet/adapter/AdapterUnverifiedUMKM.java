package com.app.markeet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.markeet.ActivityDetailumkm;
import com.app.markeet.R;
import com.app.markeet.model.UnverifiedUMKM;
import com.balysv.materialripple.MaterialRippleLayout;

import java.util.List;

public class AdapterUnverifiedUMKM extends RecyclerView.Adapter<AdapterUnverifiedUMKM.MyViewHolder> {
    List<UnverifiedUMKM> unverifiedUMKMS;
    private Context ctx;

    public AdapterUnverifiedUMKM(List<UnverifiedUMKM> unverifiedUMKMS, Context ctx) {
        this.unverifiedUMKMS = unverifiedUMKMS;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_umkmverfied, parent, false);
        AdapterUnverifiedUMKM.MyViewHolder mViewHolder = new AdapterUnverifiedUMKM.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final UnverifiedUMKM unverifiedUMKM = unverifiedUMKMS.get(position);
        holder.mNamaUMKM.setText(unverifiedUMKMS.get(position).getNamausaha());
        holder.mAlamatUMKM.setText(unverifiedUMKMS.get(position).getAddress_umkm());
        final String mfoto = unverifiedUMKM.getFotoktp();
        final String mOwner = unverifiedUMKM.getName();
        final String mDeskripsi = unverifiedUMKM.getDeskripsi();
        final String mUsaha = unverifiedUMKM.getNamausaha();
        final String mid = unverifiedUMKM.getIduser_umkm();
        final String serial = unverifiedUMKM.getSerial_umkm();

        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, ActivityDetailumkm.class);
                i.putExtra("iduser",mid);
                i.putExtra("serial",serial);
                i.putExtra("fotoktp",mfoto);
                i.putExtra("namausaha",mUsaha);
                i.putExtra("pemilik",mOwner);
                i.putExtra("deskripsi",mDeskripsi);
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return unverifiedUMKMS.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mNamaUMKM, mAlamatUMKM;
        public MaterialRippleLayout mParent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mNamaUMKM = (TextView) itemView.findViewById(R.id.umkm_verified);
            mAlamatUMKM = (TextView) itemView.findViewById(R.id.alamat_verified);
            mParent = (MaterialRippleLayout) itemView.findViewById(R.id.lyt_umkm);
        }
    }
}
