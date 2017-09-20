package com.example.mierul.myapplication22;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by mierul on 9/12/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void addFragment(Fragment fragment){
        switchFragment(fragment,false);
    }

    public void switchFragment(Fragment fragment){
        switchFragment(fragment,true);
    }

    private void switchFragment(Fragment fragment,boolean addToBackStack){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_root,fragment);

        if(addToBackStack){
            transaction.addToBackStack(null);
        }

        transaction.commit();

    }

    public void cleanSwitchFragment(Fragment fragment){
        //clear
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //add
        addFragment(fragment);
    }

    public void switchBackFragment(){

        getSupportFragmentManager()
                .popBackStackImmediate();
    }
}
