package com.example.mierul.myapplication22;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by mierul on 9/15/2017.
 */

public abstract class BaseFragment extends Fragment {

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
}
