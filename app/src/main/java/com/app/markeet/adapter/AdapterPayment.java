package com.app.markeet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.R;
import com.app.markeet.model.ListPayment;
import com.app.markeet.utils.Tools;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterPayment extends RecyclerView.Adapter<AdapterPayment.ViewHolder> {
    private static final String TAG = "AdapterPayment";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;


    public AdapterPayment(Context context, ArrayList<String> imageNames, ArrayList<String> images,ArrayList<String> desc ) {
        mImageNames = imageNames;
        mDesc = desc;
        mImages = images;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detailpay, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext).load(mImages.get(position)).into(holder.image);
        holder.imageName.setText(mImageNames.get(position));
        holder.desc.setText(mDesc.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView imageName;
        TextView desc;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.merchant_name);
            imageName = (TextView) itemView.findViewById(R.id.imagepay);
            desc = (TextView) itemView.findViewById(R.id.description);
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.pay_parent);
        }
    }
}
