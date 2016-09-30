package com.example.dllo.a36kr.ui.fragment;

import android.os.Bundle;
import android.os.Handler;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.EquityFragmentBean;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.ui.adapter.EquityFragmentAdapter;
import com.example.dllo.a36kr.view.RefreshLayout;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * EquityFragment 的复用Fragment
 */
public class EquityUseFragment extends AbsFragment {
    private ListView listView;
    private RefreshLayout swipeRefreshLayout;
    private EquityFragmentAdapter adapter;
    private List<EquityFragmentBean.DataBean.DataBean1> datas;
    private List<EquityFragmentBean.DataBean.DataBean1> datas1;
    private String string;//网址
    private String strLoad;//拼接后的网址
    private int page = 2;//网址页数

    public static EquityUseFragment newInstance(String str) {
        Bundle args = new Bundle();
        args.putString("url", str);
        EquityUseFragment equityUseFragment = new EquityUseFragment();
        equityUseFragment.setArguments(args);
        return equityUseFragment;

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_equity_use;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.fragment_equity_use_listview);
        adapter = new EquityFragmentAdapter(getContext());
        listView.setAdapter(adapter);
        swipeRefreshLayout = byView(R.id.fragment_equity_use_root_view);

    }

    @Override
    protected void initDatas() {


        Bundle bundle = getArguments();
        string = bundle.getString("url");
        strLoad = string.replace("1", page + "");
        /**
         * 设置下拉刷新
         */
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        VolleyInstance.getInstance().startRequest(string, new VolleyReault() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                EquityFragmentBean equityFragmentBean = gson.fromJson(resultStr, EquityFragmentBean.class);
                                datas = equityFragmentBean.getData().getData();
                                adapter.setDatas(datas);
                            }

                            @Override
                            public void failure() {

                            }
                        });
                        //停止刷新动画
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        swipeRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        VolleyInstance.getInstance().startRequest(strLoad, new VolleyReault() {


                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                EquityFragmentBean equityFragmentBean = gson.fromJson(resultStr, EquityFragmentBean.class);
                                datas1 = equityFragmentBean.getData().getData();
                                datas.addAll(datas1);
                                adapter.setDatas(datas);
                                strLoad.replace(page + "", page + 1 + "");
                                swipeRefreshLayout.setLoading(false);
                            }

                            @Override
                            public void failure() {

                            }
                        });
                        //停止刷新动画
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        VolleyInstance.getInstance().startRequest(string, new VolleyReault() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                //解析数据到实体类
                //gson从json数据解析
                //参数一:哪个json数据
                //参数二:解析到那个实体类
                EquityFragmentBean myBean = gson.fromJson(resultStr, EquityFragmentBean.class);
                //获取解析数据的集合
                datas = myBean.getData().getData();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });

    }
}
