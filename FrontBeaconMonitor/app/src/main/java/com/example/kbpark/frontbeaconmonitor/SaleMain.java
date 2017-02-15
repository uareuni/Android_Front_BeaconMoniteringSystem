package com.example.kbpark.frontbeaconmonitor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by KBPark on 2017. 2. 16..
 */

public class SaleMain extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale_list);
        ButterKnife.bind(this);
    }

}
