package com.app.markeet.connection.callbacks;

import com.app.markeet.model.ProdukUMKM;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CallbackUmkmProduct implements Serializable {
    @SerializedName("data")
    private List<ProdukUMKM> dataproduk;

    @SerializedName("status")
    private String status;

    public List<ProdukUMKM> getDataproduk() {
        return dataproduk;
    }

    public void setDataproduk(List<ProdukUMKM> dataproduk) {
        this.dataproduk = dataproduk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
