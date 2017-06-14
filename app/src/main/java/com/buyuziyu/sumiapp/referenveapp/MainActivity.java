package com.buyuziyu.sumiapp.referenveapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.buyuziyu.sumiapp.referenveapp.Util.TestActivity;
import com.buyuziyu.sumiapp.referenveapp.base.BaseFragment;
import com.buyuziyu.sumiapp.referenveapp.fragments.comicfragment.ComicMarketFragment;
import com.buyuziyu.sumiapp.referenveapp.fragments.comicfragment.DonjinComicFragment;
import com.buyuziyu.sumiapp.referenveapp.fragments.comicfragment.OffprintFragment;
import com.buyuziyu.sumiapp.referenveapp.fragments.comicfragment.PixivFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FloatingActionButton fab;
    List<BaseFragment> mNavigationFragmentList;
    /*
    * 被选中的fragment的对应位置标志
    * */
    private int position = 0;
    /*
    *上次切换的fragment
     */
    private Fragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化视图和监听器
        initViewAndListener();

        //初始化Fragment
        initFragment();

    }

    /*
    * **********************************************************************************************
    *                                   初始化方法集
    * **********************************************************************************************
    * */
    private void initViewAndListener() {

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(0).setChecked(true);
    }

    private void initFragment() {
        mNavigationFragmentList = new ArrayList<>();
        mNavigationFragmentList.add(new OffprintFragment());
        mNavigationFragmentList.add(new DonjinComicFragment());
        mNavigationFragmentList.add(new ComicMarketFragment());
        mNavigationFragmentList.add(new PixivFragment());
        toSetFrame();
    }


    /*
    * **********************************************************************************************
    *                                   get方法集
    * **********************************************************************************************
    * */



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_offprint) {
            // Handle the camera action
            position = 0;
            toSetFrame();
        } else if (id == R.id.nav_donjin_comic) {
            position = 1;
            toSetFrame();
        } else if (id == R.id.nav_comic_market) {
            position = 2;
            toSetFrame();
        } else if (id == R.id.nav_pixiv) {
            position = 3;
            toSetFrame();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /*
   * **********************************************************************************************
   *                                   自定义组件集
   * **********************************************************************************************
   * */
    /*
    * 配置底部导航菜单Listener
    * */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_comic:
//                    position = 0;
//                    toSetFrame();
                    return true;

                case R.id.navigation_music:
//                    position = 0;
//                    toSetFrame();
                    return true;

                case R.id.navigation_anime:
//                    position = 0;
//                    toSetFrame();
                    return true;

                case R.id.navigation_setting:
//                    position = 1;
//                    toSetFrame();
                    return true;
            }
            return false;
        }
    };


    /*
    * 切换Fragment
    * */
    private void toSetFrame() {
        //根据positioin获取对应fragment
        BaseFragment to = getFragment();
        switchFragment(lastFragment, to);
    }

    /*
    * @param from   上一次现实的fragment
    * @param to     当前要显示的fragment
    * */
    private void switchFragment(Fragment from, BaseFragment to) {
        if (from != to ) {
            lastFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //判断目标fragment是否已经成功添加
            if (!to.isAdded()) {
                //to没被添加
                //隐藏from
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.content_main_fl_content, to).commit();
                }
            } else {
                //to已经被添加
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }
    }

    private BaseFragment getFragment() {
        BaseFragment basefragment = mNavigationFragmentList.get(position);
        return basefragment;
    }
}
