package com.app.markeet.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.markeet.R;
import com.app.markeet.model.ManualOrder;
import com.balysv.materialripple.MaterialRippleLayout;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterManualOrder extends RecyclerView.Adapter<AdapterManualOrder.MyViewHolder> {
    List<ManualOrder> manualOrderList;
    Context ctx;

    public AdapterManualOrder(List<ManualOrder> manualOrderList, Context ctx) {
        this.manualOrderList = manualOrderList;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manualorder, parent, false);
        AdapterManualOrder.MyViewHolder mViewHolder = new AdapterManualOrder.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ManualOrder manualOrder = manualOrderList.get(position);
        holder.mOrderCode.setText(manualOrderList.get(position).getOrdercode());
        holder.mNamaOrder.setText(manualOrderList.get(position).getBuyer());
        holder.mNamaRek.setText(manualOrderList.get(position).getNama_akun());
        holder.mNorek.setText(manualOrderList.get(position).getRekening());
        holder.mOrderprice.setText(manualOrderList.get(position).getPay_orderr());
        String status = manualOrder.getStatus_order();

        if(status.equals("WAITING")){
            holder.mNotif.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorWarning)));
        }else{
            holder.mNotif.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorPrimary)));
        }
        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mDots.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return manualOrderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mOrderCode, mNamaOrder, mNamaRek, mNorek, mMenu, mOrderprice;
        public ImageView mDots,mNotif;
        public MaterialRippleLayout mParent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mOrderprice = (TextView) itemView.findViewById(R.id.order_pricee);
            mOrderCode = (TextView) itemView.findViewById(R.id.order_code);
            mNamaOrder = (TextView) itemView.findViewById(R.id.buyermanual);
            mNamaRek = (TextView) itemView.findViewById(R.id.nama_akun);
            mNorek = (TextView) itemView.findViewById(R.id.no_rekening);
            mMenu = (TextView) itemView.findViewById(R.id.textMenu);
            mDots = (ImageView) itemView.findViewById(R.id.dotsnotif);
            mNotif = (ImageView) itemView.findViewById(R.id.notiforder);
            mParent = (MaterialRippleLayout) itemView.findViewById(R.id.list_order_admin);
        }
    }
}
