package com.example.travelbuss.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuss.R;
import com.example.travelbuss.detailRiwayat;
import com.example.travelbuss.models.RiwayatModels;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdapterRiwayat extends FirestoreRecyclerAdapter<RiwayatModels, AdapterRiwayat.ViewHolder> {
    Context context;
    public AdapterRiwayat(FirestoreRecyclerOptions<RiwayatModels> options, Context context) {super(options); this.context = context;}
    @Override

    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull RiwayatModels model) {
        Log.d("Bind", "onBindViewHolder: " + "namamobil" +model.getNamaMobil() + "tujuan" +model.getTujuan() +"pinjam" + model.getTanggalPinjam());



        holder.namamobil.setText(model.getNamaMobil());
        holder.tuju.setText(model.getTujuan());
        holder.pinjam.setText(model.getTanggalPinjam());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("Data_Mobil").document(model.getDocument("IDMobil").toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                namamobil.setText(documentSnapshot.get("Nama").toString());
//            }
//        });

        holder.inti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, detailRiwayat.class);

                intent.putExtra("tujuan",model.getTujuan());
                intent.putExtra("tanggalpinjam",model.getTanggalPinjam());
                intent.putExtra("hari",model.getHari());
                intent.putExtra("total",model.getTotal());
                intent.putExtra("uid", model.getDocumentId().toString());



                context.startActivity(intent);
            }
        });
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mobilView = inflater.inflate(R.layout.activity_list_riwayat,parent,false);
        ViewHolder viewHolder = new ViewHolder(mobilView);
        return viewHolder;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namamobil, tuju, pinjam;
        RelativeLayout inti;

        public ViewHolder(View itemView) {
            super(itemView);
            namamobil = itemView.findViewById(R.id.namamobil);
            tuju = itemView.findViewById(R.id.tuju);
            pinjam = itemView.findViewById(R.id.pinjam);
            inti = itemView.findViewById(R.id.intinyainti);
        }
    }
}