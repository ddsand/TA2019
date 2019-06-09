package com.app.markeet.connection.callbacks;

import com.app.markeet.model.InProduct;
import com.app.markeet.model.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CallbackInProduct {
    @SerializedName("in")
    @Expose
    private String in;
    @SerializedName("data")
    @Expose
    private InProduct data;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public InProduct getData() {
        return data;
    }

    public void setData(InProduct data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
