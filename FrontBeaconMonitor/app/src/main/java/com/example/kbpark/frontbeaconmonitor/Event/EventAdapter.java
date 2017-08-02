package com.example.kbpark.frontbeaconmonitor.Event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kbpark.frontbeaconmonitor.R;

import java.util.ArrayList;

/**
 * Created by KBPark on 2017. 2. 23..
 */

public class EventAdapter extends BaseAdapter
{

    public static EventAdapter eventAdapter = new EventAdapter();
    /** 보통 collection framework이 adapter에 붙어서 adapter를 통해서 실 item에 add, remove하게 하는것이 일반적임! **/
    private ArrayList<EventItem> ITEMS = new ArrayList<>();

    public void addItem(int iconResId, String title, String desc)
    {
        ITEMS.add(new EventItem(iconResId, title, desc));
    }

    public void removeItem()
    {

    }

    @Override
    public int getCount() {
        return ITEMS.size();
    }

    @Override
    public Object getItem(int position) {
        return ITEMS.get(position);
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
            convertView = inflater.inflate(R.layout.event_item, parent, false);
        }

        EventItem item = ITEMS.get(position);

        ImageView iconImage = (ImageView) convertView.findViewById(R.id.coupon_image);
        TextView title = (TextView) convertView.findViewById(R.id.coupon_title);
        TextView desc = (TextView) convertView.findViewById(R.id.coupon_description);

        iconImage.setImageResource(item.getImageRes());
        title.setText(item.getTitle());
        desc.setText(item.getDesc());

        return convertView;
    }
}
