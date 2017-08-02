package com.example.kbpark.frontbeaconmonitor.Order;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
 * Created by KBPark on 2017. 2. 21..
 */

public class OrderMain extends Fragment implements View.OnClickListener, LoginMain.onKeyBackPressedListener
{
    public static OrderMain newInstance() { return new OrderMain(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.order_main, container, false);
        viewInit(rootView);
        return rootView;
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_order:
                Toast.makeText(getContext(), "order clicked! 주문했습니다.", Toast.LENGTH_SHORT).show();

                FragmentManager manager = getActivity().getSupportFragmentManager(); // 여기 괜찮을까?
                manager.beginTransaction()
                        .add(R.id.contaner, new OrderProduct())
                        .commit();

                // order retrofit test통신

//                String email = "pkb@pkb.pkb";
//                String pw = "123456";
//
//                String phone = "010-1111-2222";
//                String product = "아메리카노";
//                String num = "2";
//
//                /** layout test를 위해 우선 주석처리 **/
//                User user = new User(email, pw);
//                String orderRes = user.order(email, phone, product, num); // 실제로 order가 일어나는 부분 ///////////////////////////////////////////


                /** 추가로 해야할거 **/
                // 1. 주문시 팝업창 '주문 하시겠습니까?' (Y/N)
                // 2. listView에 추가하기!

                break;
        }
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


    private void viewInit(View rootView)
    {
        Button btn_order = (Button) rootView.findViewById(R.id.btn_order);

        btn_order.setOnClickListener(this);
    }

}
