package com.example.mierul.myapplication22;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by mierul on 9/15/2017.
 */

public class FirebaseEngine {

    public void signInWithEmailAndPassword(Context context, String user, String pass,OnCompleteListener<AuthResult> completeListener){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user,pass)
                .addOnCompleteListener((Activity)context,completeListener);
    }
}
