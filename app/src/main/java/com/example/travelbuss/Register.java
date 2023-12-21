package com.example.travelbuss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
private EditText editEmail, editPassword;
private Button btnRegister;
private TextView bntLogin;
private EditText nama, nohp, alamat;
private FirebaseAuth mAuth;
    private FirebaseFirestore db;


//    public Register() {
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editEmail = findViewById(R.id.usernameRes);
        editPassword = findViewById(R.id.passworRes);

        nohp = findViewById(R.id.nomortelefonRes);
        nama = findViewById(R.id.Nama);
        alamat = findViewById(R.id.Alamat);

        btnRegister = findViewById(R.id.button2);
        bntLogin = findViewById(R.id.tulisanlogin);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();



        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent=new Intent(Register.this, LoginActivity.class);
                startActivity(Intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String nomor = nohp.getText().toString().trim();
                String name = nama.getText().toString().trim();
                String almt = alamat.getText().toString().trim();

                if(email.isEmpty()){
                    editEmail.setError("email tidak boleh kosong");
            } else if (password.isEmpty()) {
                    editPassword.setError("pasword tidak boleh kosong");
                }else{
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Map<String, Object> user = new HashMap<>();
                                user.put("Email", email);
                                user.put("Nohp", nomor);
                                user.put("Nama", name);
                                user.put("Alamat", almt);

                                DocumentReference dbReff = db.collection("Akunn").document(mAuth.getUid());
                                dbReff.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                });
                                Toast.makeText(Register.this,"registrasi sukses", Toast.LENGTH_SHORT).show();
                                editEmail.setText("");
                                editPassword.setText("");
                                nohp.setText("");
                                alamat.setText("");
                                nama.setText("");
                                if (task.isSuccessful()) {
                                    Intent Intent = new Intent(Register.this, LoginActivity.class);
                                    startActivity(Intent);

                                }


//                                Intent Intent=new Intent(Register.this, LoginActivity.class);
//                                startActivity(Intent);
//

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