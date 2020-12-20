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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpTabFragment extends Fragment {

    Context context;
    EditText username;
    EditText password;
    EditText namaLengkap;
    EditText email;
    Button signUp;
    FirebaseAuth fAuth;
    float v=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_tab, container, false);

        this.context = context;
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        namaLengkap = view.findViewById(R.id.nama_lengkap);
        email = view.findViewById(R.id.email);
        signUp = view.findViewById(R.id.signUp);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
            getActivity().finish();
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(Email)){
                    email.setError("Email diperlukan");
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

                // mendaftarkan user ke firebase
                fAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(context, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
                        }else{
                            Toast.makeText(context, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        username.setTranslationY(800);
        password.setTranslationY(800);
        namaLengkap.setTranslationY(800);
        email.setTranslationY(800);
        signUp.setTranslationY(800);

        username.setAlpha(v);
        password.setAlpha(v);
        namaLengkap.setAlpha(v);
        email.setAlpha(v);
        signUp.setAlpha(v);

        username.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        namaLengkap.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();
        signUp.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return view;
    }

}