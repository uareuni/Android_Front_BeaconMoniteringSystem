package com.example.kbpark.frontbeaconmonitor.Order;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kbpark.frontbeaconmonitor.LoginMain;
import com.example.kbpark.frontbeaconmonitor.R;

import static com.example.kbpark.frontbeaconmonitor.LoginMain.curTabListener;
import static com.example.kbpark.frontbeaconmonitor.Order.OrderCart.orderCartAdapter;

/**
 * Created by KBPark on 2017. 8. 1..
 */

public class OrderProduct extends Fragment implements View.OnClickListener, LoginMain.onKeyBackPressedListener
{
    OrderProductAdapter prodAdapter;
    ListView prodOrder;

    Product americano;
    Product caffeLatte;
    Product cappuccino;
    Product lemonAde;
    Product cherryAde;
    Product peachIceTea;

    Button btn_americano;
    Button btn_caffeLatte;
    Button btn_cappuccino;
    Button btn_lemonAde;
    Button btn_cherryAde;
    Button btn_peachIceTea;
    Button btn_pay;

    TextView tv_americano;
    TextView tv_caffeLatte;
    TextView tv_cappuccino;
    TextView tv_lemonAde;
    TextView tv_cherryAde;
    TextView tv_peachIceTea;


    TabLayout tabLayout;
    Context context;

    String product;
    int num = 0; // the number of product

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.order_product, container, false);
        viewInit(rootView);

        return rootView;
    }





    @Override
    public void onClick(View v)
    {

        if(v.getId() != R.id.btn_pay)
        {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.order_prod_dialog, null);
                ImageView prodImage = (ImageView) layout.findViewById(R.id.image_order_prod);

            /** 1. product 결정 **/
            switch (v.getId())
            {
                case R.id.btn_menu1:
                    product = "아메리카노";
                    prodImage.setImageResource(R.mipmap.americano);
                    break;
                case R.id.btn_menu2:
                    product = "카페라떼";
                    prodImage.setImageResource(R.mipmap.caffelatte);
                    break;
                case R.id.btn_menu3:
                    product = "카푸치노";
                    prodImage.setImageResource(R.mipmap.cappuccino);
                    break;
                case R.id.btn_menu4:
                    product = "레몬에이드";
                    prodImage.setImageResource(R.mipmap.lemonade);
                    break;
                case R.id.btn_menu5:
                    product = "체리에이드";
                    prodImage.setImageResource(R.mipmap.cherryade);
                    break;
                case R.id.btn_menu6:
                    product = "복숭아아이스티";
                    prodImage.setImageResource(R.mipmap.peachicetea);
                    break;
            }

            // 제목
            alertDialogBuilder.setTitle("음료 추가");
            // AlertDialog
            alertDialogBuilder
                    .setView(layout)
                    .setMessage(product + " " + num + "잔 추가하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("추가",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    /** 3. listview에 추가하기 **/
                                    prodAdapter.addItem(product, num + "");

                                }
                            })
                    .setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                public void onClick( DialogInterface dialog, int id) {
                                    num = 0;
                                    dialog.cancel(); // 다이얼로그 취소
                                }
                            })
                    .setOnKeyListener(new DialogInterface.OnKeyListener() { // back key 눌렀을때도 dialog 취소
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_BACK) {
                                // 다이얼로그 취소
                                dialog.cancel();
                                return true;
                            }
                            return false;
                        }
                    });



            /** 2. 수량 결정 **/
            RadioGroup radioGroup = (RadioGroup) layout.findViewById(R.id.radioGroup);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId){
                        case R.id.radio_1 : num=1; break;
                        case R.id.radio_2 : num=2; break;
                        case R.id.radio_3 : num=3; break;
                        case R.id.radio_4 : num=4; break;
                        case R.id.radio_5 : num=5; break;
                    }
                }
            });

            // 다이얼로그 생성
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }












        else // if btn_pay clicked
        {
            // 1. popup : 결제 하시겠습니까? 결제가 되었습니다
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context); // context 이렇게 써도 될까??

            // 제목
            alertDialogBuilder.setTitle("결제 하기");
            // AlertDialog
            alertDialogBuilder
                    .setMessage("결제 하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("결제",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    /** 1. 현재 list에 있는걸 모두 cart에 올리고 **/
                                    for(int i=0; i<prodAdapter.getCount(); i++){
                                        orderCartAdapter.addItem((OrderItem)prodAdapter.getItem(i));
                                    }

                                    /** 2. fragment 종료 **/
                                    onBackKey();
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
        }

    }


    private void viewInit(View rootView)
    {
        prodAdapter = new OrderProductAdapter();

        americano = new Product("아메리카노", 2500);
        caffeLatte = new Product("카페라떼", 3200);
        cappuccino = new Product("카푸치노", 3200);
        lemonAde = new Product("레몬 에이드", 3500);
        cherryAde = new Product("체리 에이드", 3500);
        peachIceTea = new Product("복숭아 아이스티", 2800);

        btn_americano = (Button) rootView.findViewById(R.id.btn_menu1);
        btn_caffeLatte = (Button) rootView.findViewById(R.id.btn_menu2);
        btn_cappuccino = (Button) rootView.findViewById(R.id.btn_menu3);
        btn_lemonAde = (Button) rootView.findViewById(R.id.btn_menu4);
        btn_cherryAde = (Button) rootView.findViewById(R.id.btn_menu5);
        btn_peachIceTea = (Button) rootView.findViewById(R.id.btn_menu6);
        btn_pay = (Button) rootView.findViewById(R.id.btn_pay);

        tv_americano = (TextView) rootView.findViewById(R.id.tv_menu1);
        tv_caffeLatte = (TextView) rootView.findViewById(R.id.tv_menu2);
        tv_cappuccino = (TextView) rootView.findViewById(R.id.tv_menu3);
        tv_lemonAde = (TextView) rootView.findViewById(R.id.tv_menu4);
        tv_cherryAde = (TextView) rootView.findViewById(R.id.tv_menu5);
        tv_peachIceTea = (TextView) rootView.findViewById(R.id.tv_menu6);

        tv_americano.setText(americano.getName());
        tv_caffeLatte.setText(caffeLatte.getName());
        tv_cappuccino.setText(cappuccino.getName());
        tv_lemonAde.setText(lemonAde.getName());
        tv_cherryAde.setText(cherryAde.getName());
        tv_peachIceTea.setText(peachIceTea.getName());

        btn_americano.setOnClickListener(this);
        btn_caffeLatte.setOnClickListener(this);
        btn_cappuccino.setOnClickListener(this);
        btn_lemonAde.setOnClickListener(this);
        btn_cherryAde.setOnClickListener(this);
        btn_peachIceTea.setOnClickListener(this);
        btn_pay.setOnClickListener(this);

        btn_americano.setBackgroundResource(R.mipmap.americano);
        btn_caffeLatte.setBackgroundResource(R.mipmap.caffelatte);
        btn_cappuccino.setBackgroundResource(R.mipmap.cappuccino);
        btn_lemonAde.setBackgroundResource(R.mipmap.lemonade);
        btn_cherryAde.setBackgroundResource(R.mipmap.cherryade);
        btn_peachIceTea.setBackgroundResource(R.mipmap.peachicetea);


        // listview
        prodOrder = (ListView) rootView.findViewById(R.id.listview_product_sum);
        prodOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /** 추후에 'item 취소 구현' : 꾸욱 누르고 있으면 '취소하시겠습니까?' 띄우기! **/
                Toast.makeText(parent.getContext(), position + "번 item clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        prodOrder.setAdapter(prodAdapter);

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
