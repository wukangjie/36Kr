package com.example.dllo.a36kr.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.CarouselBean;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.ui.activity.DiscoverCreateActivity;
import com.example.dllo.a36kr.ui.activity.DiscoverInstituteActivity;
import com.example.dllo.a36kr.ui.activity.DiscoverInvestorActivity;
import com.example.dllo.a36kr.ui.activity.DiscoverRecentActivity;
import com.example.dllo.a36kr.ui.activity.ItoContralActivity;
import com.example.dllo.a36kr.ui.activity.UnLoginActivity;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.example.dllo.a36kr.view.LoopView;
import com.example.dllo.a36kr.view.LoopViewEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/9/9.
 * 发现Fragment
 */
public class DiscoverFragment extends AbsFragment implements View.OnClickListener {
    private LoopView loopView;//无限自动轮转控件
    private List<LoopViewEntity> entities = new ArrayList<>();
    private CarouselBean myBean;
    private LinearLayout mCreate;//创业公司
    private LinearLayout mRecent;//近期活动
    private RelativeLayout mInvest;//寻找投资人
    private LinearLayout mInsitute36;//36氪研究院


    @Override
    protected int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initViews() {
        loopView = byView(R.id.discovery_loopview);
        mCreate = byView(R.id.fragment_discover_create_linearlayout);
        mRecent = byView(R.id.fragment_discover_recent_linearlayout);
        mInvest = byView(R.id.fragment_discover_investor_relativelayout);
        mInsitute36 = byView(R.id.fragment_discover_36investor_Linearlayout);

    }

    @Override
    protected void initDatas() {
        /**
         * 网络请求获取轮播图
         */
        VolleyInstance.getInstance().startRequest(AllContantValues.ALLNEWSROTATEURL, new VolleyReault() {
            @Override
            public void success(String resultStr) {
                //解析
                //Gson 谷歌的 添加依赖
                //FastJson 阿里巴巴的
                /**
                 * 解析框架
                 * 给他一个实体类,就给你解析出数据
                 * 1.实体类属性名-要和接口中的属性名必须一致
                 */
                Gson gson = new Gson();
                //解析数据到实体类
                //gson从json数据解析
                //参数一:哪个json数据
                //参数二:解析到那个实体类
                myBean = gson.fromJson(resultStr, CarouselBean.class);
                //获取解析数据的集合
                List<CarouselBean.DataBean.PicsBean> datas = myBean.getData().getPics();
                if (entities.isEmpty()) {

                    for (int i = 0; i < datas.size(); i++) {
                        LoopViewEntity e = new LoopViewEntity();
                        e.setImageUrl(datas.get(i).getImgUrl());
                        entities.add(e);
                    }
                }
                loopView.setLoopData(entities);
            }

            @Override
            public void failure() {

            }
        });

        mCreate.setOnClickListener(this);
        mRecent.setOnClickListener(this);
        mInvest.setOnClickListener(this);
        mInsitute36.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_discover_create_linearlayout:
                startActivity(new Intent(getActivity(), DiscoverCreateActivity.class));
                break;
            case R.id.fragment_discover_recent_linearlayout:
                startActivity(new Intent(getActivity(), DiscoverRecentActivity.class));
                break;
            case R.id.fragment_discover_investor_relativelayout:
                startActivity(new Intent(getActivity(), DiscoverInvestorActivity.class));
                break;
            case R.id.fragment_discover_36investor_Linearlayout:
                startActivity(new Intent(getActivity(), DiscoverInstituteActivity.class));
                break;
        }
    }
}
