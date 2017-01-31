package com.example.kbpark.frontbeaconmonitor.retrofit;

/**
 * Created by KBPark on 2017. 1. 24..
 */

public class LoginResult
{
    String lonin_res; // 이 변수명들은 반드시 json의 'key name'이랑 같아야 한다!!!!

    public String getLoginResult() // method명은 상관 없음
    {
        return lonin_res;
    }

}


