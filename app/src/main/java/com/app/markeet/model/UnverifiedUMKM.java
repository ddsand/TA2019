package com.app.markeet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnverifiedUMKM {
    @Expose
    @SerializedName("id")
    public String id;
    @Expose
    @SerializedName("name")
    public String name;
    @Expose
    @SerializedName("email")
    public String email;
    @Expose
    @SerializedName("address")
    public String address_umkm;
    @Expose
    @SerializedName("serial")
    public String serial_umkm;
    @Expose
    @SerializedName("iduser")
    public String iduser_umkm;
    @Expose
    @SerializedName("noktp")
    public String noktp;
    @Expose
    @SerializedName("fotoktp")
    public String fotoktp;
    @Expose
    @SerializedName("nama_usaha")
    public String namausaha;
    @Expose
    @SerializedName("deskripsi")
    public String deskripsi;
    @Expose
    @SerializedName("tgldaftar")
    public String tgldaftar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress_umkm() {
        return address_umkm;
    }

    public void setAddress_umkm(String address_umkm) {
        this.address_umkm = address_umkm;
    }

    public String getSerial_umkm() {
        return serial_umkm;
    }

    public void setSerial_umkm(String serial_umkm) {
        this.serial_umkm = serial_umkm;
    }

    public String getIduser_umkm() {
        return iduser_umkm;
    }

    public void setIduser_umkm(String iduser_umkm) {
        this.iduser_umkm = iduser_umkm;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getFotoktp() {
        return fotoktp;
    }

    public void setFotoktp(String fotoktp) {
        this.fotoktp = fotoktp;
    }

    public String getNamausaha() {
        return namausaha;
    }

    public void setNamausaha(String namausaha) {
        this.namausaha = namausaha;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTgldaftar() {
        return tgldaftar;
    }

    public void setTgldaftar(String tgldaftar) {
        this.tgldaftar = tgldaftar;
    }
}
