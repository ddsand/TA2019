package com.app.markeet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListUMKM {
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("address")
    private String address;
    @Expose
    @SerializedName("serial")
    private String serialumkm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSerialumkm() {
        return serialumkm;
    }

    public void setSerialumkm(String serialumkm) {
        this.serialumkm = serialumkm;
    }
}
