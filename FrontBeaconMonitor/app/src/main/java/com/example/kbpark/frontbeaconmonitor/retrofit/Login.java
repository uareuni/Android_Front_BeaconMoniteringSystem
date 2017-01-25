package com.example.kbpark.frontbeaconmonitor.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by KBPark on 2017. 1. 24..
 */

public interface Login
{
    @POST("")
    Call<LoginResult> login(@Field("name") String email,
                            @Field("password") String password);
}
