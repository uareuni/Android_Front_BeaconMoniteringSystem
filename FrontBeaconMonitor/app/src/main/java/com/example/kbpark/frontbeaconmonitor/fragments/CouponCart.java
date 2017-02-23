package com.example.kbpark.frontbeaconmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kbpark.frontbeaconmonitor.LoginMain;
import com.example.kbpark.frontbeaconmonitor.R;

/**
 * Created by KBPark on 2017. 2. 23..
 */

public class CouponCart extends Fragment implements LoginMain.onKeyBackPressedListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.coupon_cart, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        ((LoginMain)context).setOnKeyBackPressedListener(this);
    }

    @Override
    public void onBackKey()
    {
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .detach(this)
                .commit();
    }
}
