package com.example.kbpark.frontbeaconmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kbpark.frontbeaconmonitor.CouponAdapter;
import com.example.kbpark.frontbeaconmonitor.LoginMain;
import com.example.kbpark.frontbeaconmonitor.R;

import static com.example.kbpark.frontbeaconmonitor.LoginMain.couponTabCurListener;
import static com.example.kbpark.frontbeaconmonitor.LoginMain.saleTabCurListener;

/**
 * Created by KBPark on 2017. 2. 21..
 */

public class CouponMain extends Fragment implements LoginMain.onKeyBackPressedListener, AdapterView.OnItemClickListener
{
    public static CouponMain newInstance()
    {
        return new CouponMain();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.coupon_main, container, false);

        // for test
        CouponAdapter adapter = new CouponAdapter();
        adapter.addItem(R.mipmap.icon_birth, "타이틀", "설명~");
        adapter.addItem(R.mipmap.icon_home, "타이틀", "설명~");
        adapter.addItem(R.mipmap.icon_email, "타이틀", "설명~");

        ListView listview = (ListView) rootView.findViewById(R.id.listview_coupon);
        listview.setOnItemClickListener(this);
        listview.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(getContext(), position + "번 item clicked!", Toast.LENGTH_SHORT).show();
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
        couponTabCurListener = this;
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
