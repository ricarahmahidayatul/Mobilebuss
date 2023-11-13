package com.example.travelbuss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class rincianPembayaran extends AppCompatActivity {

    private EditText penjemputan, nama, alamat, nomor;
    Button bayar;

    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_pembayaran);



        bayar = findViewById(R.id.btnbayar);
        penjemputan = findViewById(R.id.txtPenjemputan);
//        alamat = findViewById(R.id.edittAlamat);
//        nomor = findViewById(R.id.editNoHp);

        Auth = FirebaseAuth.getInstance();

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(rincianPembayaran.this, NavigationActivity.class);
                startActivity(Intent);
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference dbReff = db.collection("Booking").document(Auth.getCurrentUser().getUid());

        dbReff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                penjemputan.setText(documentSnapshot.getString("Penjemputan"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}