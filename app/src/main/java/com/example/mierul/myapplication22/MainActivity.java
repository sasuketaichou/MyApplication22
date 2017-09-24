package com.example.mierul.myapplication22;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    private FrameLayout fl_root;
    private Toolbar toolbar;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                mDrawer,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });

        FirebaseEngine firebaseEngine = new FirebaseEngine();

        //set view for login or home
        if(firebaseEngine.isLogin()){
            initHome();
        } else {
            //show login page
            initLogin();
        }
    }

    private void selectDrawerItem(MenuItem menuItem) {

        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:

                break;
            case R.id.nav_second_fragment:

                break;
            case R.id.nav_third_fragment:

                break;
            }
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        toolbar = (Toolbar)findViewById(R.id.layout_toolbar);
        setSupportActionBar(toolbar);

        fl_root = (FrameLayout)findViewById(R.id.fl_root);

    }

    private void initHome() {
        addFragment(HomeFragment.newInstance());
    }

    private void initLogin(){
        addFragment(LoginFragment.newInstance());
    }


}
