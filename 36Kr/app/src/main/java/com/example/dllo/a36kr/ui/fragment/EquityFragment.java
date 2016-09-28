package com.example.dllo.a36kr.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.utils.AllContantValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 股权投资Fragment
 */
public class EquityFragment extends AbsFragment implements View.OnClickListener {
    private TabLayout fragmentEquityTablayout;
    private ViewPager fragmentEquityViewpager;
    private List<Fragment> fragments;
    private List<String> titles;
    private ImageView mGiftImg;


    @Override
    protected int setLayout() {
        return R.layout.fragment_equity;
    }

    @Override
    protected void initViews() {
        fragmentEquityTablayout = byView(R.id.fragment_equity_tl);
        fragmentEquityViewpager = byView(R.id.fragment_equity_vp);
        mGiftImg = byView(R.id.fragment_equity_title_gift);


    }

    @Override
    protected void initDatas() {
        fragments = new ArrayList<>();
        fragments.add(EquityUseFragment.newInstance(AllContantValues.ALLEQUITY));
        fragments.add(EquityUseFragment.newInstance(AllContantValues.RAISEEQUITY));
        fragments.add(EquityUseFragment.newInstance(AllContantValues.SUCCESSEQUITY));
        fragments.add(EquityUseFragment.newInstance(AllContantValues.COMPLETEEQUITY));

        titles = new ArrayList<>();
        titles.add("全部");
        titles.add("募资中");
        titles.add("募资完成");
        titles.add("融资成功");

        /**
         * ViewPager绑定适配器
         */
        fragmentEquityViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });

        fragmentEquityTablayout.setupWithViewPager(fragmentEquityViewpager);
        mGiftImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_equity_title_gift:

                break;
        }
    }
}
