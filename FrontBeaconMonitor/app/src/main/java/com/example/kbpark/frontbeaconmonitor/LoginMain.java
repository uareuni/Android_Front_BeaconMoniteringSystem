package com.example.kbpark.frontbeaconmonitor;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kbpark.frontbeaconmonitor.fragments.CouponCart;
import com.example.kbpark.frontbeaconmonitor.tabs.CustomViewPager;
import com.example.kbpark.frontbeaconmonitor.tabs.TabsPagerAdapter;

/**
 * Created by KBPark on 2017. 2. 16..
 */

public class LoginMain extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        Button cartBtn = (Button) findViewById(R.id.btn_coupon_cart);
        cartBtn.setOnClickListener(this);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        CustomViewPager pager = (CustomViewPager) findViewById(R.id.pager);

        pager.setPagingEnabled(false); // page touch sliding disabled
        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
    }

    @Override
    public void onClick(View v)
    {
        Toast.makeText(getApplicationContext(), "cart clicked!", Toast.LENGTH_SHORT).show();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.contaner, new CouponCart())
                .commit();
    }


    /***** back key 받아먹기 리스너 등록 *****/
    public interface onKeyBackPressedListener{
        void onBackKey();
    }

    private onKeyBackPressedListener mOnKeyBackPressedListener;
    public static onKeyBackPressedListener saleTabCurListener;
    public static onKeyBackPressedListener couponTabCurListener;

    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener)
    {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    public void onBackPressed()
    {
        // 맨 처음에는 null이겠지만, 적용되는 fragment의 onAttach()(<-얘는 fragment가 view에 붙을때 호출되는 call back임) 호출 후에는 초기화가 되기 때문에 back key를 뺏아올 수 있다.
        if (mOnKeyBackPressedListener != null)
        {
            mOnKeyBackPressedListener.onBackKey();
        } else
        {
            super.onBackPressed();
        }
    }


}
