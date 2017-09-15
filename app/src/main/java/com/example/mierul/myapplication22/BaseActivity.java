package com.example.mierul.myapplication22;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by mierul on 9/12/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private FrameLayout fl_root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        Toolbar toolbar = (Toolbar)findViewById(R.id.layout_toolbar);
        setSupportActionBar(toolbar);

        fl_root = (FrameLayout)findViewById(R.id.fl_root);

        initHome();

    }

    private void initHome() {

        View view_home = View.inflate(this,R.layout.view_home,null);

        Button btn_home = (Button) view_home.findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new ScanFragment());
            }
        });

        fl_root.addView(view_home);
    }

    public void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void switchFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_root,fragment)
                .addToBackStack(null)
                .commit();

    }

    public void switchBackFragment(){

        getSupportFragmentManager()
                .popBackStackImmediate();
    }
}
