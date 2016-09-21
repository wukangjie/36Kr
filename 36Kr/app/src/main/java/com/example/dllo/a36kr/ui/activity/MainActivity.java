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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.fragment.DiscoverFragment;
import com.example.dllo.a36kr.ui.fragment.EquityFragment;
import com.example.dllo.a36kr.ui.fragment.MessageFragment;
import com.example.dllo.a36kr.ui.fragment.MineFragment;
import com.example.dllo.a36kr.ui.fragment.NewsFragment;
import com.example.dllo.a36kr.ui.fragment.NewsUseFragment;
import com.example.dllo.a36kr.utils.AllContantValues;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ItoContralActivity,View.OnClickListener  {
    private DrawerLayout mainDrawerLayout;
    private TabLayout mainTl;
    private ViewPager mainVp;
    private List<Fragment> fragments;
    private LinearLayout lineatLayout;
    private LinearLayout allLl;
    private LinearLayout earlyLl;
    private LinearLayout blateLl;
    private LinearLayout bigLl;
    private LinearLayout capitalLl;
    private LinearLayout depthLl;
    private LinearLayout researchLl;
    private ImageView backImg;
    private NewsFragment newsFragment;


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
        allLl = (LinearLayout) findViewById(R.id.main_drawer_all_ll);
        earlyLl = (LinearLayout) findViewById(R.id.main_drawer_early_ll);
        blateLl = (LinearLayout) findViewById(R.id.main_drawer_blate_ll);
        bigLl = (LinearLayout) findViewById(R.id.main_drawer_big_ll);
        capitalLl = (LinearLayout) findViewById(R.id.main_drawer_capital_ll);
        depthLl = (LinearLayout) findViewById(R.id.main_drawer_depth_ll);
        researchLl = (LinearLayout) findViewById(R.id.main_drawer_research_ll);
        backImg = (ImageView) findViewById(R.id.main_drawer_img);
        lineatLayout = (LinearLayout) findViewById(R.id.main_drawer_ll);
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);


    }

    protected void initDatas() {
        fragments = new ArrayList<>();
        // 如果要用这个Fragment对象
        newsFragment = NewsFragment.newInstance();
        fragments.add(newsFragment);
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
        mainTl.setTabTextColors(Color.BLACK, Color.argb(255, 72, 118, 255));

        mainTl.setupWithViewPager(mainVp);
        mainTl.getTabAt(0).setText("新闻").setIcon(R.drawable.selector_news_tab);
        mainTl.getTabAt(1).setText("股权投资").setIcon(R.drawable.selector_equity_tab);
        mainTl.getTabAt(2).setText("发现").setIcon(R.drawable.selector_discover_tab);
        mainTl.getTabAt(3).setText("消息").setIcon(R.drawable.selector_message_tab);
        mainTl.getTabAt(4).setText("我的").setIcon(R.drawable.selector_mine_tab);
        allLl.setOnClickListener(this);
        earlyLl.setOnClickListener(this);
        blateLl.setOnClickListener(this);
        bigLl.setOnClickListener(this);
        capitalLl.setOnClickListener(this);
        depthLl.setOnClickListener(this);
        researchLl.setOnClickListener(this);
        backImg.setOnClickListener(this);

        mainTl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("zzz", "mainTl.getSelectedTabPosition():" + mainTl.getSelectedTabPosition());
                if (tab.getPosition() == 0){
                    mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }else {
                    mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_drawer_all_ll:
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.ALLNEWSURL,"新闻"));
                mainDrawerLayout.closeDrawer(lineatLayout);
                break;
            case R.id.main_drawer_early_ll:
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.EARLYNEWSURL,"早期项目"));
                mainDrawerLayout.closeDrawer(lineatLayout);
                break;
            case R.id.main_drawer_blate_ll:
//                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.BLATENEWSURL,"B轮后"));
                Toast.makeText(this, "这个网址不太好使", Toast.LENGTH_SHORT).show();
                mainDrawerLayout.closeDrawer(lineatLayout);
                break;
            case R.id.main_drawer_big_ll:
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.BIGNEWSURL,"大公司"));
                mainDrawerLayout.closeDrawer(lineatLayout);
                break;
            case R.id.main_drawer_capital_ll:
//                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.CAPITALNEWSURL,"资本"));
                Toast.makeText(this, "这个网址不太好使", Toast.LENGTH_SHORT).show();
                mainDrawerLayout.closeDrawer(lineatLayout);
                break;
            case R.id.main_drawer_depth_ll:
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.DEPTHNEWSURL,"深度"));
                mainDrawerLayout.closeDrawer(lineatLayout);
                break;
            case R.id.main_drawer_research_ll:
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.RESEARCHNEWSURL,"研究"));
                mainDrawerLayout.closeDrawer(lineatLayout);
                break;
            case R.id.main_drawer_img:
                mainDrawerLayout.closeDrawer(lineatLayout);
                break;
        }

    }


    @Override
    public void toContralActivity(boolean state) {
      if (state){
          mainDrawerLayout.openDrawer(lineatLayout);
      }
    }

}
