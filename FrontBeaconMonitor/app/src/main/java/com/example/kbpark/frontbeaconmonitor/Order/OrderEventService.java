package com.example.kbpark.frontbeaconmonitor.Order;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import static com.example.kbpark.frontbeaconmonitor.Cons.IN;
import static com.example.kbpark.frontbeaconmonitor.Cons.MONITORING_STATE;
import static com.example.kbpark.frontbeaconmonitor.Order.OrderCart.orderAdapter;

/**
 * Created by KBPark on 2017. 8. 4..
 */

public class OrderEventService extends Service implements Runnable
{
    /** Service 시작될때 최초 한번 실행 **/
    @Override
    public void onCreate() {
        super.onCreate();

        Thread orderThread = new Thread(this);
        orderThread.start();

    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(20 * 1000);
                Log.i("ORDER_TEST", "searching beacons..");

                // Beacon 안에 들어와있고 && cart에 주문할게 있으면!
                if((MONITORING_STATE == IN) && (orderAdapter.getCount() != 0))
                {
                    Log.i("ORDER_TEST", "주문할 item이 존재함!");

                    // listview의 item갯수만큼 product 뽑아내서 보내기!
                    for (int i = 0; i < orderAdapter.getCount(); i++)
                    {
                        Log.i("ORDER_TEST", "주문할 item 이름 : " + orderAdapter.getProduct(i) + "\n");
                        // 1. 주문하기 (server로 보내기)
//                        User.getInstance().order(orderAdapter.getProduct(i), orderAdapter.getProductNum(i));
                        // 2.
//                        orderAdapter.removeItem(i); // 추후 : 여기 item을 remove하는게 아니라 '주문 완료'로 표기 변경! (이때, main thread로 view 변경시켜야함!)



                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** Service call될 때마다 실행 **/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


//        // listview에 item이 있는 경우에만 order진행!
//        if(orderAdapter.getCount() != 0) {
//            // listview의 item갯수만큼 product 뽑아내서 보내기!
//            for(int i=0; i<orderAdapter.getCount(); i++){
//                Log.i("TTEST", "주문할 item 이름 : " + orderAdapter.getProduct(i) + "\n");
//                User.getInstance().order(orderAdapter.getProduct(i), orderAdapter.getProductNum(i));
//    //////////////////////////////////////////// 보내고 list에서 지우는 것도 수행해야함!! //////////////////////////////////////////////////////////////////
//            }
//        }

        return super.onStartCommand(intent, flags, startId);
    }

    /** Service가 종료될 때 실행 **/
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
