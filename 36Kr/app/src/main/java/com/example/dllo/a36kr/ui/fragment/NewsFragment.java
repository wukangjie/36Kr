package com.example.dllo.a36kr.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.dllo.a36kr.R;


/**
 * Created by dllo on 16/9/9.
 */
public class NewsFragment extends AbsFragment {


    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {


    }


    @Override
    protected void initDatas() {

        getChildFragmentManager().beginTransaction()
                .replace(R.id.news_fragment_replace_view, NewsUseFragment.newInstance("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up", "新闻"))
                .commit();


    }

    public void changeFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.news_fragment_replace_view, fragment)
                .commit();
    }


}
