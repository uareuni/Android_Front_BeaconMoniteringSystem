package com.example.kbpark.frontbeaconmonitor.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kbpark.frontbeaconmonitor.fragments.CouponMain;
import com.example.kbpark.frontbeaconmonitor.fragments.SaleMain;

import static com.example.kbpark.frontbeaconmonitor.Cons.FIRST_TITLE_NAME;
import static com.example.kbpark.frontbeaconmonitor.Cons.SECOND_TITLE_NAME;

/**
 * Created by KBPark on 2017. 2. 21..
 */

public class TabsPagerAdapter extends FragmentPagerAdapter
{
    public TabsPagerAdapter(FragmentManager fm) { super(fm); }

    @Override
    public Fragment getItem(int position)
    {
        // 초기 fragment는 이렇게 adapter를 이용해서 붙인다.
        if(position == 0)
        {
            return SaleMain.newInstance();
        }
        else
        {
            return CouponMain.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        if(position == 1)
        {
            return FIRST_TITLE_NAME;
        } else
        {
            return SECOND_TITLE_NAME;
        }
    }
}
