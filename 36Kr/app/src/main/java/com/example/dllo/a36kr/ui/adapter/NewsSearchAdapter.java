package com.example.dllo.a36kr.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.NewFragmentBean;
import com.example.dllo.a36kr.model.bean.NewsSearchBean;
import com.example.dllo.a36kr.utils.ScreenSizeUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 16/10/7.
 */
public class NewsSearchAdapter extends BaseAdapter {
    private Context context;
    private List<NewsSearchBean.DataBean.DataBean1> datas;

    public NewsSearchAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<NewsSearchBean.DataBean.DataBean1> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas ==null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsSearchHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_news_search_pop,parent,false);
            ScreenSizeUtils.getSreenSize(context, ScreenSizeUtils.ScreenState.HEIGHT);
            holder = new NewsSearchHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (NewsSearchHolder) convertView.getTag();
        }
        NewsSearchBean.DataBean.DataBean1 bean = datas.get(position);
        if (bean != null){


        }

        return convertView;
    }

    private class NewsSearchHolder{
        public NewsSearchHolder(View view){

        }
    }
}
