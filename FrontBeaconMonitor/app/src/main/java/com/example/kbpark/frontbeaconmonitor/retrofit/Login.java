package com.example.kbpark.frontbeaconmonitor.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by KBPark on 2017. 1. 24..
 */

public interface Login
{
    // String BASE_URL = "http://echo.jsontest.com";
    // String ADDITIONAL_URL = "/key/value/one/two";

    String BASE_URL = "http://10.0.2.2:8080"; // android에서 localhost에 접근하기 위해서는 10.0.2.2 주소로 접근해야함!!!!!
    String ADDITIONAL_URL = "/JsonTest/json.php";

    @FormUrlEncoded
    @POST(ADDITIONAL_URL)
    Call<LoginResult> login(@Field("user_id") String id,  // 얘네는 server에서 받을 data들.
                            @Field("user_pw") String pw); // 얘네는 server에서 받을 data들.


    // < json test >
    // 다음 url에서 json을 받아올 예정.
    // http://echo.jsontest.com/key/value/one/two


    //-------------------------------------------------------

    // cf) GET 방식 sample
    /*
        @GET("/users/{user}/repos")
        Call<List<Repo>> listRepos(@Path("user") String user);
     */
}
