package com.example.dllo.a36kr.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.dllo.a36kr.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class EquityFragment extends AbsFragment {
    private TabLayout fragmentEquityTl;
    private List<Fragment> fragments;
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

    }

    @Override
    protected void initDatas() {
        fragments.add(new EquityAllFragment());
        fragments.add(new EquityAllFragment());
        fragments.add(new EquityAllFragment());
        fragments.add(new EquityAllFragment());
        fragmentEquityVp.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        fragmentEquityTl.setupWithViewPager(fragmentEquityVp);
        fragmentEquityTl.getTabAt(0).setText("全部");
        fragmentEquityTl.getTabAt(1).setText("融资中");
        fragmentEquityTl.getTabAt(2).setText("融资完成");
        fragmentEquityTl.getTabAt(3).setText("融资成功");
    }
}
