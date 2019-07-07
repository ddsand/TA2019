package com.app.markeet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailOrder {
    @Expose
    @SerializedName("nama")
    public String nama;
    @Expose
    @SerializedName("idproduk")
    public String idproduk;
    @Expose
    @SerializedName("jumlah")
    public String jumlah;
    @Expose
    @SerializedName("hargabarang")
    public String hargabarang;
    @Expose
    @SerializedName("serial")
    public String serialorder;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdproduk() {
        return idproduk;
    }

    public void setIdproduk(String idproduk) {
        this.idproduk = idproduk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHargabarang() {
        return hargabarang;
    }

    public void setHargabarang(String hargabarang) {
        this.hargabarang = hargabarang;
    }

    public String getSerialorder() {
        return serialorder;
    }

    public void setSerialorder(String serialorder) {
        this.serialorder = serialorder;
    }
}
