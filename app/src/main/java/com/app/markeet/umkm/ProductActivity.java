package com.app.markeet.umkm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.R;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.connection.callbacks.CallbackCatSpinner;
import com.app.markeet.connection.callbacks.CallbackCategory;
import com.app.markeet.connection.callbacks.CallbackInProduct;
import com.app.markeet.connection.callbacks.CallbackProductDetails;
import com.app.markeet.data.SharedPref;
import com.app.markeet.model.Category;
import com.app.markeet.model.ListCategory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    Button imageBtn, buttonProduct;
    private SharedPref sharedPref;
    TextView productname,prices,discounts,stocks,descriptions;
    ImageView imgView;
    String mediaPath, mediaPath1;
    Spinner spCategory,statusProduct;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        sharedPref = new SharedPref(this);
        final Context context = this;
        imgView = (ImageView) findViewById(R.id.preview);
        imageBtn = (Button)findViewById(R.id.buttonImage);
        buttonProduct = (Button) findViewById(R.id.buttonProduct);
        spCategory = (Spinner) findViewById(R.id.spCategory);
        productname = (TextView) findViewById(R.id.txtProduct);
        statusProduct = (Spinner) findViewById(R.id.statusProduct);
        prices = (TextView) findViewById(R.id.txtPrice);
        discounts = (TextView) findViewById(R.id.txtDiscount);
        stocks = (TextView) findViewById(R.id.txtStock);
        descriptions = (TextView) findViewById(R.id.txtDesc);

        initSpinner();
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kategori = parent.getItemAtPosition(position).toString();
//                requestDetailDosen(selectedName);
                Toast.makeText(context, kategori, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        statusProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String statuspro = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), statuspro, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        buttonProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile(prices.getText().toString(),discounts.getText().toString(),
                        stocks.getText().toString(),descriptions.getText().toString(),
                        statusProduct.getSelectedItem().toString(),spCategory.getSelectedItem().toString());
                String getText = productname.getText().toString();
            }
        });
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });
    }

    private void initSpinner(){
        API api = RestAdapter.createAPI();
        api.getCat().enqueue(new Callback<CallbackCatSpinner>() {
            @Override
            public void onResponse(Call<CallbackCatSpinner> call, Response<CallbackCatSpinner> response) {
                CallbackCatSpinner resp = response.body();
                if (resp != null && resp.getStatus().equals("success")) {
                    List<ListCategory> listCategoryList = response.body().getCategoryList();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i =0; i<listCategoryList.size(); i++){
                        listSpinner.add(listCategoryList.get(i).getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spCategory.setAdapter(adapter);
                } else {
                    Toast.makeText(ProductActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CallbackCatSpinner> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
               // str1.setText(mediaPath);
                // Set the Image in ImageView for Previewing the Media
                imgView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();

            } // When an Video is picked
            else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

                // Get the Video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Video.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                mediaPath1 = cursor.getString(columnIndex);
                //str2.setText(mediaPath1);
                // Set the Video Thumb in ImageView Previewing the Media
                imgView.setImageBitmap(getThumbnailPathForLocalFile(ProductActivity.this, selectedVideo));
                cursor.close();

            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    // Providing Thumbnail For Selected Image
    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
        long fileId = getFileId(context, fileUri);
        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
    }

    // Getting Selected File ID
    public long getFileId(Activity context, Uri fileUri) {
        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return cursor.getInt(columnIndex);
        }
        return 0;
    }

    public void uploadFile(String harga,String diskon, String stok, String deskripsi, String statusproduk, String kategori){
        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody images = RequestBody.create(MediaType.parse("multipart/form-data"), file.getName());
        RequestBody price = RequestBody.create(MediaType.parse("multipart/form-data"),harga);
        RequestBody price_discount = RequestBody.create(MediaType.parse("multipart/form-data"),diskon);
        RequestBody stock = RequestBody.create(MediaType.parse("multipart/form-data"),stok);
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),deskripsi);
        RequestBody status = RequestBody.create(MediaType.parse("multipart/form-data"),statusproduk);
        RequestBody category = RequestBody.create(MediaType.parse("multipart/form-data"),kategori);
        RequestBody namaproduk = RequestBody.create(MediaType.parse("multipart/form-data"),productname.getText().toString());
        String idu=sharedPref.getSPIdUser().toString();
        RequestBody iduser = RequestBody.create(MediaType.parse("multipart/form-data"),idu);
        API api = RestAdapter.createAPI();

        api.uploadFile(fileToUpload,images,namaproduk,price,price_discount,stock,description,status,iduser,category).enqueue(new Callback<CallbackInProduct>() {
            @Override
            public void onResponse(Call<CallbackInProduct> call, Response<CallbackInProduct> response) {
                CallbackInProduct serverResponse = response.body();
                if (serverResponse != null) {
                    String idproduk = serverResponse.getData().getId().toString();
                    String produks = serverResponse.getData().getName().toString();

                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(idproduk, BarcodeFormat.QR_CODE,200,200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        Intent intent = new Intent(getApplicationContext(),GenerateActivity.class);
                        intent.putExtra("pic",bitmap);
                        intent.putExtra("namaproduk",produks);
                        Toast.makeText(ProductActivity.this, produks, Toast.LENGTH_SHORT).show();
                        getApplicationContext().startActivity(intent);
                    }catch (WriterException e){
                        e.printStackTrace();
                    }
                   //Toast.makeText(ProductActivity.this, "oke ini id nya"+idproduk, Toast.LENGTH_SHORT).show();
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
            }

            @Override
            public void onFailure(Call<CallbackInProduct> call, Throwable t) {

            }
        });
    }
}
