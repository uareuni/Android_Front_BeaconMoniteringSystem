package com.example.kbpark.frontbeaconmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kbpark.frontbeaconmonitor.LoginMain;
import com.example.kbpark.frontbeaconmonitor.R;

import static com.example.kbpark.frontbeaconmonitor.LoginMain.saleTabCurListener;

/**
 * Created by KBPark on 2017. 2. 21..
 */

public class CouponMain extends Fragment implements LoginMain.onKeyBackPressedListener
{
    public static CouponMain newInstance()
    {
        return new CouponMain();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.coupon_main, container, false);
        return rootView;
    }

    @Override
    public void onBackKey()
    {
        Toast.makeText(getContext(), "CouponMain back key clicked", Toast.LENGTH_SHORT).show();

        // back key 다시 원상복구 시켜놓기!
        LoginMain activity = (LoginMain) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        ((LoginMain) context).setOnKeyBackPressedListener(this);
        saleTabCurListener = this;
    }

    // 참고로, 지금 내 경우는 SaleMain이랑 CouponMain에 있는 layout에다가 fragment들을 붙이고 있는 상황이라
    // 해당 두 Fragment들에서만 setUserVisibleHint가 먹는것 같다.

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        if(isVisibleToUser)
        {
            // listener setting
            if(getContext()!=null)
            {
                ((LoginMain) getContext()).setOnKeyBackPressedListener(saleTabCurListener);
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}
