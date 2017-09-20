package com.example.mierul.myapplication22;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * Created by mierul on 9/20/2017.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = LoginFragment.class.getSimpleName();

    private FirebaseEngine firebaseEngine;

    private EditText username,password;
    private TextView tv_error;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle arg = new Bundle();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseEngine = new FirebaseEngine();
        setTitle("Login");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);

        username = (EditText)view.findViewById(R.id.input_username);
        password = (EditText)view.findViewById(R.id.input_password);

        view.findViewById(R.id.link_forgot_password).setOnClickListener(this);

        tv_error = (TextView)view.findViewById(R.id.tv_login_error);

        Button buttonLogin = (Button)view.findViewById(R.id.btn_login);
        buttonLogin.setOnClickListener(this);

        Button buttonRegister = (Button)view.findViewById(R.id.btn_register);
        buttonRegister.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLayoutListener();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_login:
                validate();
                break;
            case R.id.btn_register:
                //register();
                break;
            case R.id.link_forgot_password:
                //showResetDialog();
                break;
        }

    }

    private void validate(){

        if(validateForm()){

            String email = username.getText().toString();
            String pass = password.getText().toString();

            if(tv_error.getVisibility() == View.VISIBLE){
                tv_error.setVisibility(View.GONE);
            }

            showProgressDialog();

            firebaseEngine.signInWithEmailAndPassword(getActivity(),
                    email,
                    pass,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            hideProgressDialog();

                            if(task.isSuccessful()){
                                //proceed to home page
                                cleanSwitchFragment(HomeFragment.newInstance());
                            } else {
                                Log.e(TAG,task.getException().toString());
                            }

                        }
                    });

        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = username.getText().toString();
        if (TextUtils.isEmpty(email)) {
            username.setError("Required.");
            valid = false;
        } else {
            username.setError(null);
        }

        String pass = password.getText().toString();
        if (TextUtils.isEmpty(pass)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    private void initLayoutListener() {
        getView().findViewById(R.id.loginLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SoftKeyboardUtil skbUtil = new SoftKeyboardUtil(getActivity());
                skbUtil.hideSoftKeyboard();
                return true;
            }
        });

    }


}
