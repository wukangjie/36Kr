package com.example.dllo.a36kr.ui.fragment.guquantouzi;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.fragment.AbsFragment;

/**
 * Created by dllo on 16/9/18.
 * 募资中
 */
public class InFragment extends AbsFragment{
    public static InFragment newInstance() {
        
        Bundle args = new Bundle();
        
        InFragment fragment = new InFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
