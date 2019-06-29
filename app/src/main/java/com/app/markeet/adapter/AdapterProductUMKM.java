package com.app.markeet.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.R;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.connection.callbacks.CallbackUmkmProduct;
import com.app.markeet.data.Constant;
import com.app.markeet.model.ProdukUMKM;
import com.app.markeet.umkm.GenerateActivity;
import com.app.markeet.umkm.ProductActivity;
import com.app.markeet.utils.Tools;
import com.balysv.materialripple.MaterialRippleLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterProductUMKM extends RecyclerView.Adapter<AdapterProductUMKM.MyViewHolder>{
    List<ProdukUMKM> produkUMKMS;
    private Context ctx;

    public AdapterProductUMKM(List<ProdukUMKM> produkUMKMS, Context ctx) {
        this.produkUMKMS = produkUMKMS;
        this.ctx = ctx;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produkumkm, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ProdukUMKM myData = produkUMKMS.get(position);
        final int idProduk = myData.getId();
        final String namaProduk = myData.getName();
        final String idp = Integer.toString(idProduk);
        holder.mTextViewNama.setText(produkUMKMS.get(position).getName());
        holder.mTextViewHarga.setText(produkUMKMS.get(position).getPrice());
        Tools.displayImageOriginal(ctx,holder.mProduk, Constant.getURLimgProduct(produkUMKMS.get(position).getImage()));
        holder.mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ctx, holder.mMenu);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_detailproduct);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.generate:
                                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                                try {
                                    BitMatrix bitMatrix = multiFormatWriter.encode(idp, BarcodeFormat.QR_CODE,200,200);
                                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                    Intent intent = new Intent(ctx,GenerateActivity.class);
                                    intent.putExtra("pic",bitmap);
                                    intent.putExtra("namaproduk",namaProduk);
                                    ctx.startActivity(intent);
                                }catch (WriterException e){
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.update:
                                Toast.makeText(ctx, "update"+idProduk, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                                builder.setTitle(R.string.confirmation);
                                builder.setMessage(R.string.confirm_delete);
                                builder.setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        API api = RestAdapter.createAPI();
                                        api.hapusProduct(idp).enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if(response.isSuccessful()){
                                                    Toast.makeText(ctx, "Successfully Delete Product", Toast.LENGTH_SHORT).show();
                                                    notifyDataSetChanged();
                                                    notifyItemRemoved(position);
                                                }else{
                                                    Toast.makeText(ctx, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                Toast.makeText(ctx, "Please Check Your Internet Connection ", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                                builder.setNegativeButton(R.string.NO, null);
                                builder.show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
        holder.lyt_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Test OK", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return produkUMKMS.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNama, mTextViewHarga, mMenu;
        public ImageView mProduk;
        public MaterialRippleLayout lyt_fragment;
        public MyViewHolder(View itemView) {
            super(itemView);
            mProduk = (ImageView) itemView.findViewById(R.id.gambarumkm);
            mTextViewNama = (TextView) itemView.findViewById(R.id.namaproduk);
            mTextViewHarga = (TextView) itemView.findViewById(R.id.hargaproduk);
            mMenu = (TextView) itemView.findViewById(R.id.textViewOptions);
            lyt_fragment = (MaterialRippleLayout) itemView.findViewById(R.id.lyt_parentt);
        }
    }


}
