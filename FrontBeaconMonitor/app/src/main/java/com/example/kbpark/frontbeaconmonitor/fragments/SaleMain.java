package com.example.kbpark.frontbeaconmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.kbpark.frontbeaconmonitor.LoginMain;
import com.example.kbpark.frontbeaconmonitor.R;

import static com.example.kbpark.frontbeaconmonitor.LoginMain.saleTabCurListener;

/**
 * Created by KBPark on 2017. 2. 21..
 */

public class SaleMain extends Fragment implements View.OnClickListener, LoginMain.onKeyBackPressedListener
{
    public static SaleMain newInstance() { return new SaleMain(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.sale_main, container, false);
        viewInit(rootView);
        return rootView;
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_fruits:
                Toast.makeText(getContext(), "fruits clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackKey()
    {
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


    private void viewInit(View rootView)
    {
        Button btn_fruits = (Button) rootView.findViewById(R.id.btn_fruits);

        btn_fruits.setOnClickListener(this);

    }

}
