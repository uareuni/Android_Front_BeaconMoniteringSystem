package com.example.kbpark.frontbeaconmonitor.Order;

/**
 * Created by KBPark on 2016. 8. 2..
 */

// 얘는 말그대로 View와 관련된 control만 수행하는군.

public class OrderItem
{
    int product_image;
    String product;
    String num;

    OrderItem(String product, String num)
    {
        this.product = product;
        this.num = num;
    }

    public int getImageRes() {
        return product_image;
    }

    public String getProduct() {
        return product;
    }

    public String getProductNum() {
        return num;
    }

    public void setImageRes(int resId) {
        this.product_image = resId;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setProductNum(String num) {
        this.num = num;
    }
}
