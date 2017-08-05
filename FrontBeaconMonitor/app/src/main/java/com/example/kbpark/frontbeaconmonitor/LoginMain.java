package com.example.kbpark.frontbeaconmonitor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kbpark.frontbeaconmonitor.Order.OrderCart;
import com.example.kbpark.frontbeaconmonitor.Order.OrderEventService;
import com.example.kbpark.frontbeaconmonitor.RECOBeacon.RecoBackgroundMonitoringService;
import com.example.kbpark.frontbeaconmonitor.tabs.CustomViewPager;
import com.example.kbpark.frontbeaconmonitor.tabs.TabsPagerAdapter;

/**
 * Created by KBPark on 2017. 2. 16..
 */

public class LoginMain extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);


        /** Service 실행 **/
        // 1. Service  - OrderCart에 item이 있는지 주기적으로 check하는 service!
        startService(new Intent(this, OrderEventService.class));

        // 2. Service - RecoBackgroundMonitoring service!
        startService(new Intent(this, RecoBackgroundMonitoringService.class));



        Button cartBtn = (Button) findViewById(R.id.btn_order_cart);
        cartBtn.setOnClickListener(this);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        CustomViewPager pager = (CustomViewPager) findViewById(R.id.pager);

        pager.setPagingEnabled(false); // page touch sliding disabled
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);




/** for testing dialog **/
        // 1. popup : 결제 하시겠습니까? 결제가 되었습니다
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.ad_dialog, null);
        ImageView adImage = (ImageView) layout.findViewById(R.id.ad_image);
        adImage.setImageResource(R.mipmap.americano);

        // 제목
        alertDialogBuilder.setTitle("광고");
        // AlertDialog
        alertDialogBuilder
                .setMessage("주문하신 상품 나왔습니다.")
                .setCancelable(false)
                .setView(layout)
                .setPositiveButton("확인", null);

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        // 다이얼로그 보여주기
        alertDialog.show();
/** for testing dialog **/




    }

    @Override
    public void onClick(View v)
    {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.contaner, new OrderCart())
                .commit();
    }

    /***** back key 받아먹기 리스너 등록 *****/
    public interface onKeyBackPressedListener{
        void onBackKey();
    }

    private onKeyBackPressedListener mOnKeyBackPressedListener;
    public static onKeyBackPressedListener menuCurTabListener;
    public static onKeyBackPressedListener eventCurTabListener;
    public static onKeyBackPressedListener curTabListener; // for cart fragment detach

    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener)
    {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    public void onBackPressed()
    {
        // 맨 처음에는 null이겠지만, 적용되는 fragment의 onAttach()(<-얘는 fragment가 view에 붙을때 호출되는 callback임) 호출 후에는 초기화가 되기 때문에 back key를 뺏아올 수 있다.
        if (mOnKeyBackPressedListener != null)
        {
            mOnKeyBackPressedListener.onBackKey();
        } else
        {
            super.onBackPressed();
        }
    }

}
