package com.example.mierul.myapplication22;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by mierul on 9/20/2017.
 */

public class SoftKeyboardUtil {

    private Activity activity;

    public SoftKeyboardUtil(Activity activity){
        this.activity = activity;
    }

    private InputMethodManager inputMethodManager;

    private InputMethodManager getInputMethodManager(){
        if(inputMethodManager == null){
            inputMethodManager =(InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
        }
        return inputMethodManager;
    }

    public void hideSoftKeyboard(){
        if(activity.getCurrentFocus() != null){
            getInputMethodManager()
                    .hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            //.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }

    public void showSoftKeyboard(){
        if(getInputMethodManager().isActive()){
            getInputMethodManager()
                    //.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
                    .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        }
    }

    public void close(){
        activity = null;
    }
}
