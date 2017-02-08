package com.example.kbpark.frontbeaconmonitor;

/**
 * Created by KBPark on 2017. 1. 31..
 */

public class Cons
{
    public static final String BASE_URL = "http://10.0.2.2:4903"; // android에서 localhost에 접근하기 위해서는 10.0.2.2 주소로 접근해야함!!!!!
    public static final String LOGIN_ADDITIONAL_URL = "/signin";
    public static final String REGISTER_ADDITIONAL_URL = "/register";

    public static final String LOGIN_SUCCESS = "200";
    public static final String LOGIN_FAILURE = "404";
    public static final String REGISTER_SUCCESS = "200";
    public static final String REGISTER_FAILURE = "404";

    public static final int MIN_PASSWORD = 4;

}
