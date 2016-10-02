package com.example.dllo.a36kr.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.NewsActivityPopBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/30.
 */
public class NewsActivityPopAdapter extends BaseAdapter {
    private Context context;
    private List<NewsActivityPopBean.DataBean.LatestArticleBean> datas;

    public NewsActivityPopAdapter(Context context) {
        this.context = context;
    }


    public void setDatas(List<NewsActivityPopBean.DataBean.LatestArticleBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
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
        NewsActivityPopHolder holder =null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.news_pop_listview,parent,false);
            holder = new NewsActivityPopHolder(convertView);

            convertView.setTag(holder);
        }else {
            holder = (NewsActivityPopHolder) convertView.getTag();
        }
        NewsActivityPopBean.DataBean.LatestArticleBean bean = datas.get(position);

        if (bean != null){
            holder.mPopTv.setText(bean.getSummary());
            Picasso.with(context).load(bean.getFeatureImg()).into(holder.mPopImg);
        }
        return convertView;
    }


    private class NewsActivityPopHolder{
        ImageView mPopImg;
        TextView mPopTv;
        public NewsActivityPopHolder(View view){
            mPopImg = (ImageView) view.findViewById(R.id.news_item_pop_img);
            mPopTv = (TextView) view.findViewById(R.id.news_item_pop_tv);
        }

    }
}
