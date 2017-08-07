package com.example.kbpark.frontbeaconmonitor.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kbpark.frontbeaconmonitor.R;

import java.util.ArrayList;

/**
 * Created by KBPark on 2016. 8. 2..
 */
public class OrderAdapter extends BaseAdapter
{
    /** 보통 collection framework이 adapter에 붙어서 adapter를 통해서 실 item에 add, remove하게 하는것이 일반적임! **/
    private ArrayList<OrderItem> ORDER_ITEMS = new ArrayList<>();

    public void addItem(String product, String num)
    {
        ORDER_ITEMS.add(new OrderItem(product, num));
        this.notifyDataSetChanged();
    }

    public String getProduct(int idx){
        return ORDER_ITEMS.get(idx).getProduct();
    }

    public String getProductNum(int idx){
        return ORDER_ITEMS.get(idx).getProductNum();
    }

    @Override
    public int getCount() {
        return ORDER_ITEMS.size();
    }

    @Override
    public Object getItem(int position) {
        return ORDER_ITEMS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_item, parent, false);
        }

        OrderItem item = ORDER_ITEMS.get(position);

//        ImageView iconImage = (ImageView) convertView.findViewById(R.id.product_image);
        TextView product = (TextView) convertView.findViewById(R.id.tv_product);
        TextView num = (TextView) convertView.findViewById(R.id.tv_num);
        TextView orderState = (TextView) convertView.findViewById(R.id.order_state);

        product.setText(item.getProduct());
        num.setText(item.getProductNum());
        orderState.setText(item.getOrderState()); // default : orderState

//        Log.i("ORDER_TEST", "getView call됨");
//        Log.i("ORDER_TEST", "view state : " + item.getOrderState());

        return convertView;
    }


}
