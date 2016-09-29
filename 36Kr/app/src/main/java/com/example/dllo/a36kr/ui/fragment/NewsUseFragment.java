package com.example.dllo.a36kr.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.CarouselBean;
import com.example.dllo.a36kr.model.bean.NewFragmentBean;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.ui.activity.NewsFragmentActivity;
import com.example.dllo.a36kr.ui.adapter.NewsFragmentAdapter;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.example.dllo.a36kr.view.LoopView;
import com.example.dllo.a36kr.view.LoopViewEntity;
import com.example.dllo.a36kr.view.RefreshLayout;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * NewsFragment 的复用Fragment
 */
public class NewsUseFragment extends AbsFragment  {
    private RefreshLayout swipeRefreshLayout;
    private ListView listView;
    private NewsFragmentAdapter newsFragmentAdapter;
    private LoopView newsLoopView;
    private List<LoopViewEntity> entities = new ArrayList<>();
    private View handerView;
    private String string;//网址
    private String strLoad;//拼接后的网址
    private int pageSize = 40;//网址的条数
    private List<NewFragmentBean.DataBean.DataBean1> datas1;
    private List<NewFragmentBean.DataBean.DataBean1> datas;


    /**
     * 单例
     *
     * @param str         NewsFragment 的网址
     * @param ifHaveTitle 是否含有轮播图
     * @return
     */
    public static NewsUseFragment newInstance(String str, boolean ifHaveTitle) {
        Bundle args = new Bundle();
        args.putString("url", str);
        args.putBoolean("ifHaveTitle", ifHaveTitle);
        NewsUseFragment fragment = new NewsUseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news_use;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.fragment_news_use_listview);
        swipeRefreshLayout = byView(R.id.fragment_news_use_swipe);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        string = bundle.getString("url");
        strLoad = string.replace("20",pageSize+"");

        boolean ifHaveTitle = bundle.getBoolean("ifHaveTitle");
        newsFragmentAdapter = new NewsFragmentAdapter(getContext());
        listView.setAdapter(newsFragmentAdapter);//加载适配器

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
                                NewFragmentBean newFragmentBean = gson.fromJson(resultStr, NewFragmentBean.class);
                                datas = newFragmentBean.getData().getData();
                                newsFragmentAdapter.setDatas(datas);
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
                                strLoad = strLoad.replace(pageSize+"",pageSize + 20 +"");

                                Gson gson = new Gson();
                                NewFragmentBean newFragmentBean = gson.fromJson(resultStr, NewFragmentBean.class);
                                datas1 = newFragmentBean.getData().getData();
                                datas.addAll(datas1);
//                                newsFragmentAdapter.setDatas(datas);
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

    /**
     * 解析轮播图的网络
     */
//        queue = Volley.newRequestQueue(getContext());//请求队列的初始化
        /**
         * 加这个判断是为了在没有轮播图的时候,不需要加载轮播图
         * 减少运行时间
         */
        if (ifHaveTitle == true) {
            handerView = LayoutInflater.from(getContext()).inflate(R.layout.news_listhandview, null);
            newsLoopView = (LoopView) handerView.findViewById(R.id.news_loopview);
            listView.addHeaderView(handerView);
            VolleyInstance.getInstance().startRequest(AllContantValues.ALLNEWSROTATEURL, new VolleyReault() {
                @Override
                public void success(String resultStr) {
                    Gson gson = new Gson();
                    //解析数据到实体类
                    //gson从json数据解析
                    //参数一:哪个json数据
                    //参数二:解析到那个实体类
                    CarouselBean myBean = gson.fromJson(resultStr, CarouselBean.class);
                    //获取解析数据的集合
                    List<CarouselBean.DataBean.PicsBean> datas1 = myBean.getData().getPics();
                    for (int i = 0; i < datas1.size(); i++) {
                        LoopViewEntity e = new LoopViewEntity();
                        e.setImageUrl(datas1.get(i).getImgUrl());
                        entities.add(e);
                    }
                    newsLoopView.setLoopData(entities);
                }

                @Override
                public void failure() {

                }
            });
        }
        /**
         * 解析NewsFragment的网络,如全部,早期项目....
         */
        VolleyInstance.getInstance().startRequest(string, new VolleyReault() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                NewFragmentBean newFragmentBean = gson.fromJson(resultStr, NewFragmentBean.class);
                datas = newFragmentBean.getData().getData();
                newsFragmentAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });

        /**
         * 设置listView(列表视图) 的监听事件
         * 点击跳转到 NewsFragment的详情页
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NewsFragmentActivity.class);
                SimpleDateFormat format = new SimpleDateFormat("MM-dd-HH:mm");//将时间转换为正常显示
                String publishTime = format.format(datas.get(position - 1).getPublishTime());
                intent.putExtra("publishTime", publishTime + "");
                intent.putExtra("postId", datas.get(position - 1).getFeedId());
                startActivity(intent);
            }
        });


    }


}
