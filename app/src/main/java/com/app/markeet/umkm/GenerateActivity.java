package com.app.markeet.umkm;

import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.markeet.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        imageView = (ImageView)findViewById(R.id.imageQR);
        final Bitmap bitmap = getIntent().getParcelableExtra("pic");
        final String namaproduk = getIntent().getStringExtra("namaproduk");
        imageView.setImageBitmap(bitmap);

        btnSave = (Button) findViewById(R.id.buttonLabel);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenerateActivity.this, namaproduk, Toast.LENGTH_SHORT).show();
                saveImage(bitmap,namaproduk);
            }
        });

    }
    private void saveImage(Bitmap finalBitmap, String image_name) {

//        String root = Environment.getExternalStorageDirectory()
//                + "/Android/data/"
//                + getApplicationContext().getPackageName()
//                + "/Files";
        String root = Environment.getExternalStorageDirectory()
                + "/FilesCeMart";
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Label-" + image_name+ ".png";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            Toast.makeText(this, "Saved Label on "+root, Toast.LENGTH_SHORT).show();

            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
