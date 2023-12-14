package com.example.travelbuss.models;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class RiwayatModels {
    public String getIDMobil() {
        return IDMobil;
    }

    public void setIDMobil(String IDMobil) {
        this.IDMobil = IDMobil;
    }

    private String IDMobil, Hari, Total;
    private String Tujuan;

    public Timestamp getTanggalPinjam() {return TanggalPinjam;}

    public void setTanggalPinjam(Timestamp tanggalPinjam) {
        TanggalPinjam = tanggalPinjam;
    }

    private Timestamp TanggalPinjam;

    public String getDocumentId() {
        return documentId;
    }

    @DocumentId
    private String documentId;

    public RiwayatModels() {
        this.documentId = null;
        this.IDMobil = "";
        this.Tujuan="";
    }





    public String getTujuan() {return Tujuan;}

    public void setTujuan(String Tujuan) {
        this.Tujuan = Tujuan;
    }

    public String getHari() {
        return Hari;
    }

    public void setHari(String hari) {this.Hari = hari;}
    public String getTotal() {return Total;}

    public void setTotal(String total) {
        this.Total = total;
    }
}

