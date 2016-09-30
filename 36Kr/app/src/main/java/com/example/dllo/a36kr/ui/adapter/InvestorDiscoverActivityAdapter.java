package com.example.dllo.a36kr.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.InvestorDiscoverActivityBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/27.
 * 寻找投资人详情适配器
 */
public class InvestorDiscoverActivityAdapter extends BaseAdapter {

    private Context context;
    private List<InvestorDiscoverActivityBean.DataBean.DataBean1> datas ;
    private List<String> focusIndustry,investPhases;

    public InvestorDiscoverActivityAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<InvestorDiscoverActivityBean.DataBean.DataBean1> datas) {
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
        InvestorDiscoverActivityHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_discover_investor_item_listview,parent,false);
            holder = new InvestorDiscoverActivityHolder(convertView);

            convertView.setTag(holder);
        }else {
            holder = (InvestorDiscoverActivityHolder) convertView.getTag();
        }
        InvestorDiscoverActivityBean.DataBean.DataBean1 bean = datas.get(position);
        if (bean != null){
            holder.mAuthorName.setText(bean.getUser().getName());

            if (bean.getFocusIndustry() != null){
            for (int i = 0; i < bean.getFocusIndustry().size(); i++) {
                focusIndustry = bean.getFocusIndustry();

            }
            holder.mFocusTv.setText(focusIndustry + "");
            }
            if (bean.getInvestPhases() != null){
                for (int i = 0; i < bean.getInvestPhases().size(); i++) {
                   investPhases = bean.getInvestPhases();

                }
                holder.mStageTv.setText(investPhases + "");
            }
            Picasso.with(context).load(bean.getUser().getAvatar()).into(holder.mTitleImg);
        }

        return convertView;
    }
    private class InvestorDiscoverActivityHolder {
        CircleImageView mTitleImg;
        TextView mAuthorName;
        TextView mFocusTv;
        TextView mStageTv;
        public InvestorDiscoverActivityHolder(View view){
            mTitleImg = (CircleImageView) view.findViewById(R.id.activity_discover_investor_item_title_img);
            mAuthorName = (TextView) view.findViewById(R.id.activity_discover_investor_item_author_name);
            mFocusTv = (TextView) view.findViewById(R.id.activity_discover_investor_item_focus_tv);
            mStageTv = (TextView) view.findViewById(R.id.activity_discover_investor_item_stage_tv);
        }

    }
}
