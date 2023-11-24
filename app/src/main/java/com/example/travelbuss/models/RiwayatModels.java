package com.example.travelbuss.models;

public class RiwayatModels {
    private String NamaMobil;
    private String Tujuan;

    public RiwayatModels() {
        this.NamaMobil = "";
        this.Tujuan="";
    }

    public String getNamaMobil() {
        return NamaMobil;
    }

    public void setNamaMobil(String Penjemputan) {
        this.NamaMobil = Penjemputan;
    }

    public String getTujuan() {
        return Tujuan;
    }


    public void setTujuan(String Tujuan) {
        this.Tujuan = Tujuan;
    }



}

