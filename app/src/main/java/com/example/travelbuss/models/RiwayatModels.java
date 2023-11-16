package com.example.travelbuss.models;

public class RiwayatModels {
    private String NamaMobil;
    private String Tujuan;

    public RiwayatModels() {

        this.NamaMobil = "";
        this.Tujuan="";
    }

    public String getNama() {
        return NamaMobil;
    }

    public void setNama(String Nama) {
        this.NamaMobil = Nama;
    }

    public String getTujuan() {
        return Tujuan;
    }
    public void setTujuan(String Tujuan) {
        this.Tujuan = Tujuan;
    }



}

