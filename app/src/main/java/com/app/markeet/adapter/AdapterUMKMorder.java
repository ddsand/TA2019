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
import com.app.markeet.model.DetailOrder;

import java.util.List;

public class AdapterUMKMorder extends RecyclerView.Adapter<AdapterUMKMorder.MyViewHolder> {
    List<DetailOrder> detailOrderList;
    Context ctx;

    public AdapterUMKMorder(List<DetailOrder> detailOrderList, Context ctx) {
        this.detailOrderList = detailOrderList;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_umkmorder, parent, false);
        AdapterUMKMorder.MyViewHolder mViewHolder = new AdapterUMKMorder.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTittle.setText(detailOrderList.get(position).getNama());
        holder.mAmount.setText(detailOrderList.get(position).getJumlah());
        holder.mPrice.setText(detailOrderList.get(position).getHargabarang());
    }

    @Override
    public int getItemCount() {
        return detailOrderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTittle, mAmount, mPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTittle = (TextView) itemView.findViewById(R.id.titleproduct);
            mAmount = (TextView) itemView.findViewById(R.id.amountproduct);
            mPrice = (TextView) itemView.findViewById(R.id.priceproduct);

        }
    }
}
