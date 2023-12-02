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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
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

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String email = editEmail.getText().toString().trim();
//                String password = editPassword.getText().toString().trim();
//
//                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    if (!password.isEmpty()) {
//                        // Memeriksa apakah email ada pada Firestore
//                        DocumentReference docRef = db.collection("Akunn").document(uid);
//                        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                if (documentSnapshot.exists()) {
//                                    // Email ditemukan di Firestore
//                                    mAuth.signInWithEmailAndPassword(email, password)
//                                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                                                @Override
//                                                public void onSuccess(AuthResult authResult) {
//                                                    Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
//                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                                    finish();
//                                                }
//                                            })
//                                            .addOnFailureListener(new OnFailureListener() {
//                                                @Override
//                                                public void onFailure(@NonNull Exception e) {
//                                                    Toast.makeText(LoginActivity.this, "Password salah", Toast.LENGTH_SHORT).show();
//                                                }
//                                            });
//                                } else {
//                                    // Email tidak ditemukan di Firestore
//                                    Toast.makeText(LoginActivity.this, "Gunakan Akun Customer", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // Handle error
//                                Toast.makeText(LoginActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    } else {
//                        editPassword.setError("Password tidak tersedia");
//                    }
//                } else if (email.isEmpty()) {
//                    editEmail.setError("Email tidak tersedia");
//                } else {
//                    editEmail.setError("Isi email dengan benar");
//                }
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnSuccessListener(authResult -> {
//                            DocumentReference checkUser = db.collection("Adminn").document(mAuth.getCurrentUser().getUid());
//                            checkUser.get()
//                                    .addOnSuccessListener(documentSnapshot -> {
//                                        if (documentSnapshot.getBoolean("admin") == true) {
//                                            Log.d("Login", "onCreate: Data adalah " + documentSnapshot.getBoolean("admin"));
//                                            Toast.makeText(LoginActivity.this, "Anda Harus Login Dengan Akun Customer", Toast.LENGTH_SHORT).show();
//                                            mAuth.signOut();
//                                        } else {
//                                            Log.d(this.toString(), "signInWithEmail:success");
//                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                            finish();
//                                        }
////                                        Log.d("Login", "onCreate: Ada Data");
//                                    })
//                                    .addOnFailureListener(e -> {
//                                        Toast.makeText(LoginActivity.this, "Anda Harus Login Dengan Akun Customer", Toast.LENGTH_SHORT).show();
//                                        mAuth.signOut();
//                                    });
//                        })
//                        .addOnFailureListener(e -> {
//                            Log.w(this.toString(), "createUserWithEmail:failure", e);
//                            Toast.makeText(LoginActivity.this, "Email Atau Password Anda Salah", Toast.LENGTH_SHORT).show();
//                            mAuth.signOut();
//                        });


                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(LoginActivity.this, "login berhasil", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "pasword salah", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    } else {
                        editPassword.setError("pasword tidak tersedia");
                    }
                } else if (email.isEmpty()) {
                    editEmail.setError("email tidak tersedia");
                } else {
                    editEmail.setError("isi email dengan benar");
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

