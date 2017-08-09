package com.example.kbpark.frontbeaconmonitor.retrofit;

/**
 * Created by KBPark on 2017. 1. 24..
 */

public class LoginResult
{
    String login_res; // 이 변수명들은 반드시 json의 'key name'이랑 같아야 한다!!!!
    int stamp_res;

    public String getLoginResult() // method명은 상관 없음
    {
        return login_res;
    }

    public int getNumOfStamp() // method명은 상관 없음
    {
        return stamp_res;
    }
}


