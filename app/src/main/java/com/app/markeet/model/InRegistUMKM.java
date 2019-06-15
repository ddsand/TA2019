package com.app.markeet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InRegistUMKM {
    @SerializedName("iduser")
    @Expose
    public Long iduser;
    @SerializedName("noktp")
    @Expose
    public String noktp;
    @SerializedName("namausaha")
    @Expose
    public String namausaha;
    @SerializedName("deskripsiusaha")
    @Expose
    public String deskripsiusaha;
    @SerializedName("fotoktp")
    @Expose
    public String fotoktp;

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getNamausaha() {
        return namausaha;
    }

    public void setNamausaha(String namausaha) {
        this.namausaha = namausaha;
    }

    public String getDeskripsiusaha() {
        return deskripsiusaha;
    }

    public void setDeskripsiusaha(String deskripsiusaha) {
        this.deskripsiusaha = deskripsiusaha;
    }

    public String getFotoktp() {
        return fotoktp;
    }

    public void setFotoktp(String fotoktp) {
        this.fotoktp = fotoktp;
    }
}
