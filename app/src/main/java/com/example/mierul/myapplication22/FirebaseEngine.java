package com.example.mierul.myapplication22;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mierul on 9/15/2017.
 */

public class FirebaseEngine {

    public void signInWithEmailAndPassword(Context context, String user, String pass,OnCompleteListener<AuthResult> completeListener){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user,pass)
                .addOnCompleteListener((Activity)context,completeListener);
    }

    public void setToken(OnCompleteListener<Void> onCompleteListener){

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        //get child
        DatabaseReference token = root.child("notification");
        //get token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //child
        String child_node = "token";
        //getuid
        String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Map<String,String> token_map = new HashMap<>();
        token_map.put(child_node,refreshedToken);
        token.child(uId).setValue(token_map).addOnCompleteListener(onCompleteListener);

    }

    public boolean isLogin() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        return mAuth.getCurrentUser() != null;
    }

    public void signOut(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }

    public void getListOrder(String lastKey, ValueEventListener valueEventListener){

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();

        DatabaseReference order = root.child("orders");

        int limitTofirst = 10;

        if(!lastKey.isEmpty()){
            order.startAt(lastKey)
                    .limitToFirst(limitTofirst)
                    .addListenerForSingleValueEvent(valueEventListener);
        } else {

            order.limitToFirst(limitTofirst)
                    .addListenerForSingleValueEvent(valueEventListener);
        }
    }
}
