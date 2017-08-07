package com.example.kbpark.frontbeaconmonitor.fcm;

import android.util.Log;

import com.example.kbpark.frontbeaconmonitor.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    // [START refresh_token]
    @Override
    public void onTokenRefresh()
    {
        // 기존에 token값이 null이 아닌 경우(앱을 방금 깐 경우 제외)
        if(User.getInstance().getToken().length() == 0){ // token값이 없는 경우(즉, app을 처음 설치한 경우)
            User.getInstance().setToken(FirebaseInstanceId.getInstance().getToken());
            Log.d("TOKEN", "init TOKEN : " + User.getInstance().getToken());
        } else if(User.getInstance().getToken().length() > 0){
            User.getInstance().setToken(FirebaseInstanceId.getInstance().getToken());
            Log.d("TOKEN", "refreshed TOKEN : " + User.getInstance().getToken());

            User.getInstance().pushTokenToServer();
        }

    }
}