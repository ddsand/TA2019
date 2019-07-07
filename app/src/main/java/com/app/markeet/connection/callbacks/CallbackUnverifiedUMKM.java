package com.app.markeet.connection.callbacks;

import com.app.markeet.model.UnverifiedUMKM;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CallbackUnverifiedUMKM  {
    @Expose
    @SerializedName("listumkm")
    private List<UnverifiedUMKM> listumkm;

    @Expose
    @SerializedName("status")
    private String status;

    public List<UnverifiedUMKM> getListumkm() {
        return listumkm;
    }

    public void setListumkm(List<UnverifiedUMKM> listumkm) {
        this.listumkm = listumkm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
