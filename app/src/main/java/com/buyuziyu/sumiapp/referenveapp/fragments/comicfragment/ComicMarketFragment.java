package com.buyuziyu.sumiapp.referenveapp.fragments.comicfragment;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.buyuziyu.sumiapp.referenveapp.R;
import com.buyuziyu.sumiapp.referenveapp.adapter.CoverAdapert;
import com.buyuziyu.sumiapp.referenveapp.base.BaseFragment;

import java.io.File;
import java.util.Arrays;

import butterknife.Bind;

/**
 * Created by buyuziyu on 2017/4/29.
 */

public class ComicMarketFragment extends BaseFragment {

    public static final String TAG = "TAG";
    public static final String tag = ComicMarketFragment.class.getSimpleName() + " | ";

    public static final String childCoimcPath = Environment.getExternalStorageDirectory() +
            File.separator + "文件匣" + File.separator + "本子" + File.separator + "C展";

    private ListView mListView;
    private String[] datas;
    private CoverAdapert adapter;

    TextView textView;
    @Bind(R.id.comic_list)
    GridView mgvComicList;

    @Override
    protected View initView() {
        Log.e(TAG, tag + "C展Fragment页面被初始化了...");
        View view = View.inflate(getContext(), R.layout.fragment_home_frame, null);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, tag + "C展数据初始化了...");
        //准备数据
        datas = getComicName();
        //设置适配器
//        adapter = new CoverAdapert(mContext, datas);
        mListView.setAdapter(adapter);
    }

    /*
    * 在单行本文件夹中获得所有子文件夹
    * */
    private String[] getComicName() {
        File children = new File(childCoimcPath);
        String[] temp = children.list();
        Arrays.sort(temp);
        return temp;
    }
}
