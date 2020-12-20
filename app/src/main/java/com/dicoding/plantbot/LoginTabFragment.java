package com.dicoding.plantbot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {

    EditText username;
    EditText password;
    TextView forgetPassword;
    Button login;
    float v=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        forgetPassword = view.findViewById(R.id.forget_password);
        login = view.findViewById(R.id.login);

        username.setTranslationY(800);
        password.setTranslationY(800);
        forgetPassword.setTranslationY(800);
        login.setTranslationY(800);

        username.setAlpha(v);
        password.setAlpha(v);
        forgetPassword.setAlpha(v);
        login.setAlpha(v);

        username.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return view;
    }

}