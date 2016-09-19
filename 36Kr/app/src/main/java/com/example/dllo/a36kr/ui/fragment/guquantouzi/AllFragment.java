package com.example.dllo.a36kr.ui.fragment.guquantouzi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.EquityFragmentBean;
import com.example.dllo.a36kr.ui.adapter.EquityFragmentAdapter;
import com.example.dllo.a36kr.ui.adapter.NewsFragmentAdapter;
import com.example.dllo.a36kr.ui.fragment.AbsFragment;
import com.example.dllo.a36kr.ui.fragment.EquityFragment;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/9/18.
 * 全部
 */
public class AllFragment extends AbsFragment{
    private String url = "https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=all&pageSize=20";
    private ListView listView;
    private RequestQueue queue;
    private EquityFragmentAdapter adapter;

    public static AllFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int setLayout() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.fragment_all_listview);
        adapter = new EquityFragmentAdapter(getContext());
        listView.setAdapter(adapter);
        // 请求 股权投资-全部 界面数据 设置给ListView
        queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                EquityFragmentBean equityFragmentBean = gson.fromJson(response,EquityFragmentBean.class);
                List<EquityFragmentBean.DataBean.DataBean1> dataBean1s = equityFragmentBean.getData().getData();
                adapter.setDatas(dataBean1s);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }


    @Override
    protected void initDatas() {

    }
}

