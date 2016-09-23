package com.example.dllo.a36kr.ui.fragment;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.CarouselBean;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.example.dllo.a36kr.view.LoopView;
import com.example.dllo.a36kr.view.LoopViewEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class DiscoverFragment extends AbsFragment {
    private LoopView loopView;
    private RequestQueue queue;
    private List<LoopViewEntity> entities = new ArrayList<>();
    private CarouselBean myBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initViews() {
        loopView = byView(R.id.discovery_loopview);

    }

    @Override
    protected void initDatas() {
        queue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(AllContantValues.ALLNEWSROTATEURL, new Response.Listener<String>() {
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
                myBean = gson.fromJson(response, CarouselBean.class);
                //获取解析数据的集合
                List<CarouselBean.DataBean.PicsBean> datas = myBean.getData().getPics();

                for (int i = 0; i < datas.size(); i++) {
                    LoopViewEntity e = new LoopViewEntity();
                    Log.d("DiscoveryFragment", "e:" + e);

                    e.setImageUrl(datas.get(i).getImgUrl());
                    Log.d("DiscoveryFragment", "e1:" + e);
                    entities.add(e);
                }
                loopView.setLoopData(entities);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        //加入请求队列
        queue.add(sr);

    }
}
