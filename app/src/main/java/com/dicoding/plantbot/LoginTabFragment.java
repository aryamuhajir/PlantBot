package com.dicoding.plantbot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment {

    Context context;
    EditText email;
    EditText password;
    TextView forgetPassword;
    Button login;
    FirebaseAuth fAuth;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        this.context = context;
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        forgetPassword = view.findViewById(R.id.forget_password);
        login = view.findViewById(R.id.login);

        fAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email = email.getText().toString().trim();
                String Pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(Email)){
                    email.setError("Username diperlukan");
                    return;
                }

                if (TextUtils.isEmpty(Pass)){
                    password.setError("Password diperlukan");
                    return;
                }

                if (Pass.length() < 10){
                    password.setError("Password harus >= 10 karakter");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(context, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
                        }else{
                            Toast.makeText(context, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        email.setTranslationY(800);
        password.setTranslationY(800);
        forgetPassword.setTranslationY(800);
        login.setTranslationY(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgetPassword.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return view;
    }

}