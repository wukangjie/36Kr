package com.example.dllo.a36kr.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.EquityFragmentBean;
import com.example.dllo.a36kr.model.bean.NewFragmentBean;
import com.example.dllo.a36kr.ui.adapter.EquityFragmentAdapter;
import com.example.dllo.a36kr.ui.fragment.guquantouzi.AllFragment;
import com.example.dllo.a36kr.ui.fragment.guquantouzi.FinishFragment;
import com.example.dllo.a36kr.ui.fragment.guquantouzi.InFragment;
import com.example.dllo.a36kr.ui.fragment.guquantouzi.OkFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 股权投资
 */
public class EquityFragment extends AbsFragment {
    private TabLayout fragmentEquityTablayout;
    private ViewPager fragmentEquityViewpager;
    private List<Fragment> fragments;
    private List<String> titles;

    @Override
    protected int setLayout() {
        return R.layout.fragment_equity;
    }

    @Override
    protected void initViews() {
        fragmentEquityTablayout = byView(R.id.fragment_equity_tl);
        fragmentEquityViewpager = byView(R.id.fragment_equity_vp);
    }

    @Override
    protected void initDatas() {
        fragments = new ArrayList<>();
        fragments.add(AllFragment.newInstance());
        fragments.add(InFragment.newInstance());
        fragments.add(FinishFragment.newInstance());
        fragments.add(OkFragment.newInstance());

        titles = new ArrayList<>();
        titles.add("全部");
        titles.add("募资中");
        titles.add("募资完成");
        titles.add("融资成功");

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

    }
}
