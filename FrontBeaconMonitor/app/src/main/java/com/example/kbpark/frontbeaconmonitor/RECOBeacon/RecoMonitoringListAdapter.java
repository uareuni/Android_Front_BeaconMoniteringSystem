///**
// * The MIT License (MIT)
// *
// * Copyright (c) 2014-2015 Perples, Inc.
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in
// * all copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// * THE SOFTWARE.
// */
//package com.example.kbpark.frontbeaconmonitor.RECOBeacon;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.example.kbpark.frontbeaconmonitor.Cons;
//import com.example.kbpark.frontbeaconmonitor.R;
//import com.perples.recosdk.RECOBeaconRegion;
//import com.perples.recosdk.RECOBeaconRegionState;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static com.example.kbpark.frontbeaconmonitor.Cons.MONITORING_STATE;
//import static com.example.kbpark.frontbeaconmonitor.Event.EventMain.eventAdapter;
//
//public class RecoMonitoringListAdapter extends BaseAdapter {
//
//    private HashMap<RECOBeaconRegion, RECOBeaconRegionState> mMonitoredRegions;
//    private HashMap<RECOBeaconRegion, String> mLastUpdateTime;
//    private HashMap<RECOBeaconRegion, Integer> mMatchedBeaconCounts;
//    private ArrayList<RECOBeaconRegion> mMonitoredRegionLists;
//
//    private LayoutInflater mLayoutInflater;
//
//    public RecoMonitoringListAdapter(Context context) {
//        super();
//        mMonitoredRegions = new HashMap<RECOBeaconRegion, RECOBeaconRegionState>();
//        mLastUpdateTime = new HashMap<RECOBeaconRegion, String>();
//        mMatchedBeaconCounts = new HashMap<RECOBeaconRegion, Integer>();
//        mMonitoredRegionLists = new ArrayList<RECOBeaconRegion>();
//
//        mLayoutInflater = LayoutInflater.from(context);
//    }
//
//    public void updateRegion(RECOBeaconRegion recoRegion, RECOBeaconRegionState recoState, int beaconCount, String updateTime) {
//        mMonitoredRegions.put(recoRegion, recoState);
//        mLastUpdateTime.put(recoRegion, updateTime);
//        mMatchedBeaconCounts.put(recoRegion, beaconCount);
//        if(!mMonitoredRegionLists.contains(recoRegion)) {
//            mMonitoredRegionLists.add(recoRegion);
//        }
//    }
//
//    public void clear() {
//        mMonitoredRegions.clear();
//    }
//
//    @Override
//    public int getCount() {
//        return mMonitoredRegions.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mMonitoredRegions.get(mMonitoredRegionLists.get(position));
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//
//        if(convertView == null) {
//            convertView = mLayoutInflater.inflate(R.layout.item_monitoring_region, parent, false);
//            viewHolder = new ViewHolder();
//            viewHolder.recoRegionID = (TextView)convertView.findViewById(R.id.region_uniqueID);
//            viewHolder.recoRegionState = (TextView)convertView.findViewById(R.id.region_state);
//            viewHolder.recoRegionTime = (TextView)convertView.findViewById(R.id.region_update_time);
//            viewHolder.recoRegionBeaconCount = (TextView)convertView.findViewById(R.id.region_beacon_count);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder)convertView.getTag();
//        }
//
//        RECOBeaconRegion recoRegion = mMonitoredRegionLists.get(position);
//        RECOBeaconRegionState recoState = mMonitoredRegions.get(recoRegion);
//
//        String recoRegionUniqueID = recoRegion.getUniqueIdentifier();
//        String recoRegionState = recoState.toString();
//        String recoUpdateTime = mLastUpdateTime.get(recoRegion);
//        String recoBeaconCount = mMatchedBeaconCounts.get(recoRegion).toString();
//
//        viewHolder.recoRegionID.setText(recoRegionUniqueID);
//        viewHolder.recoRegionState.setText(recoRegionState);
//        viewHolder.recoRegionTime.setText(recoUpdateTime);
//
//        if(recoRegionState.equals(RECOBeaconRegionState.RECOBeaconRegionInside.toString()) && mMatchedBeaconCounts.get(recoRegion) == 0) {
//            viewHolder.recoRegionBeaconCount.setText("You started monitoring inside of the region.");
//
//            MONITORING_STATE = Cons.IN;
//
//            Log.i("TTEST", "call됨!! : ");
//
////            orderCart에 가서야 addItem을 해주니까 여기서는 item을 봤자 갯수가 0개가 뜬다.
////            Service로 돌려서 지속적으로 확인하게 해야겠다!
//
////            // listview에 item이 있는 경우에만 order진행!
////            if(orderAdapter.getCount() != 0) {
////                // listview의 item갯수만큼 product 뽑아내서 보내기!
////                for(int i=0; i<orderAdapter.getCount(); i++){
////                    Log.i("TTEST", "주문할 item 이름 : " + orderAdapter.getProduct(i) + "\n");
////                    User.getInstance().order(orderAdapter.getProduct(i), orderAdapter.getProductNum(i));
//////////////////////////////////////////////// 보내고 list에서 지우는 것도 수행해야함!! //////////////////////////////////////////////////////////////////
////                }
////
////
////            }
////            dddddd 여기서 adapter 아이템들을 서버로 하나씩 보내고, 하나씩 list에서 삭제!
////            service를 돌려서 monitoring state가 in인걸 check하는 순간
//
//
//
//            Log.i("TTEST", "이미 beacon 범위 안에 있습니다!");
//            Log.i("TTEST", "현재 주문 완료 된 item 수 : " + eventAdapter.getCount() + "개");
//
//
//
//            return convertView;
//        }
//
//        if(recoRegionState.equals(RECOBeaconRegionState.RECOBeaconRegionOutside.toString())) {
//            viewHolder.recoRegionBeaconCount.setText("No beacons around.");
//
//            MONITORING_STATE = Cons.OUT;
//            Log.i("TTEST", "주변에 beacon이 없습니다.");
//            return convertView;
//        }
//
//        viewHolder.recoRegionBeaconCount.setText("# of beacons in the region: " + recoBeaconCount);
//
////        return convertView;
//        return null;
//    }
//
//    static class ViewHolder {
//        TextView recoRegionID;
//        TextView recoRegionState;
//        TextView recoRegionTime;
//        TextView recoRegionBeaconCount;
//    }
//
//}
