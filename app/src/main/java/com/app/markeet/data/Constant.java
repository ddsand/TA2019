package com.app.markeet.data;

public class Constant {

    public static String WEB_URL = "http://josstekkom.com/markeett/";

    public static final String SECURITY_CODE = "FgODeKc7LPLc6jV0iEnZCn43QjRKRAe1aiMeMfMAEqctJ0dA1S7EzGUAZpB05mug2wUvF3vASxvlPqWWZu4sqk7IgxBYbAVoQUZV";

    // Device will re-register the device data to server when open app N-times
    public static int OPEN_COUNTER = 50;

    public static int NEWS_PER_REQUEST = 10;
    public static int PRODUCT_PER_REQUEST = 10;
    public static int NOTIFICATION_PAGE = 20;
    public static int WISHLIST_PAGE = 20;

    // retry load image notification
    public static int LOAD_IMAGE_NOTIF_RETRY = 3;

    // Method get path to image
    public static String getURLimgProduct(String file_name) {
        return WEB_URL + "uploads/product/" + file_name;
    }

    public static String getURLimgNews(String file_name) {
        return WEB_URL + "uploads/news/" + file_name;
    }

    public static String getURLimgCategory(String file_name) {
        return WEB_URL + "uploads/category/" + file_name;
    }

    public static String getURLimgUmkm(String file_name) {
        return WEB_URL + "uploads/umkm/" + file_name;
    }

}
