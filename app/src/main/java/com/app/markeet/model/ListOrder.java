package com.app.markeet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOrder {
    @SerializedName("kode")
    @Expose
    public String kode;
    @SerializedName("statusorder")
    @Expose
    public String statusorder;
    @SerializedName("statuspay")
    @Expose
    public String statuspay;
    @SerializedName("serial")
    @Expose
    public String serial;
    @SerializedName("tanggal")
    @Expose
    public String tanggal;
    @SerializedName("pembeli")
    @Expose
    public String pembeli;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getStatusorder() {
        return statusorder;
    }

    public void setStatusorder(String statusorder) {
        this.statusorder = statusorder;
    }

    public String getStatuspay() {
        return statuspay;
    }

    public void setStatuspay(String statuspay) {
        this.statuspay = statuspay;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPembeli() {
        return pembeli;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }
}
