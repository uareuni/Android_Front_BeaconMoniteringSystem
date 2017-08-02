package com.example.kbpark.frontbeaconmonitor.Order;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kbpark.frontbeaconmonitor.R;

/**
 * Created by KBPark on 2016. 8. 2..
 */

// 얘는 말그대로 View와 관련된 control만 수행하는군.

public class ItemView extends RelativeLayout
{
    TextView mName;
    TextView mAge;

    public ItemView(Context context)
    {
        super(context);
        init(context);
    }

    public ItemView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public void setName(String name)
    {
        mName.setText(name);

    }

    public void setAge(String age)
    {
        mAge.setText(age);
    }



    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_view, this, true);

        mName = (TextView) findViewById(R.id.name);
        mAge = (TextView) findViewById(R.id.age);
    }

}
