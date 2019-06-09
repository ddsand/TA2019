package com.app.markeet.model;

import android.graphics.drawable.Drawable;

public class ListPayment {
    public String image;
    public Drawable imageDrw;
    public String merchant_name;
    public String desc_merchant;
    public boolean section = false;

    public ListPayment() {
    }

    public ListPayment(String merchant_name, boolean section) {
        this.merchant_name = merchant_name;
        this.section = section;
    }
}

