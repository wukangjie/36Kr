package com.example.dllo.a36kr.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.NewFragmentBean;
import com.example.dllo.a36kr.utils.ScreenSizeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/13.
 */
public class NewsFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<NewFragmentBean.DataBean.DataBean1> datas;


    public void setDatas(List<NewFragmentBean.DataBean.DataBean1> datas) {
        this.datas =datas;
        notifyDataSetChanged();
    }

    public NewsFragmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 :datas.size();
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
        NewsFragmentHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item_listview,parent,false);
            ScreenSizeUtils.getSreenSize(context, ScreenSizeUtils.ScreenState.HEIGHT);
            holder = new NewsFragmentHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (NewsFragmentHolder) convertView.getTag();
        }
        NewFragmentBean.DataBean.DataBean1 bean = datas.get(position);
        if (bean != null){
            holder.newsItemContentTv.setText(bean.getTitle());
            holder.newsItemAuthorTv.setText(bean.getUser().getName());
            holder.newsItemTypeTv.setText(bean.getColumnName());

            holder.newsItemTimeTv.setText( bean.getPublishTime()+"");
            Picasso.with(context).load(bean.getFeatureImg()).into(holder.newsItemImg);

        }
        return convertView;
    }
    private class NewsFragmentHolder{
        ImageView newsItemImg ;
        TextView newsItemAuthorTv;
        TextView newsItemContentTv;
        TextView newsItemTimeTv;
        TextView newsItemTypeTv;
        public NewsFragmentHolder(View view){
            newsItemImg = (ImageView) view.findViewById(R.id.news_item_list_img);
            newsItemAuthorTv = (TextView) view.findViewById(R.id.news_item_author_tv);
            newsItemContentTv = (TextView) view.findViewById(R.id.news_item_content_tv);
            newsItemTimeTv = (TextView) view.findViewById(R.id.news_item_time_tv);
            newsItemTypeTv = (TextView) view.findViewById(R.id.news_item_type_tv);
        }
    }
}
