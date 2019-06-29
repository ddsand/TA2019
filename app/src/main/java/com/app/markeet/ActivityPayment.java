package com.app.markeet;

import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.markeet.utils.CallbackDialog;
import com.app.markeet.utils.DialogUtils;
import com.app.markeet.utils.Tools;
import com.balysv.materialripple.MaterialRippleLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityPayment extends ExpandableListActivity {
    private TextView nomorva,totalharga;
    private ImageButton salin;
    private ExpandableListAdapter mAdapterView;
    private MaterialRippleLayout btnOK;
    android.widget.ExpandableListView expandableListView;
    Toolbar toolbar;
    private Toolbar supportActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initToolbar();

        final String billkey= getIntent().getStringExtra("bill_key");
        final String billcode= getIntent().getStringExtra("bill_code");
        String total = getIntent().getStringExtra("totalfees");
        btnOK = (MaterialRippleLayout) findViewById(R.id.lyt_pay);

        nomorva = (TextView) findViewById(R.id.nomorva);
        totalharga = (TextView) findViewById(R.id.totalpay);
        salin = (ImageButton) findViewById(R.id.copyva);

        nomorva.setText(billkey); totalharga.setText(total);
        salin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.copyToClipboard(getApplicationContext(), billkey);
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSuccess();
            }
        });
        ExpandableList();
    }
    private void initToolbar() {
        ActionBar actionBar;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Payment Method");
        Tools.systemBarLolipop(this);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }
    public void ExpandableList(){
        List<Map<String, String>> groupListItem = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childListItem = new ArrayList<List<Map<String, String>>>();

        /* ******************** Group item 1 ********************* */
        Map<String, String> group1 = new HashMap<>();
        groupListItem.add(group1);
        group1.put("parentItem", "Test");

        List<Map<String, String>> children1 = new ArrayList<Map<String, String>>();
        Map<String, String> childrenitem1 = new HashMap<String, String>();
        children1.add(childrenitem1);
        childrenitem1.put("childItem", "ListView Example");

        Map<String, String> childrenitem2 = new HashMap<String, String>();
        children1.add(childrenitem2);
        childrenitem2.put("childItem", "ListView Tutorial");

        Map<String, String> childrenitem3 = new HashMap<String, String>();
        children1.add(childrenitem3);
        childrenitem3.put("childItem", "Expandable ListView");
        childListItem.add(children1);

        /* ******************** Group Item 2  ********************* */
        Map<String, String> childrenitem6 = new HashMap<String, String>();
        groupListItem.add(childrenitem6);
        childrenitem6.put("parentItem", "Android Expandable ListView");
        List<Map<String, String>> children2 = new ArrayList<Map<String, String>>();

        Map<String, String> childrenitem7 = new HashMap<String, String>();
        children2.add(childrenitem7);
        childrenitem7.put("childItem", "Android ListView");

        Map<String, String> childrenitem8 = new HashMap<String, String>();
        children2.add(childrenitem8);
        childrenitem8.put("childItem", "Expandable ListView");

        Map<String, String> child8 = new HashMap<String, String>();
        children2.add(child8);
        child8.put("childItem", "ListView Example");
        childListItem.add(children2);


        /* ******************** Group Item 3  ********************* */
        Map<String, String> childrenitem9 = new HashMap<String, String>();
        groupListItem.add(childrenitem9);
        childrenitem9.put("parentItem", "Expandable ListView Tutorial");
        List<Map<String, String>> children3 = new ArrayList<Map<String, String>>();

        Map<String, String> childrenitem10 = new HashMap<String, String>();
        children3.add(childrenitem10);
        childrenitem10.put("childItem", "Android ListView");

        Map<String, String> childrenitem11 = new HashMap<String, String>();
        children3.add(childrenitem11);
        childrenitem11.put("childItem", "Expandable ListView");

        Map<String, String> childrenitem12 = new HashMap<String, String>();
        children3.add(childrenitem12);
        childrenitem12.put("childItem", "ListView Example");
        childListItem.add(children3);

        mAdapterView = new SimpleExpandableListAdapter(
                this,
                groupListItem,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"parentItem"},
                new int[]{android.R.id.text1, android.R.id.text2},
                childListItem,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{"childItem"},
                new int[]{android.R.id.text1}
        );
        setListAdapter(mAdapterView);
        expandableListView = getExpandableListView();
        expandableListView.setOnChildClickListener(new android.widget.ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(android.widget.ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                Toast.makeText(getBaseContext(), "ListView Example",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(getBaseContext(), "ListView Tutorial",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(getBaseContext(), "Expandable ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                Toast.makeText(getBaseContext(), "Android ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(getBaseContext(), "Expandable ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(getBaseContext(), "Expandable ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                        break;
                    case 2:
                        switch (childPosition) {
                            case 0:
                                Toast.makeText(getBaseContext(), "Android ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(getBaseContext(), "Expandable ListView",
                                        Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(getBaseContext(), "ListView Example",
                                        Toast.LENGTH_LONG).show();
                                break;
                        }
                        break;
                }
                return false;
            }
        });
    }

    public void dialogSuccess() {
        Dialog dialog = new DialogUtils(this).buildDialogInfo(
                getString(R.string.success_checkout),
                getString(R.string.store_picture_message),
                getString(R.string.OK),
                R.drawable.img_checkout_success,
                new CallbackDialog() {
                    @Override
                    public void onPositiveClick(Dialog dialog) {
                        Intent i = new Intent(ActivityPayment.this,ActivityMain.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onNegativeClick(Dialog dialog) {
                    }
                });
        dialog.show();
    }
}
