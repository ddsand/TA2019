package com.app.markeet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.markeet.R;
import com.app.markeet.model.ListPayment;
import com.app.markeet.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class AdapterPayment extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListPayment> items = new ArrayList<>();

    private Context ctx;
    private AdapterPayment.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, ListPayment obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterPayment(Context context, List<ListPayment> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.imagepay);
            name = (TextView) v.findViewById(R.id.merchant_name);
            lyt_parent = (View) v.findViewById(R.id.pay_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detailpay, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            ListPayment p = items.get(position);
            view.name.setText(p.merchant_name);
            //Tools.displayImageRound(ctx, view.image, p.image);
            Tools.displayImageOriginal(ctx, view.image, p.image);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
