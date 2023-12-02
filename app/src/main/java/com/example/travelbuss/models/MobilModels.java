package com.example.travelbuss.models;

public class MobilModels {
    private String Nama;
    private String Harga;
    private String Gambar;

    private String Kursi;


    public MobilModels() {
        this.Nama = "";
        this.Harga="";
        this.Gambar="";
        this.Kursi="";
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

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String Gambar) {
        this.Gambar = Gambar;
    }

    public String getKursi() {
        return Kursi;
    }

    public void setKursi(String kursi) {
        Kursi = kursi;
    }

}





