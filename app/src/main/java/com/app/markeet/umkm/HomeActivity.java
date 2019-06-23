package com.app.markeet.umkm;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.app.markeet.ActivityInstruction;
import com.app.markeet.ActivityMain;
import com.app.markeet.ActivityNotification;
import com.app.markeet.ActivityOrderHistory;
import com.app.markeet.ActivitySearch;
import com.app.markeet.ActivitySettings;
import com.app.markeet.ActivityShoppingCart;
import com.app.markeet.ActivityWishlist;
import com.app.markeet.LoginActivity;
import com.app.markeet.R;
import com.app.markeet.ScannerActivity;
import com.app.markeet.connection.API;
import com.app.markeet.connection.RestAdapter;
import com.app.markeet.data.DatabaseHandler;
import com.app.markeet.data.SharedPref;
import com.app.markeet.fragment.FragmentCategory;
import com.app.markeet.fragment.FragmentFeaturedNews;
import com.app.markeet.fragment.FragmentUmkm;
import com.app.markeet.utils.CallbackDialog;
import com.app.markeet.utils.DialogUtils;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private Toolbar toolbar;
    private CardView search_bar;
    private SwipeRefreshLayout swipe_refresh;
    private View parent_view;
    private NavigationView nav_view;
    private DatabaseHandler db;
    private SharedPref sharedPref;
    private Dialog dialog_failed = null;
    public boolean news_load = false;

    static HomeActivity homeActivity;

    public static HomeActivity getInstance(){return homeActivity;}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeActivity = this;
        db = new DatabaseHandler(this);
        sharedPref = new SharedPref(this);

        initToolbar();
        initDrawerMenu();
        initComponent();
        //initFragment();
       // swipeProgress(true);

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(R.string.app_name);
    }

    private void initDrawerMenu() {
        nav_view = (NavigationView) findViewById(R.id.nav_umkm);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_umkm);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                //showInterstitial();
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {

                onItemSelected(item.getItemId());

                //drawer.closeDrawers();
                return true;
            }
        });
        nav_view.setItemIconTintList(getResources().getColorStateList(R.color.nav_state_list));
    }

    public void processLogout(){
        String iduser = sharedPref.getSPIdUser().toString();
        int id_user = Integer.valueOf(iduser);
        API api = RestAdapter.createAPI();
        api.logoutRequest(iduser).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                if(responseBody!=null){
                    try{
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String status = jsonObject.getString("success");
                        //String name = jsonObject.getString("name");
                        if(status.equals("Berhasil Log Out")){
                            sharedPref.saveBoolean(sharedPref.SP_SUDAH_LOGIN, false);
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        }
//                        else if(name.equals("UnauthorizedError")){
//                            sharedPref.saveBoolean(sharedPref.SP_SUDAH_LOGIN, false);
//                            startActivity(new Intent(ActivityMain.this, LoginActivity.class)
//                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                            finish();
//                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Failed to Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onItemSelected(int id) {
        Intent i;
        switch (id) {
            //sub menu
            case R.id.nav_profile:
                i = new Intent(this, ActivityUmkm.class);
                startActivity(i);
                finish();
                break;
            case R.id.nav_scan:
                i = new Intent(this, ScannerActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.nav_history:
                startActivity(new Intent(this,HistoryActivity.class));
                finish();
                break;
            case R.id.nav_notif:
                Toast.makeText(this, "Notif Transaction", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_instruction:
                startActivity(new Intent(this,ActivityInstruction.class));
                finish();
                break;
            case R.id.nav_setting:
                startActivity(new Intent(this,ActivitySettings.class));
                finish();
                break;
            case R.id.nav_logout:
//                sharedPref.saveBoolean(sharedPref.SP_SUDAH_LOGIN, false);
//                startActivity(new Intent(this, LoginActivity.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                finish();
                processLogout();
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawers();
        return true;
    }
    private void initComponent() {
        parent_view = findViewById(R.id.parentumkm);
        search_bar = (CardView) findViewById(R.id.search_bar);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        NestedScrollView nested_content = (NestedScrollView) findViewById(R.id.nested_content);
        nested_content.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < oldScrollY) { // up
                    animateSearchBar(false);
                }
                if (scrollY > oldScrollY) { // down
                    animateSearchBar(true);
                }
            }
        });
        /*
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFragment();
            }
        });*/

        ((ImageButton) findViewById(R.id.action_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivitySearch.navigate(HomeActivity.this);
            }
        });
    }
    private void refreshFragment() {
        swipeProgress(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initFragment();
            }
        }, 500);
    }

    private void swipeProgress(final boolean show) {
        if (!show) {
            swipe_refresh.setRefreshing(show);
            return;
        }
        swipe_refresh.post(new Runnable() {
            @Override
            public void run() {
                swipe_refresh.setRefreshing(show);
            }
        });
    }
    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // init fragment slider new product
        FragmentUmkm fragmentumkm = new FragmentUmkm();
        fragmentTransaction.replace(R.id.frame_content_new_product, fragmentumkm);

        fragmentTransaction.commit();
    }

    boolean isSearchBarHide = false;

    private void animateSearchBar(final boolean hide) {
        if (isSearchBarHide && hide || !isSearchBarHide && !hide) return;
        isSearchBarHide = hide;
        int moveY = hide ? -(2 * search_bar.getHeight()) : 0;
        search_bar.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    static boolean active = false;

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        active = false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_umkm);
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        } else {
            doExitApp();
        }
    }

    private long exitTime = 0;
    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


    public void showDataLoaded() {
        if (news_load) {
            swipeProgress(false);
            //Snackbar.make(parent_view, R.string.msg_data_loaded, Snackbar.LENGTH_SHORT).show();
        }
    }

    public void showDialogFailed(@StringRes int msg) {
        if (dialog_failed != null && dialog_failed.isShowing()) return;
        swipeProgress(false);
        dialog_failed = new DialogUtils(this).buildDialogWarning(-1, msg, R.string.TRY_AGAIN, R.drawable.img_no_connect, new CallbackDialog() {
            @Override
            public void onPositiveClick(Dialog dialog) {
                dialog.dismiss();
                refreshFragment();
            }

            @Override
            public void onNegativeClick(Dialog dialog) {
            }
        });
        dialog_failed.show();
    }


}
