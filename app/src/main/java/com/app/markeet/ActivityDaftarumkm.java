package com.app.markeet;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.markeet.data.SharedPref;
import com.app.markeet.umkm.ProductActivity;

import org.w3c.dom.Text;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ActivityDaftarumkm extends AppCompatActivity {
    private View parent_view;
    private Toolbar toolbar;
    private ActionBar actionBar;
    EditText nama_usaha,nomorktp,deskripsi_usaha;
    Button btnktp,btndaftar;
    TextInputLayout namausha_lyt, nomorktp_lyt, deskripsi_lyt;
    private SharedPref sharedPref;

    ImageView imgView;
    String mediaPath, mediaPath1;
    String[] mediaColumns = {MediaStore.Video.Media._ID};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarumkm);
        initToolbar();

        sharedPref = new SharedPref(this);
        final Context context = this;

        initComponent();

    }
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("UMKM Registration");
    }
    private void initComponent(){
        nama_usaha = (EditText) findViewById(R.id.nama_usaha);
        nomorktp = (EditText) findViewById(R.id.nomorktp);
        deskripsi_usaha = (EditText) findViewById(R.id.deskripsi_usaha);
        btnktp = (Button) findViewById(R.id.btnktp);
        btndaftar = (Button) findViewById(R.id.btn_daftar);
        imgView = (ImageView) findViewById(R.id.previewktp);
        parent_view = findViewById(android.R.id.content);

        namausha_lyt = (TextInputLayout) findViewById(R.id.lyt_namausaha);
        nomorktp_lyt = (TextInputLayout) findViewById(R.id.lyt_ktp);
        deskripsi_lyt = (TextInputLayout) findViewById(R.id.lyt_deskripsiusaha);

        btnktp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });
        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivityDaftarumkm.this, "Tes", Toast.LENGTH_SHORT).show();
                submitForm();
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
    private void submitForm() {
        if (!validateUMKM()) {
            Snackbar.make(parent_view, R.string.invalid_name, Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (!validateDeskripsi()) {
            Snackbar.make(parent_view, R.string.validateDesc, Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (!validateNomorktp()) {
            Snackbar.make(parent_view, R.string.validateKTP, Snackbar.LENGTH_SHORT).show();
            return;
        }
        hideKeyboard();

        // show dialog confirmation
        dialogConfirmRegist();
    }
    public void dialogConfirmRegist() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirmation);
        builder.setMessage(getString(R.string.registratio_umkm));
        builder.setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                uploadFile(nama_usaha.getText().toString(),nomorktp.getText().toString(),deskripsi_usaha.getText().toString());
            }
        });
        builder.setNegativeButton(R.string.NO, null);
        builder.show();
    }

    public void uploadFile(String namausaha,String nomorktp,String dekripsi) {
        File file = new File(mediaPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody fotoktp = RequestBody.create(MediaType.parse("multipart/form-data"), file.getName());
        String idu=sharedPref.getSPIdUser().toString();
        RequestBody iduser = RequestBody.create(MediaType.parse("multipart/form-data"),idu);
        RequestBody nusaha = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(namausaha));
        RequestBody no_ktp = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(nomorktp));
        RequestBody desc = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(dekripsi));

    }

    private boolean validateUMKM() {
        String str = nama_usaha.getText().toString().trim();
        if (str.isEmpty()) {
            namausha_lyt.setError(getString(R.string.invalid_name));
            requestFocus(nama_usaha);
            return false;
        } else {
            namausha_lyt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateNomorktp() {
        String str = nomorktp.getText().toString().trim();
        if (str.isEmpty()) {
            nomorktp_lyt.setError(getString(R.string.invalid_name));
            requestFocus(nomorktp);
            return false;
        } else {
            nomorktp_lyt.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDeskripsi() {
        String str = deskripsi_usaha.getText().toString().trim();
        if (str.isEmpty()) {
            deskripsi_lyt.setError(getString(R.string.invalid_name));
            requestFocus(deskripsi_usaha);
            return false;
        } else {
            deskripsi_lyt.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
