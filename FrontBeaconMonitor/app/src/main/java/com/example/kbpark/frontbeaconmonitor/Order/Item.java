package com.example.kbpark.frontbeaconmonitor.Order;

/**
 * Created by KBPark on 2016. 8. 3..
 */

// 얘는 말그대로 하나의 item에 대한 정보들을 담는 클래스.
// 1객체 = 1item
public class Item
{
    private Object[] mData; // 자료형이 겹치니 이렇게 하더라고. (참고: MultiMemoStage01.project)

    public Item(String name, String age)
    {
        mData = new Object[2];

        mData[0] = name;
        mData[1] = age;
    }

    // adapter에서 data를 사용할 수 있도록 메소르들 정의해 놓는다.
    public Object[] getData()
    {
        return mData;
    }

    public Object getData(int index)
    {
        if(mData == null || index >= mData.length)
        {
            return null;
        }

        return mData[index];
    }
}
