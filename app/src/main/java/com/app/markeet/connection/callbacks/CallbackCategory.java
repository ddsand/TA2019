package com.app.markeet.connection.callbacks;

import com.app.markeet.model.Category;
import com.app.markeet.model.ListCategory;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CallbackCategory implements Serializable {

    public String status = "";
    public List<Category> categories = new ArrayList<>();
}
