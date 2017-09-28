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

public class FirebaseEngine extends BaseFirebase {

    public void signInWithEmailAndPassword(Context context, String user, String pass,OnCompleteListener<AuthResult> completeListener){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user,pass)
                .addOnCompleteListener((Activity)context,completeListener);
    }

    public void setToken(OnCompleteListener<Void> onCompleteListener){

        //get ref token
        DatabaseReference token_ref = super.getTokenRef();
        //get token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Map<String,String> token_map = new HashMap<>();
        token_map.put(CHILD_TOKEN,refreshedToken);
        token_ref.setValue(token_map).addOnCompleteListener(onCompleteListener);
    }

    private void clearToken(OnCompleteListener<Void> onCompleteListener){
        //get token
        DatabaseReference tokenRef = super.getTokenRef();
        tokenRef.removeValue().addOnCompleteListener(onCompleteListener);
    }

    public boolean isLogin() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        return mAuth.getCurrentUser() != null;
    }

    public void signOut(OnCompleteListener<Void> onCompleteListener){
        //clear token
        clearToken(onCompleteListener);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }

    public void getListOrder(long lastKey, ValueEventListener valueEventListener){

        DatabaseReference order = super.getOrdersRef();

        int limitTofirst = 5;

        if(lastKey != 0){
            order.orderByChild("timestamp")
                    .startAt(lastKey)
                    .limitToFirst(limitTofirst)
                    .addListenerForSingleValueEvent(valueEventListener);
        } else {

            order.orderByChild("timestamp")
                    .limitToFirst(limitTofirst)
                    .addListenerForSingleValueEvent(valueEventListener);
        }
    }
}
