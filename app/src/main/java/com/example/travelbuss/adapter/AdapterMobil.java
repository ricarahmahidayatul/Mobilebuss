package com.example.travelbuss.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelbuss.R;
import com.example.travelbuss.models.MobilModels;
import com.example.travelbuss.rincianmobil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterMobil extends FirestoreRecyclerAdapter<MobilModels, AdapterMobil.ViewHolder> {
    Context context;
    public AdapterMobil(FirestoreRecyclerOptions<MobilModels> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MobilModels model) {
        Log.d("Bind", "onBindViewHolder: " + model.getNama() +model.getGambar() + " "+model.getKursi());

        holder.namaMobil.setText(model.getNama());
        holder.harga.setText(model.getHarga());
        Glide.with(context)
                .load(model.getGambar())
                .centerCrop()
                .into(holder.gambar);

        holder.listMobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, rincianmobil.class);
                intent.putExtra("Nama",model.getNama());
                intent.putExtra("Kursi", model.getKursi());
                intent.putExtra("Harga", model.getHarga());
                intent.putExtra("Gambar", model.getGambar());
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mobilView = inflater.inflate(R.layout.listberanda, parent, false);
        ViewHolder viewHolder = new ViewHolder(mobilView);
        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaMobil, kursi;
        public TextView harga;
        ImageView gambar;
        CardView listMobil;

        public ViewHolder(View itemView) {
            super(itemView);
            namaMobil = itemView.findViewById(R.id.namamobil);
            harga = itemView.findViewById(R.id.harga);
            kursi=itemView.findViewById(R.id.rincikursi);
            gambar = itemView.findViewById(R.id.rincigmbr);
            listMobil = itemView.findViewById(R.id.listmobil);

        }
    }
}
