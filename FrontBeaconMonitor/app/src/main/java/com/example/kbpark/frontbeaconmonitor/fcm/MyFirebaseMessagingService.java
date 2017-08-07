package com.example.kbpark.frontbeaconmonitor.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.kbpark.frontbeaconmonitor.MainActivity;
import com.example.kbpark.frontbeaconmonitor.R;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "TOKEN";

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE );
        PowerManager.WakeLock wakeLock = pm.newWakeLock( PowerManager.SCREEN_DIM_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG" );
        wakeLock.acquire(3000);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                // 키잠금 해제하기
//                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
//                // 화면 켜기
//                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData().get("data")); // 요기서 server에 저장된 image url을 받아올 것!
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }


//
///**-------------------------- 이거 되는지 안되는지는 아직 test 못해봄!! --------------------------**/
//        Handler handler = new Handler();
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//
//                // 1. popup : 결제 하시겠습니까? 결제가 되었습니다
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
//
//                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View layout = inflater.inflate(R.layout.ad_dialog, null);
//                ImageView adImage = (ImageView) layout.findViewById(R.id.ad_image);
//                adImage.setImageResource(R.mipmap.americano);
//
//                // 제목
//                alertDialogBuilder.setTitle("광고");
//
//                // AlertDialog
//                alertDialogBuilder
//                        .setMessage("주문하신 상품 나왔습니다.")
//                        .setCancelable(false)
//                        .setView(layout)
//                        .setPositiveButton("확인", null);
//
//                // 다이얼로그 생성
//                AlertDialog alertDialog = alertDialogBuilder.create();
//
//                // 다이얼로그 보여주기
//                alertDialog.show();
//            }
//        });
///**-----------------------------------------------------------------------------------------**/
//
//






        //추가한것
        sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }






    private void sendNotification(String messageTitle, String messageBody){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());


        // 진동
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);

        //

    }
}
