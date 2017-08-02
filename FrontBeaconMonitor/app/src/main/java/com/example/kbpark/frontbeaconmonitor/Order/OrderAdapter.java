package com.example.kbpark.frontbeaconmonitor.Order;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KBPark on 2016. 8. 2..
 */
public class OrderAdapter extends BaseAdapter
{
    // Item들을 관리할 수 있는 List<Item>을 여기서 선언해 관리한다.

    Context mContext;

    List<Item> mItems = new ArrayList<Item>();

    public void addItem(Item item) // List에 Item객체를 추가하는건 외부에서 한다.
    {
        mItems.add(item);
        this.notifyDataSetChanged();
    }

    public void removeItem(int position)
    {
        mItems.remove(position);
        this.notifyDataSetChanged();
    }


    public OrderAdapter(Context context)
    {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ItemView view = null;

        if(convertView != null)
        {
            view = (ItemView) convertView;
        }else
        {
            view = new ItemView(mContext);
        }

        // 이렇게 활용한다는 걸 명심!! 아주 중요!!
        view.setName((String)mItems.get(position).getData(0));
        view.setAge((String) mItems.get(position).getData(1));

        return view;
    }
}
