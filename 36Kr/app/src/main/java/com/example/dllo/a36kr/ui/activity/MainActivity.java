package com.example.dllo.a36kr.ui.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.fragment.DiscoverFragment;
import com.example.dllo.a36kr.ui.fragment.EquityFragment;
import com.example.dllo.a36kr.ui.fragment.MessageFragment;
import com.example.dllo.a36kr.ui.fragment.MineFragment;
import com.example.dllo.a36kr.ui.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsBaseActivity {
    private TabLayout mainTl;
    private ViewPager mainVp;
    private List<Fragment> fragments;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mainTl = byView(R.id.main_tl);
        mainVp = byView(R.id.main_vp);

        fragments = new ArrayList<>();

    }

    @Override
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

        mainTl.setupWithViewPager(mainVp);
        mainTl.getTabAt(0).setCustomView(R.layout.tab_news);
        mainTl.getTabAt(1).setCustomView(R.layout.tab_equity);
        mainTl.getTabAt(2).setCustomView(R.layout.tab_discover);
        mainTl.getTabAt(3).setCustomView(R.layout.tab_message);
        mainTl.getTabAt(4).setCustomView(R.layout.tab_mine);



    }
}
