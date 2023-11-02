package com.example.travelbuss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
private EditText editEmail, editPassword, editNohp;
private Button btnRegister;
private TextView bntLogin;
private FirebaseAuth mAuth;


    public Register() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editEmail = findViewById(R.id.usernameRes);
        editPassword = findViewById(R.id.passworRes);
        editNohp = findViewById(R.id.nomortelefonRes);
        btnRegister = findViewById(R.id.button2);
        bntLogin = findViewById(R.id.tulisanlogin);
        mAuth = FirebaseAuth.getInstance();



        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent=new Intent(Register.this, LoginActivity.class);
                startActivity(Intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String nohp = editNohp.getText().toString().trim();

                if(email.isEmpty()){
                    editEmail.setError("email tidak boleh kosong");
            } else if (password.isEmpty()) {
                    editPassword.setError("pasword tidak boleh kosong");
                }else{
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this,"registrasi sukses", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, LoginActivity.class));
                            }else{
                                Toast.makeText(Register.this, "registergagal" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



    }
}