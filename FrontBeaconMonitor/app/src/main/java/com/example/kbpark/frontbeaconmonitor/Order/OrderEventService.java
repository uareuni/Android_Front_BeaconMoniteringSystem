package com.example.kbpark.frontbeaconmonitor.Order;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.example.kbpark.frontbeaconmonitor.Cons;
import com.example.kbpark.frontbeaconmonitor.User;

import static com.example.kbpark.frontbeaconmonitor.Cons.IN;
import static com.example.kbpark.frontbeaconmonitor.Cons.MONITORING_STATE;
import static com.example.kbpark.frontbeaconmonitor.Order.OrderCart.orderCartAdapter;

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
                Log.i("ORDER_TEST", "searching beacons..");
                Thread.sleep(5 * 1000);

                // Beacon 안에 들어와있고 && cart에 주문할게 있으면!
                if((MONITORING_STATE == IN) && (orderCartAdapter.getCount() != 0))
                {
                    // listview의 item갯수만큼 product 뽑아내서 보내기!
                    for (int i = 0; i < orderCartAdapter.getCount(); i++)
                    {
                        if(((OrderItem)orderCartAdapter.getItem(i)).getOrderState().equals(Cons.PAYMENT_COMPLETE)) // '결제 완료'
                        {
                            Log.i("ORDER_TEST", "주문할 item 이름 : " + orderCartAdapter.getProduct(i) + "\n");

                            // 1. 주문하기 (server로 보내기)
                            User.getInstance().order(orderCartAdapter.getProduct(i), orderCartAdapter.getProductNum(i) + "");

                            /** 여기서 '결제완료' -> '주문완료'  **/
                            new Handler(Looper.getMainLooper()).post(new ChangeView(i));
                        }
                    }
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class ChangeView implements Runnable {

        int i;

        public ChangeView(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            ((OrderItem)orderCartAdapter.getItem(i)).setOrderState(Cons.ORDER_COMPLETE); // '주문 완료'
//            Log.i("ORDER_TEST", "View 변경 : " + ((OrderItem) orderAdapter.getItem(i)).getOrderState());
            orderCartAdapter.notifyDataSetChanged();
        }
    }


    /** Service call될 때마다 실행 **/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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
