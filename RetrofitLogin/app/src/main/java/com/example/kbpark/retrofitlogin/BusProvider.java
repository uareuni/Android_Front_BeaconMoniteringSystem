package com.example.kbpark.retrofitlogin;

import com.squareup.otto.Bus;

/**
 * Created by KBPark on 2017. 1. 26..
 */

public class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    public BusProvider(){}
}