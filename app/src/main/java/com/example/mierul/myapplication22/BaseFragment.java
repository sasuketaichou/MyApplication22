package com.example.mierul.myapplication22;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by mierul on 9/15/2017.
 */

public abstract class BaseFragment extends Fragment {

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setTitle(String title){
        ((MainActivity)getActivity()).setTitle(title);
    }

    public void switchFragment(Fragment fragment){
        ((MainActivity)getActivity()).switchFragment(fragment);
    }

    public void switchBackFragment(){
        ((MainActivity)getActivity()).switchBackFragment();
    }

    public void cleanSwitchFragment(Fragment fragment){
        ((MainActivity)getActivity()).cleanSwitchFragment(fragment);
    }


    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void log(String message){
        Log.d("naruto",message);
    }
}
