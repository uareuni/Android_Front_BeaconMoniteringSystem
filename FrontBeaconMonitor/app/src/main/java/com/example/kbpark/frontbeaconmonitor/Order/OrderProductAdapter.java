package com.example.kbpark.frontbeaconmonitor.Order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kbpark.frontbeaconmonitor.R;

import java.util.ArrayList;

/**
 * Created by KBPark on 2017. 8. 8..
 */

public class OrderProductAdapter extends BaseAdapter
{
    private ArrayList<OrderItem> ORDER_ITEMS = new ArrayList<>();

    public void addItem(String product, String num, int price)
    {
        ORDER_ITEMS.add(new OrderItem(product, num, price));
        this.notifyDataSetChanged();
    }

    public void addItem(OrderItem item){
        ORDER_ITEMS.add(item);
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
        ImageView prodImage = (ImageView) convertView.findViewById(R.id.imageview_prod_image);

        product.setText(item.getProduct());
        num.setText(item.getProductNum());
        price.setText((item.getPrice() * Integer.parseInt(item.getProductNum())) + ""); // price * num
        prodImage.setImageResource(item.getImageRes());

//        Log.i("ORDER_TEST", "getView call됨");
//        Log.i("ORDER_TEST", "view state : " + item.getOrderState());

        return convertView;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight;
        listView.setLayoutParams(params);

        listView.requestLayout();
    }

}
