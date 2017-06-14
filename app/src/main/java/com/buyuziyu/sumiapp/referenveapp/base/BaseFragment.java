package com.buyuziyu.sumiapp.referenveapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by buyuziyu on 2017/4/29.
 */

public abstract class BaseFragment extends Fragment {

    /*
    * 上下文
    * */
    protected Context mContext;

    /*
    * 类初始化标志
    * */
    private boolean isInit = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /*
    * 由子类实现该方法，创建自己的视图
    * */
    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /*
    * 子类初始化数据，重写该方法
    * */
    protected void initData() {
    }
}
