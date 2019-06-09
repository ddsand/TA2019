package com.app.markeet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InProduct {
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("price")
    @Expose
    public Double price;
    @SerializedName("price_discount")
    @Expose
    public Double price_discount;
    @SerializedName("stock")
    @Expose
    public Long stock;
    @SerializedName("draft")
    @Expose
    public Integer draft;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("created_at")
    @Expose
    public Long created_at;
    @SerializedName("last_update")
    @Expose
    public Long last_update;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice_discount() {
        return price_discount;
    }

    public void setPrice_discount(Double price_discount) {
        this.price_discount = price_discount;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Integer getDraft() {
        return draft;
    }

    public void setDraft(Integer draft) {
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

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getLast_update() {
        return last_update;
    }

    public void setLast_update(Long last_update) {
        this.last_update = last_update;
    }
}
