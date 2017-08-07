package com.example.kbpark.frontbeaconmonitor;

import android.util.Log;

import com.example.kbpark.frontbeaconmonitor.retrofit.LoginResult;
import com.example.kbpark.frontbeaconmonitor.retrofit.OrderResult;
import com.example.kbpark.frontbeaconmonitor.retrofit.RegisterResult;
import com.example.kbpark.frontbeaconmonitor.retrofit.ServiceApi;
import com.example.kbpark.frontbeaconmonitor.retrofit.TokenResult;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

import static com.example.kbpark.frontbeaconmonitor.Cons.BASE_URL;
import static com.example.kbpark.frontbeaconmonitor.Cons.LOGIN_FAILURE;
import static com.example.kbpark.frontbeaconmonitor.Cons.LOGIN_SUCCESS;
import static com.example.kbpark.frontbeaconmonitor.Cons.ORDER_FAILURE;
import static com.example.kbpark.frontbeaconmonitor.Cons.ORDER_SUCCESS;
import static com.example.kbpark.frontbeaconmonitor.Cons.QUERY_ERROR;
import static com.example.kbpark.frontbeaconmonitor.Cons.REGISTER_FAILURE;
import static com.example.kbpark.frontbeaconmonitor.Cons.REGISTER_SUCCESS;
import static com.example.kbpark.frontbeaconmonitor.Order.OrderCart.orderAdapter;

/**
 * Created by KBPark on 2017. 1. 31..
 */

// 실질적인 retrofit 통신이 일어나는 class
public class User
{
    private static User userInstance = new User();

    static HttpLoggingInterceptor logging;
    static Retrofit retrofit;

    String loginRes;
    String registerRes;
    String orderRes;
    String tokenRes;

    String id; // email 형식
    String name = "박경배";
    String pw;
    String birth;
    String address;
    String phone;
    String product;
    String num; // 주문한 product의 개수
    static String token;

    private User() {
        retrofitInit();
    }

    public static User getInstance(){
        return userInstance;
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

        final Call<RegisterResult> res = serviceApi.resister(id, name, pw, birth, address, phone, User.getInstance().getToken()); // register (실제 통신이 이루어지는 곳)

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

    /**
     * order
     */
    public String order(String product, String num)
    {
        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        // listview의 item갯수만큼 product 뽑아내서 보내기!
        for(int i=0; i<orderAdapter.getCount(); i++){
            Log.i("adapter", "item : " + orderAdapter.getItem(i) + "\n");
        }


        String[] orderList = new String[4];
        orderList[0] = id;
        orderList[1] = phone;
        orderList[2] = product;
        orderList[3] = num;



        final Call<OrderResult> res = serviceApi.order(orderList); // register (실제 통신이 이루어지는 곳)

        // 3. 받아온거 뽑아내기 (동기)
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    orderRes = res.execute().body().getOrderResult();

                    // test log
                    if(orderRes == null)
                    {
                        Log.d("TEST", "Order 통신 실패....");
                    } else if (orderRes.equals(ORDER_SUCCESS))
                    {
                        Log.d("TEST", "Order 성공!");
                    } else if (orderRes.equals(ORDER_FAILURE))
                    {
                        Log.d("TEST", "Order 실패.. ");
                    }

                } catch (IOException ie)
                {
                    ie.printStackTrace();
                }
            }
        }).start();

        return orderRes;
    }

    /**
     * order - push token
     */
    public void pushTokenToServer()
    {
        retrofitInit();
        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        final Call<TokenResult> res = serviceApi.pushTokenToServer(this.token, id); // token push (실제 통신이 이루어지는 곳)

        // 3. 받아온거 뽑아내기 (동기)
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    tokenRes = res.execute().body().getTokenResult();

                    // test log
                    if(tokenRes == null)
                    {
                        Log.d("TEST", "Token 통신 실패....");
                    } else if (tokenRes.equals(REGISTER_SUCCESS))
                    {
                        Log.d("TEST", "Token 성공!");
                    } else if (tokenRes.equals(REGISTER_FAILURE))
                    {
                        Log.d("TEST", "Token 실패.. ");
                    }


                } catch (IOException ie)
                {
                    ie.printStackTrace();
                }
            }
        }).start();
    }


    public static void retrofitInit()
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


    // setter
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // getter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    public String getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getToken() {
        return token;
    }
}
