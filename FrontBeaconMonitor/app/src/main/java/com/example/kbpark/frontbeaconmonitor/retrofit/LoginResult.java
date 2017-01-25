package com.example.kbpark.frontbeaconmonitor.retrofit;

/**
 * Created by KBPark on 2017. 1. 24..
 */

public class LoginResult
{
    String one; // 이 변수명들은 반드시 json의 'key name'이랑 같아야 한다!!!!
    String key; // 이 변수명들은 반드시 json의 'key name'이랑 같아야 한다!!!!

    public String getFirstTest() // method명은 상관 없음
    {
        return one;
    }

    public String getSecondTest() // method명은 상관 없음
    {
        return key;
    }
}


