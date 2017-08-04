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

    public void removeItem(int index)
    {
        ORDER_ITEMS.remove(index);
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

//        iconImage.setImageResource(item.getImageRes());
        product.setText(item.getProduct());
        num.setText(item.getProductNum());

        return convertView;
    }


//    // Item들을 관리할 수 있는 List<Item>을 여기서 선언해 관리한다.
//
//    Context mContext;
//
//    List<Item> mItems = new ArrayList<Item>();
//
//    public void addItem(Item item) // List에 Item객체를 추가하는건 외부에서 한다.
//    {
//        mItems.add(item);
//        this.notifyDataSetChanged();
//    }
//
//    public void removeItem(int position)
//    {
//        mItems.remove(position);
//        this.notifyDataSetChanged();
//    }
//
//
//    public OrderAdapter(Context context)
//    {
//        mContext = context;
//    }
//
//    @Override
//    public int getCount() {
//        return mItems.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mItems.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent)
//    {
//        ItemView view = null;
//
//        if(convertView != null)
//        {
//            view = (ItemView) convertView;
//        }else
//        {
//            view = new ItemView(mContext);
//        }
//
//        // 이렇게 활용한다는 걸 명심!! 아주 중요!!
//        view.setName((String)mItems.get(position).getData(0));
//        view.setAge((String) mItems.get(position).getData(1));
//
//        return view;
//    }
}
