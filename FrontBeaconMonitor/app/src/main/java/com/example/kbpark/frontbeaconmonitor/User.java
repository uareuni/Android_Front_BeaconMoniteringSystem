package com.example.kbpark.frontbeaconmonitor;

import android.util.Log;

import com.example.kbpark.frontbeaconmonitor.retrofit.LoginResult;
import com.example.kbpark.frontbeaconmonitor.retrofit.RegisterResult;
import com.example.kbpark.frontbeaconmonitor.retrofit.ServiceApi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import static com.example.kbpark.frontbeaconmonitor.Cons.BASE_URL;
import static com.example.kbpark.frontbeaconmonitor.Cons.LOGIN_FAILURE;
import static com.example.kbpark.frontbeaconmonitor.Cons.LOGIN_SUCCESS;
import static com.example.kbpark.frontbeaconmonitor.Cons.QUERY_ERROR;
import static com.example.kbpark.frontbeaconmonitor.Cons.REGISTER_FAILURE;
import static com.example.kbpark.frontbeaconmonitor.Cons.REGISTER_SUCCESS;

/**
 * Created by KBPark on 2017. 1. 31..
 */

// 실질적인 retrofit 통신이 일어나는 class
public class User
{
    HttpLoggingInterceptor logging;
    Retrofit retrofit;

    String loginRes;
    String registerRes;

    String id; // email 형식
    String name;
    String pw;
    String birth;
    String address;
    String phone;


    public User(String id, String pw)
    {
        retrofitInit();
        this.id = id;
        this.pw = pw;
    }

    public User(String id, String name, String pw, String birth, String address, String phone)
    {
        retrofitInit();
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
    }


    /**
     * login
     */
    public String login()
    {

        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        final Call<LoginResult> res = serviceApi.login(id, pw); // login (실제 통신이 이루어지는 곳)/////////////////////////////////////

        // 3. 받아온거 뽑아내기 (동기)

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    loginRes = res.execute().body().getLoginResult();

                    // test log
                    if(loginRes != null)
                    {
                        if (loginRes.equals(QUERY_ERROR)) {
                            Log.i("TEST", "Login query error.. " + "loginRes값 : " + loginRes);
                        } else if (loginRes.equals(LOGIN_SUCCESS)) {
                            Log.i("TEST", "Login 성공! " + "loginRes값 : " + loginRes);
                        } else if (loginRes.equals(LOGIN_FAILURE)) {
                            Log.i("TEST", "중복된 아이디.. " + "loginRes값 : " + loginRes);
                        }
                    } else {
                        Log.i("TEST", "Login 실패.. ");
                    }

                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

/*

        // 3. 받아온거 뽑아내기 (비동기)
        res.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    Log.d("LOGIN", "서버통신 성공!(받아오기)");
                loginRes = response.body().getLoginResult();
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                    Log.d("LOGIN", "서버통신 실패..");
                //loginRes = LOGIN_FAILURE;
                loginRes = LOGIN_SUCCESS; // 조작
            }
        });

*/
        return loginRes;
    }

    /**
     * register
     */
    public String register()
    {
        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        String[] userInfo = new String[6];
        userInfo[0] = id;
        userInfo[1] = name;
        userInfo[2] = pw;
        userInfo[3] = birth;
        userInfo[4] = address;
        userInfo[5] = phone;


        final Call<RegisterResult> res = serviceApi.resister(userInfo); // register (실제 통신이 이루어지는 곳)

        // 3. 받아온거 뽑아내기 (동기)
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    registerRes = res.execute().body().getRegisterResult();

                    // test log
                    if(registerRes == null)
                    {
                        Log.d("TEST", "Register 통신 실패....");
                    } else if (registerRes.equals(REGISTER_SUCCESS))
                    {
                        Log.d("TEST", "Register 성공!");
                    } else if (registerRes.equals(REGISTER_FAILURE))
                    {
                        Log.d("TEST", "Register 실패.. ");
                    }


                } catch (IOException ie)
                {
                    ie.printStackTrace();
                }
            }
        }).start();



/*
        // 3. 받아온거 뽑아내기 (비동기)
        res.enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                Log.d("NETWORK", "서버통신 성공!(받아오기)");
                registerRes = response.body().getRegisterResult();
            }

            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                Log.d("test", "서버통신 실패..");
                registerRes = REGISTER_FAILURE;
            }
        });
*/
        return registerRes;
    }


    private void retrofitInit()
    {
        /** < retrofit2 : POST > **/

        // 1. for logging (okhttp log)
        //Here a logging interceptor is created
        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        // 2. retrofit setting
        retrofit = new Retrofit.Builder()
                .client(httpClient.build()) // okhttp client for logging
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }


}
