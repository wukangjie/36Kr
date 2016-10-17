package com.example.dllo.a36kr.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_news_search_pop_item_listview,parent,false);
            ScreenSizeUtils.getSreenSize(context, ScreenSizeUtils.ScreenState.HEIGHT);
            holder = new NewsSearchHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (NewsSearchHolder) convertView.getTag();
        }
        NewsSearchBean.DataBean.DataBean1 bean = datas.get(position);
        if (bean != null ){

            holder.contentTv.setText(bean.getTitle());
            holder.nameTv.setText(bean.getUser().getName());
            holder.typeTv.setText(bean.getColumnName());
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日");//将时间转换为正常显示
            String publishTime =format.format(new Date(bean.getPublishTime()));
            holder.timeTv.setText( publishTime);
            Picasso.with(context).load(bean.getFeatureImg()).into(holder.titleImg);//毕加索加载图片


        }

        return convertView;
    }

    private class NewsSearchHolder{
        ImageView titleImg;
        TextView contentTv;
        TextView nameTv;
        TextView timeTv;
        TextView typeTv;

        public NewsSearchHolder(View view){
            titleImg = (ImageView) view.findViewById(R.id.activity_news_search_pop_title_img);
            contentTv = (TextView) view.findViewById(R.id.activity_news_search_pop_title_tv);
            nameTv = (TextView) view.findViewById(R.id.activity_news_search_pop_author_tv);
            timeTv = (TextView) view.findViewById(R.id.activity_news_search_pop_time_tv);
            typeTv = (TextView) view.findViewById(R.id.activity_news_search_pop_type_tv);

        }
    }
}
