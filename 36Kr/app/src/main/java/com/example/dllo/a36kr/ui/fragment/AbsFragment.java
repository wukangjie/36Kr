package com.example.dllo.a36kr.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.a36kr.ui.activity.AbsBaseActivity;
import com.example.dllo.a36kr.utils.T;

/**
 * Created by dllo on 16/9/8.
 * Fragment的基类
 */
public abstract class AbsFragment extends Fragment {
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化组件
        initViews();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //使用数据
        initDatas();
    }
    protected abstract int setLayout();
    protected abstract void initViews();
    protected abstract void initDatas();
    //简化findViewById
    protected <T extends View>T byView(int resId){
        return (T) getView().findViewById(resId);
    }
    //简化跳转
    protected void goTo(Class<? extends AbsBaseActivity> to){
        context.startActivity(new Intent(context,to));
    }
    protected void goTo(Class<? extends AbsBaseActivity> to, Bundle extras){
        Intent intent = new Intent(context,to);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

}
