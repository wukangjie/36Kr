package com.example.dllo.a36kr.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/9/8.
 */
public class EquityAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] titles;

    public EquityAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments !=null && fragments.size() > 0?fragments.get(position):null;
    }

    @Override
    public int getCount() {


        return fragments !=null && fragments.size() > 0 ? fragments.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
