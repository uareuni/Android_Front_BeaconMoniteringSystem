package com.example.kbpark.frontbeaconmonitor.Event;

/**
 * Created by KBPark on 2017. 2. 23..
 */

// POJO class
public class EventItem
{
    int resId;
    String title;
    String desc;

    EventItem(int resId, String title, String desc)
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
