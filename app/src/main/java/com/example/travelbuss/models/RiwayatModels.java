package com.example.travelbuss.models;

public class RiwayatModels {
    private String NamaMobil;
    private String Tujuan;
    private String TanggalPinjam;

    public RiwayatModels() {
        this.TanggalPinjam = "";
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


    public String getTanggalPinjam() {
        return TanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.TanggalPinjam = tanggalPinjam;
    }
}

