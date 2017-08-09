package com.example.kbpark.frontbeaconmonitor.Order;

import com.example.kbpark.frontbeaconmonitor.Cons;

/**
 * Created by KBPark on 2016. 8. 2..
 */

// 얘는 말그대로 View와 관련된 control만 수행하는군.

public class OrderItem
{
    int product_image;
    int num;
    String product;
    String orderState;

    OrderItem(String product, int num)
    {
        this.product = product;
        this.num = num;
        orderState = Cons.PAYMENT_COMPLETE;
    }

    public int getImageRes() {
        return product_image;
    }

    public String getProduct() {
        return product;
    }

    public int getProductNum() {
        return num;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setImageRes(int resId) {
        this.product_image = resId;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setProductNum(int num) {
        this.num = num;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
