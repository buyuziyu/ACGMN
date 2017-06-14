package com.buyuziyu.sumiapp.referenveapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.buyuziyu.sumiapp.referenveapp.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by buyuziyu on 2017/4/29.
 */

public class CoverAdapert extends BaseAdapter {

    public static final String TAG = "TAG";
    public static final String tag = CoverAdapert.class.getSimpleName() + " | ";

    String FILEURI = "file://";
    String CACHEPATH = Environment.getExternalStorageDirectory() + File.separator + "文件匣"
            + File.separator + "cache" + File.separator;


    private final Context mContext;
    private final String[] mDatas;
    private final String type;
    private final ImageLoader imageLoader;
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showStubImage(R.color.庚斯博罗灰)          // 设置图片下载期间显示的图片
            .showImageForEmptyUri(android.R.drawable.stat_sys_warning)  // 设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(android.R.drawable.ic_menu_report_image)       // 设置图片加载或解码过程中发生错误显示的图片
//            .showImageOnFail(R.drawable.a2)
            .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)                          // 设置下载的图片是否缓存在SD卡中
            .bitmapConfig(Bitmap.Config.RGB_565)        // 设置图片的解码类型
            .build();                                   // 创建配置过得DisplayImageOption对象;

    public CoverAdapert(Context context, String[] datas, String type){
        this.mContext = context;
        this.mDatas = datas;
        this.type = type;

        // 初始化imageloader
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        Log.i(TAG, tag + "getCount" + "mDatas.length = " + mDatas.length);
        return mDatas.length;
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, tag + "getItem" + "position = " + position + "mDatas[position] = " + mDatas[position]);
        return mDatas[position];
    }

    @Override
    public long getItemId(int position) {
        Log.i(TAG, tag + "getItemId" + "position = " + position);
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null) {
            convertView = View.inflate(mContext, R.layout.singlecomicview, null);
            holder = new CoverAdapert.ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (CoverAdapert.ViewHolder) convertView.getTag();
        }

        // 显示图片
        Log.i(TAG, tag + "显示图片");
        switch (this.type){
            case "offprint":
                Log.i(TAG, tag + "单行本列表");

//                imageLoader.displayImage(FILEURI + CACHEPATH + type + File.separator + mDatas[position],
//                        holder.comicCover,
//                        options);

                holder.comicCover.setImageURI(FILEURI + CACHEPATH + type + File.separator + mDatas[position]);
//                holder.name.setText(mDatas[position]);
                break;

            case "comicmarket":
                Log.i(TAG, tag + "C展列表");

                break;

            case "pixiv":
                Log.i(TAG, tag + "pixiv列表");

                break;

            case "donjin":
                Log.i(TAG, tag + "同人本列表");

                break;
        }

        return convertView;
    }


    class ViewHolder{

        @Bind(R.id.comicView)
        SimpleDraweeView comicCover;

        ///@Bind(R.id.comicName)
       // TextView name;

        public ViewHolder(View view) {
//            Log.i(TAG, tag + "开始绑定ViewHolder");
            ButterKnife.bind(ViewHolder.this, view);
        }
    }

    class TextHolder{
        @Bind(R.id.comicName)
        TextView name;

        public TextHolder(View view) {
//            Log.i(TAG, tag + "开始绑定TextHolder");
            ButterKnife.bind(TextHolder.this, view);
        }
    }
}
