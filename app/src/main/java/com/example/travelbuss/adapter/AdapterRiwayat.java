package com.example.travelbuss.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuss.R;
import com.example.travelbuss.models.MobilModels;
import com.example.travelbuss.models.RiwayatModels;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdapterRiwayat extends FirestoreRecyclerAdapter<RiwayatModels, AdapterRiwayat.ViewHolder> {

    public AdapterRiwayat(FirestoreRecyclerOptions<RiwayatModels> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterRiwayat.ViewHolder holder, int position, @NonNull RiwayatModels model) {

    }


    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MobilModels model) {
        Log.d("Bind", "onBindViewHolder: " + model.getNama());
        holder.namamobil.setText(model.getNama());

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
        public TextView namamobil;

        public ViewHolder(View itemView) {
            super(itemView);
            namamobil = itemView.findViewById(R.id.namamobil);
        }
    }

}


