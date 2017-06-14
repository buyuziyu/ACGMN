package com.buyuziyu.sumiapp.referenveapp.fragments;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.buyuziyu.sumiapp.referenveapp.base.BaseFragment;


/**
 * Created by buyuziyu on 2017/4/29.
 */

public class SettingFragment extends BaseFragment {

    public static final String TAG = "TAG";
    public static final String tag = SettingFragment.class.getSimpleName() + " | ";

    TextView textView;

    @Override
    protected View initView() {
        Log.e(TAG,tag + "自定义页面初始化了...");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, tag + "自定义数据初始化了...");
        textView.setText("Setting页面");

    }
}
