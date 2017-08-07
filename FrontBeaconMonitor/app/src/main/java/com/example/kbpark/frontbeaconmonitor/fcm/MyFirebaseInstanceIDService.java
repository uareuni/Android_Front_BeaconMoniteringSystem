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
        // Get updated InstanceID token.
        User.getInstance().setToken(FirebaseInstanceId.getInstance().getToken());
        Log.d("TOKEN", "refreshed TOKEN : " + User.getInstance().getToken());

        User.getInstance().pushTokenToServer();

    }
}