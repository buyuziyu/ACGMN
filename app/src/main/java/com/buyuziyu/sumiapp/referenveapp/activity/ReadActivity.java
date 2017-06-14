package com.buyuziyu.sumiapp.referenveapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.buyuziyu.sumiapp.referenveapp.R;
import com.buyuziyu.sumiapp.referenveapp.view.MyPhotoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by buyuziyu on 2017/5/16.
 */

public class ReadActivity extends Activity {

    public static final String TAG = "TAG";
    public static final String tag = ReadActivity.class.getSimpleName() + " | ";

    String FILEURI = "file://";
    private Context mContext;
    private int position;
    private String childpath;
    private String singleComicName;

    @Bind(R.id.myPhotoView)
    MyPhotoView mMyPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picview_activity);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);

//        position = savedInstanceState.getInt("position");
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        position = intent.getIntExtra("position", -1);
        childpath = intent.getStringExtra("childpath");
        singleComicName = intent.getStringExtra("singleComicName");
        Log.e(TAG, tag + childpath + File.separator + singleComicName);


        final int widthPixels = getResources().getDisplayMetrics().widthPixels;
        final int heightPixels = getResources().getDisplayMetrics().heightPixels;

        File[] uri = new File(childpath + File.separator + singleComicName).listFiles();
        Log.i(TAG, tag + uri[0].getAbsolutePath());

//        mMyPhotoView.setImageUri(FILEURI + uri[0].getAbsolutePath(), null);

        try {
            Log.i(TAG, tag + "try...");
            InputStream open = new FileInputStream(uri[0]);
            Bitmap bitmap = BitmapFactory.decodeStream(open);
//            mMyPhotoView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            mMyPhotoView.setImageBitmap(bitmap);
//            mMyPhotoView.setImageUri("http://img5.duitang.com/uploads/item/201511/04/20151104214718_FfnST.jpeg"
//                    , null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        Log.i(TAG, tag + "onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, tag + "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, tag + "onDestroy");
        super.onDestroy();
    }

}
