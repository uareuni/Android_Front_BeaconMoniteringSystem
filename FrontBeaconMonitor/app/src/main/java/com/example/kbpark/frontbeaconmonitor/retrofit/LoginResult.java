package com.example.kbpark.frontbeaconmonitor.retrofit;

/**
 * Created by KBPark on 2017. 1. 24..
 */

public class LoginResult
{
    String id;
    String pw;
    String key;

    public LoginResult(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getKey()
    {
        return key;
    }
}
