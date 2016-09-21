package com.example.dllo.a36kr.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.NewFragmentBean;
import com.example.dllo.a36kr.ui.activity.ItoContralActivity;
import com.example.dllo.a36kr.ui.adapter.NewsFragmentAdapter;
import com.example.dllo.a36kr.view.ExpandListView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class NewsUseFragment extends AbsFragment implements View.OnClickListener {
    private ImageView menuIv;
    private TextView titleTv;
    private ItoContralActivity itoContralActivity;
    private ListView listView;
    private NewsFragmentAdapter newsFragmentAdapter;
    private RequestQueue queue;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itoContralActivity = (ItoContralActivity) context;


    }

    public static NewsUseFragment newInstance(String str, String text) {
        Bundle args = new Bundle();
        args.putString("text", text);
        args.putString("url", str);
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
        menuIv = byView(R.id.fragment_news_navi_img);
        titleTv = byView(R.id.fragment_news_title_tv);
    }

    @Override
    protected void initDatas() {
        Bundle bundle = getArguments();
        String string = bundle.getString("url");
        String text = bundle.getString("text");
        Log.d("zzz", string);
        titleTv.setText(text);
        newsFragmentAdapter = new NewsFragmentAdapter(getContext());
        listView.setAdapter(newsFragmentAdapter);

        queue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(string, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewFragmentBean newFragmentBean = gson.fromJson(response, NewFragmentBean.class);
                List<NewFragmentBean.DataBean.DataBean1> datas = newFragmentBean.getData().getData();
                newsFragmentAdapter.setDatas(datas);
                Log.d("zzz", datas.get(0).getTitle() + "---" + datas.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(sr);
        listView.setAdapter(newsFragmentAdapter);
        menuIv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_news_navi_img:
                itoContralActivity.toContralActivity(true);

                break;
        }
    }
}
