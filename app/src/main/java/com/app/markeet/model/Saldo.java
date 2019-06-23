package com.app.markeet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Saldo {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("jumlah_uang")
    public String jumlah_uang;

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

    public String getJumlah_uang() {
        return jumlah_uang;
    }

    public void setJumlah_uang(String jumlah_uang) {
        this.jumlah_uang = jumlah_uang;
    }
}
