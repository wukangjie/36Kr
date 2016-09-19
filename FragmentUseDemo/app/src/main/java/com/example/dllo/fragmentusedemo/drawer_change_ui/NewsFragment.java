package com.example.dllo.fragmentusedemo.drawer_change_ui;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.fragmentusedemo.R;

/**
 * A simple {@link Fragment} subclass.
 * 新闻Fragment
 */
public class NewsFragment extends Fragment {
    private MyReceiver myReceiver;

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //让新闻Fragment显示默认页
        getChildFragmentManager().beginTransaction()
                .replace(R.id.news_replace_view,NewsAllFragment.newInstance("默认页"))
                .commit();
//        //注册广播
//        //1.定义并实例化广播接收者
//        myReceiver = new MyReceiver();
//        //2.定义意图过滤器
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("change");
//        //3.注册广播(通过Context)
//        getContext().registerReceiver(myReceiver,intentFilter);
//        //4.在onDestroy里取消注册
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        getContext().unregisterReceiver(myReceiver);
    }

    /**
     * 对外提供参数布局的方法
     */
    public void changeFragment(Fragment fragment){
        getChildFragmentManager().beginTransaction().replace(R.id.news_replace_view,fragment).commit();
    }



    //定义广播接收者
    //接收到广播替换界面
    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null){
                int a = intent.getIntExtra("index",0);
                switch (a){
                    case 0:
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.news_replace_view,NewsAllFragment.newInstance("默认页"));
                        break;
                    case 1:
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.news_replace_view,NewsAllFragment.newInstance("B轮后"));
                        break;
                    case 2:
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.news_replace_view,NewsAllFragment.newInstance("早期项目"));
                        break;
                }
            }

        }
    }
}
