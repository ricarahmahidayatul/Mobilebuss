package com.example.travelbuss.models;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.ServerTimestamp;

public class RiwayatModels {
    private String Nama;
    private String Harga;

    public RiwayatModels() {
        this.Nama = "";
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }



}

