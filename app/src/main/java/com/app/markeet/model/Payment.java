package com.app.markeet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {
    @SerializedName("status_code")
    @Expose
    public int status_code;
    @SerializedName("status_message")
    @Expose
    public String status_message;
    @SerializedName("transaction_id")
    public String transaction_id;
    @SerializedName("transaction_time")
    public String transaction_time;
    @SerializedName("transaction_status")
    public String transaction_status;
    @SerializedName("fraud_status")
    public String fraud_status;
    @SerializedName("bill_key")
    public String bill_key;
    @SerializedName("biller_code")
    public String biller_code;

    public Payment(int status_code, String status_message, String transaction_id, String transaction_time, String transaction_status, String fraud_status, String bill_key, String biller_code) {
        this.status_code = status_code;
        this.status_message = status_message;
        this.transaction_id = transaction_id;
        this.transaction_time = transaction_time;
        this.transaction_status = transaction_status;
        this.fraud_status = fraud_status;
        this.bill_key = bill_key;
        this.biller_code = biller_code;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }

    public String getFraud_status() {
        return fraud_status;
    }

    public void setFraud_status(String fraud_status) {
        this.fraud_status = fraud_status;
    }

    public String getBill_key() {
        return bill_key;
    }

    public void setBill_key(String bill_key) {
        this.bill_key = bill_key;
    }

    public String getBiller_code() {
        return biller_code;
    }

    public void setBiller_code(String biller_code) {
        this.biller_code = biller_code;
    }
}
