package com.app.markeet.connection.callbacks;

import com.app.markeet.model.Payment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CallbackPayment implements Serializable{
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("data")
    @Expose
    public Payment payment;
}
