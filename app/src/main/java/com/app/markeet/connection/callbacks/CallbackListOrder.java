package com.app.markeet.connection.callbacks;


import com.app.markeet.model.ListOrder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CallbackListOrder implements Serializable {
    @SerializedName("order_list")
    private List<ListOrder> dataorder;

    @SerializedName("status")
    private String status;

    @SerializedName("msg")
    private String msg;

    public List<ListOrder> getDataorder() {
        return dataorder;
    }

    public void setDataorder(List<ListOrder> dataorder) {
        this.dataorder = dataorder;
    }

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


}
