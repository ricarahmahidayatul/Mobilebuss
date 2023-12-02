package com.example.travelbuss;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.travelbuss.models.MobilModels;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class rincianmobil extends AppCompatActivity {

    TextView detailnama, detailkursi, detailharga;
    ImageView detailImage;

    String key = "";
    String imageUrl = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincianmobil);

        Query query = FirebaseFirestore.getInstance().collection("Data_Mobil");

        FirestoreRecyclerOptions<MobilModels> option = new FirestoreRecyclerOptions.Builder<MobilModels>()
                .setQuery(query, MobilModels.class)
                .build();
        detailnama = findViewById(R.id.rincimobil);

        detailkursi = findViewById(R.id.rincikursi);
        detailharga  = findViewById(R.id.rinciharga);
        detailImage = findViewById(R.id.rincigmbr);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
//        Toast.makeText(this, bundle.getString("uid"), Toast.LENGTH_SHORT).show();
        if (bundle != null){

            detailnama.setText(bundle.getString("Nama"));
            detailkursi.setText(bundle.getString("Kursi"));
            detailharga.setText(bundle.getString("Harga"));
            Glide.with(this).load(bundle.getString("Gambar")).into(detailImage);




        }
    }
}