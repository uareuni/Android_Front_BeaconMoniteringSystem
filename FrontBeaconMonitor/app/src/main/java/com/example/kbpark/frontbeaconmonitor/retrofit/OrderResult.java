package com.example.kbpark.frontbeaconmonitor.retrofit;

/**
 * Created by KBPark on 2017. 7. 31..
 */

public class OrderResult
{
    String order_res; // 이 변수명들은 반드시 json의 'key name'이랑 같아야 한다!!!!
    int wait_res; // wating line
    int stamp_res; // stamp 갯수

    public String getOrderResult() // method명은 상관 없음
    {
        return order_res;
    }

    public int getWaitResult() {
        return wait_res;
    }

    public int getStampResult() {
        return stamp_res;
    }

}
