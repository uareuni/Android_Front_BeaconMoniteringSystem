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
import static com.example.kbpark.frontbeaconmonitor.Cons.REGISTER_FAILURE;

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

    public User(String id, String pw, String birth, String address, String phone)
    {
        retrofitInit();
        this.id = id;
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
        final Call<LoginResult> res = serviceApi.login(id, pw); // login (실제 통신이 이루어지는 곳)

        Log.i("LOG", "log1 : " + loginRes);

        // 3. 받아온거 뽑아내기 (동기)

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    //Log.i("LOG", "log2 : " + loginRes);
                    loginRes = res.execute().body().getLoginResult();
                    //Log.i("LOG", "log3 : " + loginRes);

                } catch(IOException ie) {
                    loginRes = LOGIN_FAILURE;
                    ie.printStackTrace();
                }
            }
        }).start();

        // for 쓰레드가 도는 시간을 벌어주기 위함 (동기식으로 처리하기 위해 일부러 지연시킴)
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            e.printStackTrace();
        }

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

                Log.i("LOG", "log2 : " + loginRes);

                    Log.d("LOGIN", "서버통신 실패..");
                //loginRes = LOGIN_FAILURE;
                loginRes = LOGIN_SUCCESS; // 조작

                Log.i("LOG", "log3 : " + loginRes);
            }
        });

        Log.i("LOG", "log4" + loginRes);
*/
        return loginRes;
    }

    /**
     * register
     */
    public String register()
    {
        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        final Call<RegisterResult> res = serviceApi.resister(id, pw, birth, address, phone); // register (실제 통신이 이루어지는 곳)

        // 3. 받아온거 뽑아내기 (동기)
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    registerRes = res.execute().body().getRegisterResult();
                } catch (IOException ie)
                {
                    registerRes = REGISTER_FAILURE;
                    ie.printStackTrace();
                }
            }
        }).start();

        // for 쓰레드가 도는 시간을 벌어주기 위함 (동기식으로 처리하기 위해 일부러 지연시킴)
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            e.printStackTrace();
        }

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
        /**
         * < retrofit2 : POST >
         */

        // 1. for loggin (okhttp log)
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
