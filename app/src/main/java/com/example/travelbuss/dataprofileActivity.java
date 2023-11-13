package com.example.travelbuss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class dataprofileActivity extends AppCompatActivity {

    private EditText nama, alamat, nomor;
    ImageButton balek;

    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataprofile);


        nama = findViewById(R.id.editNama);
        alamat = findViewById(R.id.edittAlamat);
        nomor = findViewById(R.id.editNoHp);
        balek = findViewById(R.id.back);
        auth = FirebaseAuth.getInstance();


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference dbReff = db.collection("Akunn").document(auth.getCurrentUser().getUid());
        dbReff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                nama.setText(documentSnapshot.getString("Nama"));
                alamat.setText(documentSnapshot.getString("Alamat"));
                nomor.setText(documentSnapshot.getString("Nohp"));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


            }
        });

        balek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(dataprofileActivity.this, AkunFragment.class);
                startActivity(inten);
            }
        });
    }

}

