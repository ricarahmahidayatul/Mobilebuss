package com.example.travelbuss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class dataprofileActivity extends AppCompatActivity {

    private EditText  editalamat, editnomor;
    TextView editnama,editemail;
    ImageButton balek;
    Button simpn;

    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataprofile);


        editnama = findViewById(R.id.labelnama);
        editalamat = findViewById(R.id.edittAlamat);
        editemail = findViewById(R.id.editEmail);
        editnomor = findViewById(R.id.editNoHp);
        balek = findViewById(R.id.back);
        auth = FirebaseAuth.getInstance();
//        simpn = findViewById(R.id.buttonsimpan);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference dbReff = db.collection("Akunn").document(auth.getCurrentUser().getUid());
        dbReff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String nama = documentSnapshot.getString("Nama");
                String email = documentSnapshot.getString("Email");
                String alamat = documentSnapshot.getString("Alamat");
                String nomor = documentSnapshot.getString("Nohp");

                Log.d("dataprofileActivity", "Nama" +nama);
                Log.d("dataprofileActivity", "Email" +email);
                Log.d("dataprofileActivity", "Alamat" +alamat);
                Log.d("dataprofileActivity", "Nohp" +nomor);

                editnama.setText(nama);
                editemail.setText(email);
                editalamat.setText(alamat);
                editnomor.setText(nomor);


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
                finish();
            }
        });

//        simpn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String nam = editnama.getText().toString().trim();
//                String eml= editemail.getText().toString().trim();
//                String ALAMAT = editalamat.getText().toString().trim();
//                String Telpn = editnomor.getText().toString().trim();
//
//
//                Map<String, Object> user = new HashMap<>();
//                user.put("Nama", nam);
//                user.put("Email",eml);
//                user.put("Alamat", ALAMAT);
//                user.put("Telepon", Telpn);
//
//
//                DocumentReference dbReff = db.collection("Akunn").document(auth.getUid());
//                dbReff.update(user);
//
//
//
//
//            }
//
//        });



    }

}

