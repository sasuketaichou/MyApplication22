package com.example.mierul.myapplication22;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hexa-Amierul.Japri on 25/9/2017.
 */

public abstract class BaseFirebase {

    private final String ROOT_USERS = "users";
    private final String ROOT_PRODUCT = "product";
    private final String ROOT_ORDERS = "orders";
    private final String ROOT_NOTIFICATION = "notification";
    public final String CHILD_TOKEN = "token";
    private final String CHILD_PROFILE = "Profile";
    private final String CHILD_URL = "url";
    private final String CHILD_ORDER = "Order";
    private final String CHILD_IMAGE = "Image";
    private final String CHILD_ADDRESS = "address";

    public final static int SIGNINEMAILPASSWORD = 1;
    public final static int CREATEUSEREMAILPASSWORD = 2;
    public final static int RESETPASSWORD = 3;


    private DatabaseReference getRootRef(){
        return FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getUsersRef(){
        String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return getRootRef().child(ROOT_USERS).child(uId);
    }

    public DatabaseReference getNotiRef(){
        return getRootRef().child(ROOT_NOTIFICATION);
    }

    public DatabaseReference getTokenRef(){
        String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return getNotiRef().child(uId);
    }

    public DatabaseReference getOrdersRef(){
        return getRootRef().child(ROOT_ORDERS);
    }

    private DatabaseReference getUsersProfileRef(){
        return getUsersRef().child(CHILD_PROFILE);
    }

    private DatabaseReference getUsersAddressRef(){
        return getUsersProfileRef().child(CHILD_ADDRESS);
    }

    private DatabaseReference getUsersOrderRef(){
        return getUsersRef().child(CHILD_ORDER);
    }
}
