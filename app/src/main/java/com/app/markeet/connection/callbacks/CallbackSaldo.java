package com.app.markeet.connection.callbacks;

import com.app.markeet.model.Saldo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CallbackSaldo implements Serializable {
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

    public Saldo saldo;

    public Saldo getSaldo() {
        return saldo;
    }

    public void setSaldo(Saldo saldo) {
        this.saldo = saldo;
    }
}
