package com.app.markeet.connection.callbacks;

import com.app.markeet.model.InRegistUMKM;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallbackRegistUMKM {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private InRegistUMKM data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public InRegistUMKM getData() {
        return data;
    }

    public void setData(InRegistUMKM data) {
        this.data = data;
    }
}
