package com.buyuziyu.sumiapp.referenveapp.fragments.comicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.buyuziyu.sumiapp.referenveapp.R;
import com.buyuziyu.sumiapp.referenveapp.activity.ReadActivity;
import com.buyuziyu.sumiapp.referenveapp.adapter.CoverAdapert;
import com.buyuziyu.sumiapp.referenveapp.base.BaseFragment;

import java.io.File;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by buyuziyu on 2017/4/29.
 */

public class OffprintFragment extends BaseFragment {

    public static final String TAG = "TAG";
    public static final String tag = OffprintFragment.class.getSimpleName() + " | ";

    public static final String CACHE_CHILD_PATH = Environment.getExternalStorageDirectory() +
            File.separator + "文件匣" + File.separator + "cache" + File.separator + "offprint";
    public static final String CHILD_PATH = Environment.getExternalStorageDirectory() +
            File.separator + "文件匣" + File.separator + "本子" + File.separator + "单行本";

    private String[] datas;
    private CoverAdapert adapter;


    @Bind(R.id.comic_list)
    GridView mgvComicList;




    @Override
    protected View initView() {
        Log.e(TAG, tag + "单行本Fragment页面被初始化了...");
        View view = View.inflate(getContext(), R.layout.fragment_home_frame, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        Log.e(TAG, tag + "单行本数据初始化了...");
        //准备数据
        datas = getComicName();

        // 初始化Gridview
        CoverAdapert imageloaderGridviewAdapter = new CoverAdapert(mContext, datas, "offprint");
        mgvComicList.setAdapter(imageloaderGridviewAdapter);

        mgvComicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e(TAG, tag + "onItemClick...");
                Intent intent = new Intent(mContext, ReadActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("childpath", CHILD_PATH);
                intent.putExtra("singleComicName", datas[position].substring(0, datas[position].length() - 4));
                Log.e(TAG, tag + CHILD_PATH + File.separator + datas[position].substring(0, datas[position].length() - 4));
                mContext.startActivity(intent);
            }
        });

    }

    /*
    * 在单行本缓存文件夹中获取所有缓存图片
    * */
    private String[] getComicName() {
        File comicCovers = new File(CACHE_CHILD_PATH);
        String[] temp = comicCovers.list();
        Arrays.sort(temp);
        return temp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
