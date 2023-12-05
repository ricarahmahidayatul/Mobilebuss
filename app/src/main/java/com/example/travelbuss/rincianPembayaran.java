package com.example.travelbuss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class rincianPembayaran extends AppCompatActivity {

    private EditText tujuan, tglpnjm, tglkmbli, mobil, bbm;
    Button bayar;

    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_pembayaran);



        bayar = findViewById(R.id.btnbayar);
        tujuan= findViewById(R.id.txtTujuan);


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
                tujuan.setText(documentSnapshot.getString("Tujuan"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
}