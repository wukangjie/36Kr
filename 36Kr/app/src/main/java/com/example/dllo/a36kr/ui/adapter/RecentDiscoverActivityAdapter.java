package com.example.dllo.a36kr.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.RecentDiscoverActivityBean;
import com.example.dllo.a36kr.utils.ScreenSizeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class RecentDiscoverActivityAdapter extends BaseAdapter {
    private Context context;
    private List<RecentDiscoverActivityBean.DataBean.DataBean1> datas;

    public RecentDiscoverActivityAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<RecentDiscoverActivityBean.DataBean.DataBean1> datas) {
        this.datas = datas;
        notifyDataSetChanged();
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
       RecentDiscoverActivityHolder  holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_discover_recent_item_listview,parent,false);
            ScreenSizeUtils.getSreenSize(context, ScreenSizeUtils.ScreenState.HEIGHT);
            holder = new RecentDiscoverActivityHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (RecentDiscoverActivityHolder) convertView.getTag();
        }
        RecentDiscoverActivityBean.DataBean.DataBean1 bean = datas.get(position);
        if (bean != null){
            holder.mTitleTv.setText(bean.getActivityName());
            holder.mContentTv.setText(bean.getActivityDesc());
            holder.mApplyTv.setText(bean.getActivityStatus());
            holder.mPlaceTv.setText(bean.getActivityCity());
            holder.mTimeTv.setText(bean.getActivityTime());
            Picasso.with(context).load(bean.getActivityImg()).into(holder.mTitleImg);//毕加索加载图片

        }

        return convertView;
    }
    private class RecentDiscoverActivityHolder{
        ImageView mTitleImg;
        TextView mTitleTv;
        TextView mContentTv;
        TextView mApplyTv;
        TextView mPlaceTv;
        TextView mTimeTv;
        public RecentDiscoverActivityHolder(View view){
            mTitleImg = (ImageView) view.findViewById(R.id.activity_discover_item_title_img);
            mTitleTv = (TextView) view.findViewById(R.id.activity_discover_item_title_tv);
            mContentTv = (TextView) view.findViewById(R.id.activity_discover_item_content_tv);
            mApplyTv = (TextView) view.findViewById(R.id.activity_discover_item_apply_tv);
            mPlaceTv = (TextView) view.findViewById(R.id.activity_discover_item_place_tv);
            mTimeTv = (TextView) view.findViewById(R.id.activity_discover_item_time_tv);

        }
    }
}
