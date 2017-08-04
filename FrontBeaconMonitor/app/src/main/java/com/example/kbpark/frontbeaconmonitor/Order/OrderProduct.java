package com.example.kbpark.frontbeaconmonitor.Order;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kbpark.frontbeaconmonitor.LoginMain;
import com.example.kbpark.frontbeaconmonitor.R;

import static com.example.kbpark.frontbeaconmonitor.LoginMain.curTabListener;
import static com.example.kbpark.frontbeaconmonitor.Order.OrderCart.orderAdapter;

/**
 * Created by KBPark on 2017. 8. 1..
 */

public class OrderProduct extends Fragment implements View.OnClickListener, LoginMain.onKeyBackPressedListener
{
    TabLayout tabLayout;
    Button btn_pay;
    Context context;

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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context); // context 이렇게 써도 될까??

        // 제목
        alertDialogBuilder.setTitle("결제하기");
        // AlertDialog
        alertDialogBuilder
                .setMessage("아메리카노 1잔 결제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("결제",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 결제함
                                // 2. 결제 완료시 '주문 내역 list'에 올리고 notify()
                                orderAdapter.addItem("아메리카노", "1잔");
                                orderAdapter.notifyDataSetChanged();

                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                // 다이얼로그 취소
                                dialog.cancel();
                            }
                        });

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        // 다이얼로그 보여주기
        alertDialog.show();








        // 3. beacon은 주문 내역에 item이 있는지 계속 탐지하게 한다. (있으면 : server로 push/ 없으면 : nothing)

    }



    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        ((LoginMain)context).setOnKeyBackPressedListener(this);

        tabLayout = (TabLayout)((LoginMain)context).findViewById(R.id.tabs);
        tabLayout.setVisibility(View.GONE); // GONE으로 하고 layout param로 layout_weight값 맞춰주는게 더 좋긴한데, 일단은 INVISIBLE.

        this.context = context;

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
