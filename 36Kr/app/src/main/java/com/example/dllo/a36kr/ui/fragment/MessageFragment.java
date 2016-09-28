package com.example.dllo.a36kr.ui.fragment;

import android.content.Intent;
import android.util.Log;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.IsLoginBean;
import com.example.dllo.a36kr.ui.activity.UnLoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dllo on 16/9/9.
 * 消息Fragment
 */
public class MessageFragment extends AbsFragment {
    @Override
    protected int setLayout() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(IsLoginBean bean){
        if (!bean.isData()){
            startActivity(new Intent(getActivity(), UnLoginActivity.class));
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
