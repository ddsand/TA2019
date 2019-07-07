package com.app.markeet.connection.callbacks;

import com.app.markeet.model.DetailOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CallbackDetailOrder implements Serializable{
    @Expose
    @SerializedName("data")
    public List<DetailOrder> dataDetailOrders;

    @Expose
    @SerializedName("status")
    public String status;

    public List<DetailOrder> getDataDetailOrders() {
        return dataDetailOrders;
    }

    public void setDataDetailOrders(List<DetailOrder> dataDetailOrders) {
        this.dataDetailOrders = dataDetailOrders;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
