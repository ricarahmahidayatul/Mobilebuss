package com.example.travelbuss;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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

public class rincian_booking extends AppCompatActivity {

    private EditText tujuan, tglpnjm, tglkmbli, mobil, bbm, total;
    Button bayar;
    FirebaseAuth Auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_booking);
        bayar = findViewById(R.id.btnbayar);
        tujuan = findViewById(R.id.txtTujuan);
        tglpnjm = findViewById(R.id.txtTglPinjam);
        tglkmbli = findViewById(R.id.txtTglKembali);
        mobil = findViewById(R.id.txtmobil);
        bbm = findViewById(R.id.txtbensin);
        total = findViewById(R.id.txttotal);

        Auth = FirebaseAuth.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference dbReff = db.collection("Booking").document(Auth.getCurrentUser().getUid());
        dbReff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String Tuju = documentSnapshot.getString("Tujuan");
                String TglP= documentSnapshot.getString("TanggalPinjam");
                String TglK = documentSnapshot.getString("TanggalKembali");
                String Mob = documentSnapshot.getString("NamaMobil");
                String Bb = documentSnapshot.getString("BahanBakar");
//                String ttl =documentSnapshot.getString("JumlahHari");
//                String Total = documentSnapshot.getString("Telepon");

                Log.d("dataprofile", "Tujuan" +Tuju);
                Log.d("dataprofile", "tglpinjam" +TglP);
                Log.d("dataprofile", "tglk" +TglK);
                Log.d("dataprofile", "mobil" +Mob);
                Log.d("dataprofile", "bbm" +Bb);
//                Log.d("dataprofile", "total" +ttl);

                tujuan.setText(Tuju);
                tglpnjm.setText(TglP);
                tglkmbli.setText(TglK);
                mobil.setText(Mob);
                bbm.setText(Bb);
//                total.setText(ttl);
                tujuan.setText(getIntent().getStringExtra("Tujuan"));
                tglpnjm.setText(getIntent().getStringExtra("TanggalPinjam"));
                tglkmbli.setText(getIntent().getStringExtra("TanggalKembali"));
                mobil.setText(getIntent().getStringExtra("NamaMobil"));
                bbm.setText(getIntent().getStringExtra("BahanBakar"));
//                total.setText(getIntent().getStringExtra("JumlahHari"));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        }
    }
