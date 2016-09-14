package com.example.dllo.a36kr.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.a36kr.R;

/**
 * Created by dllo on 16/9/10.
 */
public class EquityUseFragment extends Fragment {
    private TextView contentTv;
    public static EquityUseFragment newInstance(String str){
        Bundle args = new Bundle();
        args.putString("url",str);
        EquityUseFragment equityUseFragment = new EquityUseFragment();
        equityUseFragment.setArguments(args);
        return equityUseFragment;

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_equity_use,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentTv = (TextView) view.findViewById(R.id.fragment_equity_content_tv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //取值
        contentTv.setText(getArguments().getString("url"));
    }
}
