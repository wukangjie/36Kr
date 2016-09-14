package com.example.dllo.a36kr.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.NewFragmentBean;
import com.example.dllo.a36kr.ui.activity.NewsFragmentActivity;
import com.example.dllo.a36kr.ui.adapter.NewsFragmentAdapter;
import com.google.gson.Gson;

import java.lang.ref.ReferenceQueue;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class NewsFragment extends AbsFragment {
    private String url = "https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up";
    private ImageView naviImg;
    private DrawerLayout fragmentDl;
    private LinearLayout fragmentLl;
    private ListView listView;
    private RequestQueue queue;
    private NewsFragmentAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        naviImg = byView(R.id.fragment_news_navi_img);
        fragmentDl = byView(R.id.fragment_news_drawer_layout);
        fragmentLl = byView(R.id.fragment_news_drawer_ll);
        listView = byView(R.id.fragment_news_listview);
        adapter = new NewsFragmentAdapter(getContext());
        listView.setAdapter(adapter);


        queue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewFragmentBean newFragmentBean = gson.fromJson(response,NewFragmentBean.class);
                List<NewFragmentBean.DataBean.DataBean1> dataBeen = newFragmentBean.getData().getData();
                adapter.setDatas(dataBeen);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(sr);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewFragmentBean.DataBean.DataBean1 dataBean = (NewFragmentBean.DataBean.DataBean1) parent.getItemAtPosition(position);
                String title = dataBean.getTitle();
                String columnName = dataBean.getColumnName();
                long publishTime = dataBean.getPublishTime();
                String authorName = dataBean.getUser().getName();
                String imgSrc = dataBean.getFeatureImg();
                Intent intent = new Intent(getActivity(), NewsFragmentActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("publishTime",publishTime);
                intent.putExtra("authorName",authorName);
                intent.putExtra("imgSrc",imgSrc);
                startActivity(intent);
            }
        });
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
