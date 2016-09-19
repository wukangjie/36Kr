package com.example.dllo.fragmentusedemo.drawer_change_ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.fragmentusedemo.R;

/**
 * A simple {@link Fragment} subclass.
 * 新闻界面复用的Fragment
 */
public class NewsAllFragment extends Fragment {

    private TextView textView;

    public static NewsAllFragment newInstance(String text) {

        Bundle args = new Bundle();
        //存储到Bundle
        args.putString("text",text);
        NewsAllFragment fragment = new NewsAllFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_all, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.news_all_tv);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //从bundle里取出数据
        Bundle bundle = getArguments();
        String str = bundle.getString("text");
        textView.setText(str);
    }
}
