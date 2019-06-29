package com.app.markeet.model;

import com.google.gson.annotations.SerializedName;

public class ProdukUMKM {
    @SerializedName("id")
    private int id;
    @SerializedName("iduser")
    private int iduser;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private String price;
    @SerializedName("price_discount")
    private String price_discount;
    @SerializedName("stock")
    private int stock;
    @SerializedName("draft")
    private int draft;
    @SerializedName("description")
    private String description;
    @SerializedName("status")
    private String status;

    public ProdukUMKM(int id, int iduser, String name, String image, String price, String price_discount, int stock, int draft, String description, String status) {
        this.id = id;
        this.iduser = iduser;
        this.name = name;
        this.image = image;
        this.price = price;
        this.price_discount = price_discount;
        this.stock = stock;
        this.draft = draft;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_discount() {
        return price_discount;
    }

    public void setPrice_discount(String price_discount) {
        this.price_discount = price_discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDraft() {
        return draft;
    }

    public void setDraft(int draft) {
        this.draft = draft;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
