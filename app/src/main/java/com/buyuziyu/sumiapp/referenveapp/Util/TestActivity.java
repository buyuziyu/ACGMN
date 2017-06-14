package com.buyuziyu.sumiapp.referenveapp.Util;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.buyuziyu.sumiapp.referenveapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TestActivity extends AppCompatActivity {


    Uri uri1 = Uri.parse("http://www.005.tv/uploads/allimg/170316/2152564W0-0.jpg");

    String uri2 = "file://" + Environment.getExternalStorageDirectory() + File.separator + "文件匣"
            + File.separator + "cache" + File.separator + "donjin" + File.separator + "a.jpg";

    String FILEURI = "file://";
    String CACHEPATH = Environment.getExternalStorageDirectory() + File.separator + "文件匣"
            + File.separator + "cache" + File.separator;
    String uri3 = FILEURI + CACHEPATH + "donjin" + File.separator + "a.jpg";



    String PATH4 = "file://" + Environment.getExternalStorageDirectory() + File.separator + "文件匣"
            + File.separator + "cache" + File.separator;
    String uri4 = PATH4 + "donjin" + File.separator +  "a.jpg";


    @Bind(R.id.frescoView1)
    SimpleDraweeView mFrescoView1;
    @Bind(R.id.frescoView2)
    SimpleDraweeView mFrescoView2;
    @Bind(R.id.frescoView3)
    SimpleDraweeView mFrescoView3;
    @Bind(R.id.frescoView4)
    SimpleDraweeView mFrescoView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        mFrescoView1.setImageURI(uri1);
        mFrescoView2.setImageURI(uri2);
        mFrescoView3.setImageURI(uri3);
        mFrescoView4.setImageURI(uri4);
    }
}
