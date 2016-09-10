package com.example.dllo.a36kr.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dllo.a36kr.R;

/**
 * Created by dllo on 16/9/9.
 */
public class NewsFragment extends AbsFragment {
    private ImageView naviImg;
    private DrawerLayout fragmentDl;
    private LinearLayout fragmentLl;

    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        naviImg = byView(R.id.fragment_navi_img);
        fragmentDl = byView(R.id.fragment_drawer_layout);
        fragmentLl = byView(R.id.fragment_drawer_ll);

    }

    @Override
    protected void initDatas() {
        naviImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentDl.openDrawer(fragmentLl);
            }
        });

    }
}
