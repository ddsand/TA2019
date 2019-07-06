package com.app.markeet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManualOrder {
    @Expose
    @SerializedName("id")
    public String idorder;
    @Expose
    @SerializedName("code")
    public String ordercode;
    @Expose
    @SerializedName("buyer")
    public String buyer;
    @Expose
    @SerializedName("manual_pay")
    public String pay_orderr;
    @Expose
    @SerializedName("nama_akun")
    public String nama_akun;
    @Expose
    @SerializedName("rekening")
    public String rekening;
    @Expose
    @SerializedName("limir_transfer")
    public String limit_transfer;
    @Expose
    @SerializedName("serial")
    public String serial_order;
    @Expose
    @SerializedName("status")
    public String status_order;

    public String getPay_orderr() {
        return pay_orderr;
    }

    public void setPay_orderr(String pay_orderr) {
        this.pay_orderr = pay_orderr;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public String getSerial_order() {
        return serial_order;
    }

    public void setSerial_order(String serial_order) {
        this.serial_order = serial_order;
    }

    public String getIdorder() {
        return idorder;
    }

    public void setIdorder(String idorder) {
        this.idorder = idorder;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getNama_akun() {
        return nama_akun;
    }

    public void setNama_akun(String nama_akun) {
        this.nama_akun = nama_akun;
    }

    public String getRekening() {
        return rekening;
    }

    public void setRekening(String rekening) {
        this.rekening = rekening;
    }

    public String getLimit_transfer() {
        return limit_transfer;
    }

    public void setLimit_transfer(String limit_transfer) {
        this.limit_transfer = limit_transfer;
    }
}
