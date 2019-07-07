package com.app.markeet.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.R;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.model.ManualOrder;
import com.balysv.materialripple.MaterialRippleLayout;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ManualOrder manualOrder = manualOrderList.get(position);
        holder.mOrderCode.setText(manualOrderList.get(position).getOrdercode());
        holder.mNamaOrder.setText(manualOrderList.get(position).getBuyer());
        final String status = manualOrder.getStatus_order();

        if(status.equals("WAITING")){
            holder.mNotif.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorWarning)));
        }else{
            holder.mNotif.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorPrimary)));
            holder.mDots.setVisibility(View.GONE);
        }
        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mDots.setVisibility(View.GONE);
                final BottomSheetDialog dialog;
                View views = LayoutInflater.from(ctx).inflate(R.layout.bottom_sheet_order, null);
                TextView hargaorder = (TextView) views.findViewById(R.id.subtotalorder);
                TextView accname = (TextView) views.findViewById(R.id.accname);
                TextView accnumber = (TextView) views.findViewById(R.id.accnunumber);
                Button btnverify = (Button) views.findViewById(R.id.processverify);
                if(status.equals("PROCESSED")){
                    btnverify.setVisibility(View.GONE);
                }
                hargaorder.setText("IDR "+manualOrder.getPay_orderr());
                accname.setText(manualOrder.getNama_akun());
                accnumber.setText(manualOrder.getRekening());

                btnverify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String serial = manualOrder.getSerial_order();
                        String idkode = manualOrder.getIdorder();
                        String statusorder = "PROCESSED";
                        //Toast.makeText(ctx, "serial "+serial+" dd "+kode+" sfs"+statusorder, Toast.LENGTH_SHORT).show();
                        API api = RestAdapter.createAPI();
                        api.upOrder(idkode,serial,statusorder).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(ctx, "Success", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(ctx, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(ctx, "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                dialog = new BottomSheetDialog(ctx);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(views);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return manualOrderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mOrderCode, mNamaOrder;
        public ImageView mDots,mNotif;
        public MaterialRippleLayout mParent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mOrderCode = (TextView) itemView.findViewById(R.id.kode_pesan);
            mNamaOrder = (TextView) itemView.findViewById(R.id.buyermanual);
            mDots = (ImageView) itemView.findViewById(R.id.dotsnotif);
            mNotif = (ImageView) itemView.findViewById(R.id.notiforder);
            mParent = (MaterialRippleLayout) itemView.findViewById(R.id.list_order_admin);
        }
    }
}
