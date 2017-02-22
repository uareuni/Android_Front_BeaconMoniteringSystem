package com.example.kbpark.frontbeaconmonitor;

/**
 * Created by KBPark on 2017. 2. 23..
 */

// POJO class
public class CouponItem
{
    int resId;
    String title;
    String desc;

    CouponItem(int resId, String title, String desc)
    {
        this.resId = resId;
        this.title = title;
        this.desc = desc;
    }

    public int getImageRes() {
        return resId;
    }

    public void setImageRes(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
