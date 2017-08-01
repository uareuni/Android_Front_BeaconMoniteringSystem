package com.example.kbpark.frontbeaconmonitor.Order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kbpark.frontbeaconmonitor.R;

/**
 * Created by KBPark on 2017. 8. 1..
 */

public class OrderProduct extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.order_product, container, false);
        viewInit(rootView);
//        return super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    private void viewInit(View rootView)
    {
//        Button btn_order = (Button) rootView.findViewById(R.id.btn_order);
//        btn_order.setOnClickListener(this);
    }
}
