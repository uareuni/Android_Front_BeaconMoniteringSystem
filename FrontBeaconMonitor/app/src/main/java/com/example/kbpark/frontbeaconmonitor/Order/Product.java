package com.example.kbpark.frontbeaconmonitor.Order;

/**
 * Created by KBPark on 2017. 8. 8..
 */

public class Product
{
    int imageRes;
    String name;
    String num;
    int price;

    public Product(String name, int price, int imageRes) {
        this.name = name;
        this.price = price;
        this.imageRes = imageRes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageRes() {
        return imageRes;
    }
}
