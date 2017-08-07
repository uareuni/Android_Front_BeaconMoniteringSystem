package com.example.kbpark.frontbeaconmonitor.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.example.kbpark.frontbeaconmonitor.Cons.LOGIN_ADDITIONAL_URL;
import static com.example.kbpark.frontbeaconmonitor.Cons.ORDER_ENQUEUE_URL;
import static com.example.kbpark.frontbeaconmonitor.Cons.ORDER_TOKENREFRESH_URL;
import static com.example.kbpark.frontbeaconmonitor.Cons.REGISTER_ADDITIONAL_URL;

/**
 * Created by KBPark on 2017. 1. 24..
 */

public interface ServiceApi
{
    @FormUrlEncoded
    @POST(LOGIN_ADDITIONAL_URL)
    Call<LoginResult> login(@Field("user_id") String id,  // 얘네는 server에서 받을 data들. (server에서 받을때는 'request.body.user_id'로!)
                            @Field("user_pw") String pw); // 얘네는 server에서 받을 data들.

    @FormUrlEncoded
    @POST(REGISTER_ADDITIONAL_URL)
    Call<RegisterResult> resister(@Field("res_email") String res_email, // email을 id로 사용
                                  @Field("res_name") String res_name,
                                  @Field("res_pw") String res_pw,
                                  @Field("res_birth") String res_birth,
                                  @Field("res_address") String res_address,
                                  @Field("res_phone") String res_phone,
                                  @Field("res_token") String res_token
    );

    @FormUrlEncoded
    @POST(ORDER_ENQUEUE_URL)
    Call<OrderResult> order(@Field("orderList") String[] orderList); // name, phone, product(token : space bar), num
    // ex) String product = "아메키라노 에므스 망고스무디"; // token space

    @FormUrlEncoded
    @POST(ORDER_TOKENREFRESH_URL)
    Call<TokenResult> pushTokenToServer(@Field("token") String token, @Field("id") String id); // token, id


}





// cf) GET 방식 sample
    /*
        @GET("/users/{user}/repos")
        Call<List<Repo>> listRepos(@Path("user") String user);
     */