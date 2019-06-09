package com.app.markeet.connection.callbacks;

import com.app.markeet.model.ListCategory;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CallbackCatSpinner {
    @SerializedName("categories")
    private List<ListCategory> categoryList;
    @SerializedName("status")
    private String status;

    public List<ListCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ListCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
