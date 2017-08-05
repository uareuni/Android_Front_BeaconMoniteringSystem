package com.example.kbpark.frontbeaconmonitor.fcm;

import android.util.Log;

import com.example.kbpark.frontbeaconmonitor.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    // [START refresh_token]
    @Override
    public void onTokenRefresh()
    {
        // Get updated InstanceID token.
        User.getInstance().setToken(FirebaseInstanceId.getInstance().getToken());
        Log.d(TAG, "새로 refresh 된 TOKEN: " + User.getInstance().getToken());

    }
}