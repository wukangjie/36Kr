package com.example.dllo.a36kr.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.NewFragmentBean;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.ui.adapter.NewsFragmentAdapter;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.example.dllo.a36kr.view.RefreshLayout;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.List;

public class DiscoverInstituteActivity extends AbsBaseActivity {
    private RefreshLayout swipeRefreshLayout;
    private ListView listView;
    private NewsFragmentAdapter newsFragmentAdapter;
    private String string;//网址
    private String strLoad;//拼接后的网址
    private int pageSize = 40;//网址的条数
    private List<NewFragmentBean.DataBean.DataBean1> datas1;
    private List<NewFragmentBean.DataBean.DataBean1> datas;



    @Override
    protected int setLayout() {
        return R.layout.activity_discover_institute;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.activity_discover_institute_listview);
        swipeRefreshLayout = byView(R.id.activity_discover_institute_rootview);

    }

    @Override
    protected void initDatas() {
        string = AllContantValues.RESEARCHNEWSURL;
        strLoad = string.replace("20",pageSize+"");
        newsFragmentAdapter = new NewsFragmentAdapter(getBaseContext());
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
                Intent intent = new Intent(getApplicationContext(), NewsFragmentActivity.class  );
                SimpleDateFormat format = new SimpleDateFormat("MM-dd-HH:mm");//将时间转换为正常显示
                String publishTime = format.format(datas.get(position - 1).getPublishTime());
                intent.putExtra("publishTime", publishTime + "");
                intent.putExtra("postId", datas.get(position - 1).getFeedId());
                startActivity(intent);
            }
        });


    }

}
