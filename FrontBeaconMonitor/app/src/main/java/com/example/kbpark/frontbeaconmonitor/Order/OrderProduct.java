package com.example.kbpark.frontbeaconmonitor.Order;

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

import static com.example.kbpark.frontbeaconmonitor.LoginMain.curTabListener;
import static com.example.kbpark.frontbeaconmonitor.LoginMain.saleCurTabListener;

/**
 * Created by KBPark on 2017. 8. 1..
 */

public class OrderProduct extends Fragment implements View.OnClickListener, LoginMain.onKeyBackPressedListener
{
    Button btn_pay;

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
        btn_pay = (Button) rootView.findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) // '결제' 버튼 클릭시
    {
        // 1. popup : 결제 하시겠습니까? 결제가 되었습니다
        // 2. 결제 완료시 '주문 내역 list'에 올리고 notify()
        // 3. beacon은 주문 내역에 item이 있는지 계속 탐지하게 한다. (있으면 : server로 push/ 없으면 : nothing)

    }


    @Override
    public void onBackKey()
    {
        // back key 다시 원상복구 시켜놓기!
        LoginMain activity = (LoginMain) getActivity();
        if(activity != null)
        {
            activity.setOnKeyBackPressedListener(null);
            activity.onBackPressed();
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        ((LoginMain) context).setOnKeyBackPressedListener(this);
        saleCurTabListener = this;
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
                Toast.makeText(getContext(), "sale set listener", Toast.LENGTH_SHORT).show();
                ((LoginMain) getContext()).setOnKeyBackPressedListener(saleCurTabListener);
                curTabListener = this;
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

}
