package com.example.travelbuss;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class detailRiwayat extends AppCompatActivity {
    EditText tvTujuan, tvNama, tvTanggal, tvHari, tvTotal, tvPenyewa, tvTKembali, tvPenjemputan;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_detail_riwayat);
        tvNama = findViewById(R.id.mobil);
        tvTujuan = findViewById(R.id.tujuan);
        tvTanggal = findViewById(R.id.tanggalpinjam);
        tvHari =findViewById(R.id.Totalhari);
        tvTotal = findViewById(R.id.total);
        tvPenyewa = findViewById(R.id.Namabose);
        tvTKembali = findViewById(R.id.tanggalkembali);
        tvPenjemputan = findViewById(R.id.penjemputan);
        Bundle bundle = getIntent().getExtras();
//        Toast.makeText(this, bundle.getString("uid"), Toast.LENGTH_SHORT).show();
        if (bundle != null){

//            tvNama.setText(bundle.getString("namambbil"));
            tvTujuan.setText(bundle.getString("tujuan"));
            tvTotal.setText(bundle.getString("total"));
            tvTanggal.setText(bundle.getString("tanggalpinjam"));
            tvHari .setText(bundle.getString("hari"));
            tvPenyewa .setText(bundle.getString("namapenyewa"));
            tvTKembali .setText(bundle.getString("tanggalkembali"));
            tvPenjemputan .setText(bundle.getString("penjemputan"));



            db.collection("Booking").document(bundle.getString("uid")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    tvHari.setText(documentSnapshot.get("JumlahHari").toString());
                    tvTanggal.setText(documentSnapshot.get("TanggalPinjam").toString());
                    tvTujuan.setText(documentSnapshot.get("Tujuan").toString());
                    tvPenjemputan.setText(documentSnapshot.get("Penjemputan").toString());
                    tvTKembali.setText(documentSnapshot.get("TanggalKembali").toString());
                    tvPenyewa.setText(documentSnapshot.get("NamaPenyewa").toString());
                    tvTotal.setText(documentSnapshot.get("Total").toString());

                    db.collection("Data_Mobil").document(documentSnapshot.get("IDMobil").toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            tvNama.setText(documentSnapshot.get("Nama").toString());
                        }
                    });

                }
            });


        }
    }
}