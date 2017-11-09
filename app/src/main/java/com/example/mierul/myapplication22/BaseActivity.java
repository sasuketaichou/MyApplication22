package com.example.mierul.myapplication22;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

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

    protected void switchFragment(Fragment fragment, boolean addToBackStack) {

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
}
