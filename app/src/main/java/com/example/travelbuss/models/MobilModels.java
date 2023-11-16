package com.example.travelbuss.models;

public class MobilModels {
    private String Nama;
    private String Harga;

    public MobilModels() {
        this.Nama = "";
        this.Harga="";
    }

    public String getNama() {
        return Nama;
    }
    public void setNama(String Nama) {
        this.Nama = Nama;
    }


    public String getHarga() {
        return Harga;
    }

    public void setHarga(String Harga) {
        this.Harga = Harga;
    }



}

