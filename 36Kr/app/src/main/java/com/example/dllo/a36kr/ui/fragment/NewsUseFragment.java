package com.example.dllo.a36kr.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.CarouselBean;
import com.example.dllo.a36kr.model.bean.NewFragmentBean;
import com.example.dllo.a36kr.ui.adapter.NewsFragmentAdapter;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.example.dllo.a36kr.view.LoopView;
import com.example.dllo.a36kr.view.LoopViewEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class NewsUseFragment extends AbsFragment {


    private ListView listView;
    private NewsFragmentAdapter newsFragmentAdapter;
    private RequestQueue queue;
    private LoopView newsLoopView;
    private boolean ifHaveTitle = true;
    private List<LoopViewEntity> entities = new ArrayList<>();
    private View handerView;


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


    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("url");
        boolean ifHaveTitle = bundle.getBoolean("ifHaveTitle");
        newsFragmentAdapter = new NewsFragmentAdapter(getContext());

        queue = Volley.newRequestQueue(getContext());
        if (ifHaveTitle == true) {
            handerView = LayoutInflater.from(getContext()).inflate(R.layout.news_listhandview, null);
            newsLoopView = (LoopView) handerView.findViewById(R.id.news_loopview);
            listView.addHeaderView(handerView);
            StringRequest sr1 = new StringRequest(AllContantValues.ALLNEWSROTATEURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("xxx", response);
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
                    CarouselBean myBean = gson.fromJson(response, CarouselBean.class);
                    //获取解析数据的集合
                    List<CarouselBean.DataBean.PicsBean> datas1 = myBean.getData().getPics();

                    for (int i = 0; i < datas1.size(); i++) {
                        LoopViewEntity e = new LoopViewEntity();
                        Log.d("DiscoveryFragment", "e:" + e);

                        e.setImageUrl(datas1.get(i).getImgUrl());
                        Log.d("DiscoveryFragment", "e1:" + e);
                        entities.add(e);
                    }
                    newsLoopView.setLoopData(entities);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });
            queue.add(sr1);
        }


        StringRequest sr = new StringRequest(string, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewFragmentBean newFragmentBean = gson.fromJson(response, NewFragmentBean.class);
                List<NewFragmentBean.DataBean.DataBean1> datas = newFragmentBean.getData().getData();
                newsFragmentAdapter.setDatas(datas);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(sr);
        listView.setAdapter(newsFragmentAdapter);

    }


}
