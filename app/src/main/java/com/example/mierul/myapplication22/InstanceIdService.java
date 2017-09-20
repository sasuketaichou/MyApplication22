package com.example.mierul.myapplication22;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by mierul on 9/15/2017.
 */

public class InstanceIdService extends FirebaseInstanceIdService {

    private final String TAG = getClass().getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        //save token in db
        new FirebaseEngine().setToken(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //Displaying token on logcat
                    Log.v(TAG, "Token refreshed");
                }
            }
        });
    }
}
