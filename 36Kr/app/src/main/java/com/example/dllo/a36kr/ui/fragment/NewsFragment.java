package com.example.dllo.a36kr.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.activity.ItoContralActivity;
import com.example.dllo.a36kr.utils.AllContantValues;


/**
 * Created by dllo on 16/9/9.
 */
public class NewsFragment extends AbsFragment {
    private ImageView menuIv;
    private TextView titleTv;
    private ItoContralActivity itoContralActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itoContralActivity = (ItoContralActivity) context;


    }


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
        menuIv = byView(R.id.fragment_news_navi_img);
        titleTv = byView(R.id.fragment_news_title_tv);
    }


    @Override
    protected void initDatas() {


        getChildFragmentManager().beginTransaction()
                .replace(R.id.news_fragment_replace_view, NewsUseFragment.newInstance(AllContantValues.ALLNEWSURL, true))
                .commit();
        menuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itoContralActivity.toContralActivity(true);
            }
        });


    }

    public void changeFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.news_fragment_replace_view, fragment)
                .commit();
    }

    public void changeTextFragment(String text) {
        titleTv.setText(text);
    }


}
