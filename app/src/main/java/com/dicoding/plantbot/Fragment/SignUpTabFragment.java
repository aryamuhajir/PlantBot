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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dicoding.plantbot.HomeActivity;
import com.dicoding.plantbot.LoginActivity;
import com.dicoding.plantbot.R;
import com.dicoding.plantbot.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpTabFragment extends Fragment implements View.OnClickListener{

    EditText username;
    EditText password;
    EditText namaLengkap;
    EditText email;
    Button signUp;
    TextInputLayout inputLayout2, inputLayout3, inputLayout4, inputLayout5;
    private FirebaseAuth fAuth;
    float v=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up_tab, container, false);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        namaLengkap = view.findViewById(R.id.nama_lengkap);
        email = view.findViewById(R.id.email);
        signUp = view.findViewById(R.id.signUp);
        signUp.setOnClickListener(this);
        inputLayout2 = view.findViewById(R.id.inputLayout2);
        inputLayout3 = view.findViewById(R.id.inputLayout3);
        inputLayout4 = view.findViewById(R.id.inputLayout4);
        inputLayout5 = view.findViewById(R.id.inputLayout5);

        fAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = email.getText().toString().trim();
                String Pass = password.getText().toString().trim();
                final String Naleng = namaLengkap.getText().toString().trim();
                final String Uname = username.getText().toString().trim();

                if (TextUtils.isEmpty(Email)){
                    email.setError("Email diperlukan");
                    email.requestFocus();
                    return;
                }
                
                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    email.setError("Email tidak valid");
                    email.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Pass)){
                    password.setError("Password diperlukan");
                    return;
                }

                if (TextUtils.isEmpty(Naleng)){
                    namaLengkap.setError("Nama lengkap diperlukan");
                    return;
                }

                if (TextUtils.isEmpty(Uname)){
                    username.setError("username diperlukan");
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
                            User user = new User(Naleng, Uname, Email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUpTabFragment.this.getActivity(), "Akun berhasil dibuat", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
                                    }else {
                                        Toast.makeText(SignUpTabFragment.this.getActivity(), "Gagal mendaftar, coba ulang", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUpTabFragment.this.getActivity(), "Gagal mendaftar, coba ulang", Toast.LENGTH_LONG).show();
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
        inputLayout2.setTranslationY(800);
        inputLayout3.setTranslationY(800);
        inputLayout4.setTranslationY(800);
        inputLayout5.setTranslationY(800);

        username.setAlpha(v);
        password.setAlpha(v);
        namaLengkap.setAlpha(v);
        email.setAlpha(v);
        signUp.setAlpha(v);
        inputLayout2.setAlpha(v);
        inputLayout3.setAlpha(v);
        inputLayout4.setAlpha(v);
        inputLayout5.setAlpha(v);

        username.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        namaLengkap.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        inputLayout2.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        inputLayout3.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        inputLayout4.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        inputLayout5.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signUp.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}