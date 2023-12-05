package com.example.travelbuss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail, editPassword;
    private Button btnLogin;
    private TextView btnRegister;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.Email);
        editPassword = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.Login);
        btnRegister = findViewById(R.id.Register);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(LoginActivity.this, Register.class);
                startActivity(Intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        // Check if the email exists in the "Adminn" collection
                        db.collection("Akunn")
                                .whereEqualTo("Email", email)
                                .get()
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        if (!task.getResult().isEmpty()) {
                                            // Email exists in the "Adminn" collection, proceed with login
                                            mAuth.signInWithEmailAndPassword(email, password)
                                                    .addOnSuccessListener(authResult -> {
                                                        Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                                                        finish();
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        Toast.makeText(LoginActivity.this, "Password salah", Toast.LENGTH_SHORT).show();
                                                    });
                                        } else {
                                            // Email does not exist in the "Adminn" collection
                                            Toast.makeText(LoginActivity.this, "Login Dengan Akun Customer", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Handle the exception if the task is not successful
                                        Toast.makeText(LoginActivity.this, "Gagal memeriksa email", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        editPassword.setError("Password tidak tersedia");
                    }
                } else if (email.isEmpty()) {
                    editEmail.setError("Email tidak tersedia");
                } else {
                    editEmail.setError("Isi email dengan benar");
                }
            }
        });

    }



    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            startActivity(new Intent(LoginActivity.this,NavigationActivity.class));
            finish();
        }
    }


        }

