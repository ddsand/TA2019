package com.app.markeet.connection.callbacks;

import com.app.markeet.model.ListOrder;
import com.app.markeet.model.ListUMKM;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CallbackListUMKM implements Serializable{
    @Expose
    @SerializedName("listumkm")
    private List<ListUMKM> dataumkm;

    @Expose
    @SerializedName("status")
    private String status;

    public List<ListUMKM> getDataumkm() {
        return dataumkm;
    }

    public void setDataumkm(List<ListUMKM> dataumkm) {
        this.dataumkm = dataumkm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
