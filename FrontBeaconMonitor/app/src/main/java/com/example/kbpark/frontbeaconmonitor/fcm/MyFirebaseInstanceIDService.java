package com.example.kbpark.frontbeaconmonitor.fcm;

import android.util.Log;

import com.example.kbpark.frontbeaconmonitor.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static String TOKEN;

    HttpLoggingInterceptor logging;
    Retrofit retrofit;

    private static final String TAG = "MyFirebaseIIDService";

    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        TOKEN = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + TOKEN);

        // 생성등록된 토큰을 개인 앱서버에 보내 저장해 두었다가 추가 뭔가를 하고 싶으면 할 수 있도록 한다.
        sendRegistrationToServer(TOKEN);
    }


    // must be changed
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.

        Log.i("TOKEN", token);
        User.pushTokenToServer(token);




//        // retrofit으로 token 보내기
//        User.retrofitInit();
//        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
//        serviceApi.pushTokenToServer(token, "pkb@pkb.pkb"); // token push (실제 통신이 이루어지는 곳)


//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = new FormBody.Builder()
//                .add("Token", token)
//                .add("KB", "KB")
//                .build();
//
//        //request - change needed
//        Request request = new Request.Builder()
//                .url(BASE_URL + ORDER_TOKENPUSH_URL)
//                .post(body)
//                .build();
//
//        try {
//            client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}