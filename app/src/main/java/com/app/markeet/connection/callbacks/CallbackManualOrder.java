package com.app.markeet.connection.callbacks;

import com.app.markeet.model.ManualOrder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CallbackManualOrder implements Serializable {
    @Expose
    @SerializedName("status")
    public String statusorder;

    @Expose
    @SerializedName("data")
    public List<ManualOrder> manualOrders;

    public String getStatusorder() {
        return statusorder;
    }

    public void setStatusorder(String statusorder) {
        this.statusorder = statusorder;
    }

    public List<ManualOrder> getManualOrders() {
        return manualOrders;
    }

    public void setManualOrders(List<ManualOrder> manualOrders) {
        this.manualOrders = manualOrders;
    }
}
