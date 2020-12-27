package com.dicoding.plantbot.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dicoding.plantbot.ForgotPassword;
import com.dicoding.plantbot.HomeActivity;
import com.dicoding.plantbot.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment implements View.OnClickListener {

    private EditText email, password;
    private TextView forgotPassword;
    private Button login;

    TextInputLayout inputLayout, inputLayout1;
    FirebaseAuth fAuth;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        forgotPassword = view.findViewById(R.id.forget_password);
        login = view.findViewById(R.id.login);
        login.setOnClickListener(this);
        inputLayout = view.findViewById(R.id.inputLayout);
        inputLayout1 = view.findViewById(R.id.inputLayout1);

        fAuth = FirebaseAuth.getInstance();

        forgotPassword = view.findViewById(R.id.forget_password);
        forgotPassword.setOnClickListener(this);

        email.setTranslationY(800);
        password.setTranslationY(800);
        forgotPassword.setTranslationY(800);
        login.setTranslationY(800);
        inputLayout.setTranslationY(800);
        inputLayout1.setTranslationY(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgotPassword.setAlpha(v);
        login.setAlpha(v);
        inputLayout.setAlpha(v);
        inputLayout1.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        inputLayout.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        inputLayout1.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forget_password:
                startActivity(new Intent(LoginTabFragment.this.getActivity(), ForgotPassword.class));
                break;
            case R.id.login:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String Email = email.getText().toString().trim();
        String Pass = password.getText().toString().trim();

        if (Email.isEmpty()){
            email.setError("Email diperlukan");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Email tidak valid");
            email.requestFocus();
            return;
        }

        if (Pass.isEmpty()){
            password.setError("Password diperlukan");
            password.requestFocus();
            return;
        }

        if (Pass.length() < 10){
            password.setError("Password harus >= 10 karakter");
            password.requestFocus();
            return;
        }

        fAuth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        startActivity(new Intent(LoginTabFragment.this.getActivity(), HomeActivity.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginTabFragment.this.getActivity(), "Cek verifikasi email anda", Toast.LENGTH_LONG).show();
                    }
                    //redirect to user profile
                    startActivity(new Intent(LoginTabFragment.this.getActivity(), HomeActivity.class));
                }else{
                    Toast.makeText(LoginTabFragment.this.getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}