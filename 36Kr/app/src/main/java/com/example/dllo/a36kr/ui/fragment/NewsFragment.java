package com.example.dllo.a36kr.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.activity.ItoContralActivity;
import com.example.dllo.a36kr.ui.activity.NewsSearchActivity;
import com.example.dllo.a36kr.utils.AllContantValues;


/**
 * Created by dllo on 16/9/9.
 * 新闻Fragment
 */
public class NewsFragment extends AbsFragment implements View.OnClickListener {
    private ImageView menuIv;
    private TextView titleTv;
    private ItoContralActivity itoContralActivity;
    private ImageView searchImg;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itoContralActivity = (ItoContralActivity) context;


    }

    /**
     * 单例
     * @return
     */
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
        searchImg = byView(R.id.fragment_news_search_img);
    }


    @Override
    protected void initDatas() {
        /**
         * 默认显示新闻的全部界面
         */
        getChildFragmentManager().beginTransaction()
                .replace(R.id.news_fragment_replace_view, NewsUseFragment.newInstance(AllContantValues.ALLNEWSURL, true))
                .commit();
        /**
         * 设置菜单的点击事件
         */
        menuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itoContralActivity.toContralActivity(true);//接口回调传值
            }
        });
        searchImg.setOnClickListener(this);



    }

    /**
     * 自定义方法
     * 在MainActivity调用
     * 通过改变网址
     * 替换Fragment
     * @param fragment
     */
    public void changeFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.news_fragment_replace_view, fragment)
                .commit();
    }

    /**
     * 点击改变NewsFragment的标题
     * @param text
     */
    public void changeTextFragment(String text) {
        titleTv.setText(text);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_news_search_img:
                startActivity(new Intent(getActivity(), NewsSearchActivity.class));
                break;
        }
    }
}
