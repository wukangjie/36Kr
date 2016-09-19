package com.example.dllo.a36kr.ui.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.fragment.DiscoverFragment;
import com.example.dllo.a36kr.ui.fragment.EquityFragment;
import com.example.dllo.a36kr.ui.fragment.MessageFragment;
import com.example.dllo.a36kr.ui.fragment.MineFragment;
import com.example.dllo.a36kr.ui.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity  {
    private TabLayout mainTl;
    private ViewPager mainVp;
    private List<Fragment> fragments;
    private LinearLayout lineatLayout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }

    private void initViews() {
        mainTl = (TabLayout) findViewById(R.id.main_tl);
        mainVp = (ViewPager) findViewById(R.id.main_vp);
        fragments = new ArrayList<>();
        lineatLayout = (LinearLayout) findViewById(R.id.main_drawer_ll);


    }

    protected void initDatas() {
        fragments.add(new NewsFragment());
        fragments.add(new EquityFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MineFragment());
        mainVp.setAdapter(new FragmentPagerAdapter
                (getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mainTl.setTabTextColors(Color.BLACK,Color.argb(255,72,118,255));

        mainTl.setupWithViewPager(mainVp);
//            mainTl.getTabAt(0).setText("我们").setIcon(R.drawable.selector_news_tab);
            mainTl.getTabAt(0).setText("新闻").setIcon(R.drawable.selector_news_tab);
            mainTl.getTabAt(1).setText("股权投资").setIcon(R.drawable.selector_equity_tab);
            mainTl.getTabAt(2).setText("发现").setIcon(R.drawable.selector_discover_tab);
            mainTl.getTabAt(3).setText("消息").setIcon(R.drawable.selector_message_tab);
            mainTl.getTabAt(4).setText("我的").setIcon(R.drawable.selector_mine_tab);



    }


}
