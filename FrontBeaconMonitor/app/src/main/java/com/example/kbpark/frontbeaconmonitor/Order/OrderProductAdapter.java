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
 * Created by KBPark on 2017. 8. 8..
 */

public class OrderProductAdapter extends BaseAdapter
{
    private ArrayList<OrderItem> ORDER_ITEMS = new ArrayList<>();

    public void addItem(String product, int num)
    {
        ORDER_ITEMS.add(new OrderItem(product, num));
        this.notifyDataSetChanged();
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
            convertView = inflater.inflate(R.layout.order_prod_item, parent, false);
        }

        OrderItem item = ORDER_ITEMS.get(position);

//        ImageView iconImage = (ImageView) convertView.findViewById(R.id.product_image);
        TextView product = (TextView) convertView.findViewById(R.id.tv_prod_product);
        TextView num = (TextView) convertView.findViewById(R.id.tv_prod_num);
        TextView price = (TextView) convertView.findViewById(R.id.tv_prod_price);

        product.setText(item.getProduct());
        num.setText(item.getProductNum());
        price.setText(item.getOrderState()); // default : orderState

//        Log.i("ORDER_TEST", "getView call됨");
//        Log.i("ORDER_TEST", "view state : " + item.getOrderState());

        return convertView;
    }
}
