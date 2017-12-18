package com.example.squaess.androidglasses.ProductsActivityPackage;

/**
 * Created by silentnauscopy on 12/12/17.
 */

public class ItemModel {
    private int item_id;
    private String item_name;
    private boolean available;
    private double price;
    private double correction;
    private String url;

    public ItemModel(int id, String name, boolean available,
                     double price, double correction, String url){
        this.item_id = id;
        this.item_name = name;
        this.available = available;
        this.price = price;
        this.correction = correction;
        this.url = url;

    }


    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCorrection() {
        return correction;
    }

    public void setCorrection(double correction) {
        this.correction = correction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
