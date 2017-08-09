package com.example.kbpark.frontbeaconmonitor.Order;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kbpark.frontbeaconmonitor.LoginMain;
import com.example.kbpark.frontbeaconmonitor.R;

import static com.example.kbpark.frontbeaconmonitor.LoginMain.curTabListener;

/**
 * Created by KBPark on 2017. 2. 23..
 */

public class OrderCart extends Fragment implements LoginMain.onKeyBackPressedListener, AdapterView.OnItemClickListener
{
    public static OrderCartAdapter orderCartAdapter = new OrderCartAdapter();

    TabLayout tabLayout;
    ListView cartOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.order_cart, container, false);

        // for test
        cartOrder = (ListView) rootView.findViewById(R.id.listview_order_cart);
        cartOrder.setOnItemClickListener(this);
        cartOrder.setAdapter(orderCartAdapter);

        return rootView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        ((LoginMain)context).setOnKeyBackPressedListener(this);

        tabLayout = (TabLayout)((LoginMain)context).findViewById(R.id.tabs);
        tabLayout.setVisibility(View.INVISIBLE); // GONE으로 하고 layout param로 layout_weight값 맞춰주는게 더 좋긴한데, 일단은 INVISIBLE.

//        cartOrder = (ListView) ((LoginMain) context).findViewById(R.id.listview_order_cart);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), position + "번 item clicked!", Toast.LENGTH_SHORT).show();
    }
}
