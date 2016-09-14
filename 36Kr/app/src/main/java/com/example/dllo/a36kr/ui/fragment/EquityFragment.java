package com.example.dllo.a36kr.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.adapter.EquityAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class EquityFragment extends AbsFragment {
    private TabLayout fragmentEquityTl;
    private List<Fragment> fragments;
    private String[] titles;
    private ViewPager fragmentEquityVp;
    @Override
    protected int setLayout() {
        return R.layout.fragment_equity;
    }

    @Override
    protected void initViews() {
        fragmentEquityTl = byView(R.id.fragment_equity_tl);
        fragmentEquityVp = byView(R.id.fragment_equity_vp);
        fragments = new ArrayList<>();
        fragments.add(EquityUseFragment.newInstance("www.baidu.com"));
        fragments.add(EquityUseFragment.newInstance("www.a.com"));
        fragments.add(EquityUseFragment.newInstance("www.b.com"));
        fragments.add(EquityUseFragment.newInstance("www.c.com"));

        titles = new String[fragments.size()];
        titles[0] = "全部";
        titles[1] = "募资中";
        titles[2] = "募资成功";
        titles[3] = "融资成功";
        EquityAdapter equityAdapter = new EquityAdapter(getChildFragmentManager());
        equityAdapter.setFragments(fragments);
        equityAdapter.setTitles(titles);

        fragmentEquityVp.setAdapter(equityAdapter);
        fragmentEquityTl.setupWithViewPager(fragmentEquityVp);
//        fragmentEquityTl.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    @Override
    protected void initDatas() {


    }
}
