package com.app.markeet.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.ActivityUMKMorder;
import com.app.markeet.R;
import com.app.markeet.model.ListOrder;

import java.util.List;

public class AdapterOrderList extends RecyclerView.Adapter<AdapterOrderList.MyViewHolder>{
    List<ListOrder> mListOrders;
    private Context ctx;

    public AdapterOrderList(List<ListOrder> mListOrders, Context ctx) {
        this.mListOrders = mListOrders;
        this.ctx = ctx;
    }

    @Override
    public AdapterOrderList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listorder, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterOrderList.MyViewHolder holder, int position) {
        final ListOrder myData = mListOrders.get(position);
        String status = myData.getStatusorder();
        if(status.equals("WAITING")){
            holder.mStatusOrder.setTextColor(ContextCompat.getColor(ctx,R.color.colorWarning));
            holder.mNotif.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorWarning)));
            holder.mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = myData.getKode();
                    String serial = myData.getSerial();
                    Intent i = new Intent(ctx, ActivityUMKMorder.class);
                    i.putExtra("idorder",id);
                    i.putExtra("serialorder",serial);
                    ctx.startActivity(i);
                }
            });
        }else if(status.equals("PROCESSED")){
            holder.mStatusOrder.setTextColor(ContextCompat.getColor(ctx,R.color.colorAddCart));
            holder.mNotif.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorAddCart)));
        }else {
            holder.mStatusOrder.setTextColor(ContextCompat.getColor(ctx,R.color.colorPrimaryLight));
            holder.mNotif.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorPrimaryLight)));
        }
        holder.mBuyer.setText(mListOrders.get(position).getPembeli());
        holder.mDateOrder.setText(mListOrders.get(position).getTanggal());
        holder.mOrderCode.setText(mListOrders.get(position).getKode());
        holder.mStatusOrder.setText(mListOrders.get(position).getStatusorder());


    }

    @Override
    public int getItemCount() {
        return mListOrders.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mOrderCode, mBuyer, mStatusOrder, mDateOrder;
        public ImageView mNotif;
        public LinearLayout mParent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mOrderCode = (TextView) itemView.findViewById(R.id.order_code);
            mBuyer = (TextView) itemView.findViewById(R.id.buyercode);
            mStatusOrder = (TextView) itemView.findViewById(R.id.statusorder);
            mDateOrder = (TextView) itemView.findViewById(R.id.dateorder);
            mNotif = (ImageView) itemView.findViewById(R.id.notifcolor);
            mParent = (LinearLayout) itemView.findViewById(R.id.lyt_parent);

        }
    }
}
