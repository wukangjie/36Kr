package com.example.dllo.a36kr.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.EquityFragmentBean;
import com.example.dllo.a36kr.ui.adapter.EquityFragmentAdapter;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class EquityUseFragment extends AbsFragment {
    private ListView listView;
    private EquityFragmentAdapter adapter;
    private RequestQueue queue;



    public static EquityUseFragment newInstance(String str){
        Bundle args = new Bundle();
        args.putString("url",str);
        Log.d("vvv", str);
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

    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("url");
        Log.d("aaaa", string+"");
        queue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(string, new Response.Listener<String>() {
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
                EquityFragmentBean myBean = gson.fromJson(response,EquityFragmentBean.class);
                //获取解析数据的集合
                List<EquityFragmentBean.DataBean.DataBean1> datas = myBean.getData().getData();
                adapter.setDatas(datas);

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
