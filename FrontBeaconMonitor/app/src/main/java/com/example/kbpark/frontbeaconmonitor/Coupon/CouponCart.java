package com.example.kbpark.frontbeaconmonitor.Coupon;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kbpark.frontbeaconmonitor.LoginMain;
import com.example.kbpark.frontbeaconmonitor.R;

import static com.example.kbpark.frontbeaconmonitor.LoginMain.curTabListener;

/**
 * Created by KBPark on 2017. 2. 23..
 */

public class CouponCart extends Fragment implements LoginMain.onKeyBackPressedListener
{
    TabLayout tabLayout;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        ((LoginMain)context).setOnKeyBackPressedListener(this);

        tabLayout = (TabLayout)((LoginMain)context).findViewById(R.id.tabs);
        tabLayout.setVisibility(View.INVISIBLE); // GONE으로 하고 layout param로 layout_weight값 맞춰주는게 더 좋긴한데, 일단은 INVISIBLE.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.coupon_cart, container, false);

        return rootView;
    }

    @Override
    public void onBackKey()
    {
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .detach(this)
                .commit();
        ((LoginMain) getContext()).setOnKeyBackPressedListener(curTabListener);
        tabLayout.setVisibility(View.VISIBLE);
    }

}
