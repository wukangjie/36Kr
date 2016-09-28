package com.example.dllo.a36kr.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.EquityFragmentBean;
import com.example.dllo.a36kr.ui.fragment.EquityFragment;
import com.example.dllo.a36kr.utils.ScreenSizeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/8.
 * 股权投资适配器
 */
public class EquityFragmentAdapter extends BaseAdapter {

    private List<EquityFragmentBean.DataBean.DataBean1> datas;
    private Context context;

    public EquityFragmentAdapter(Context context) {
        this.context = context;
    }



    public void setDatas(List<EquityFragmentBean.DataBean.DataBean1> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return datas == null ?  0: datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? 0: datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EquityFragmentHolder holder = null;
        int width = ScreenSizeUtils.getSreenSize(context, ScreenSizeUtils.ScreenState.WIDTH);
        int height = ScreenSizeUtils.getSreenSize(context, ScreenSizeUtils.ScreenState.HEIGHT);

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.equity_item_listview,parent,false);
            holder = new EquityFragmentHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (EquityFragmentHolder) convertView.getTag();
        }
        EquityFragmentBean.DataBean.DataBean1 bean = datas.get(position);
        if (bean != null){
            holder.titleName.setText(bean.getCompany_name());
            holder.titleAuthor.setText(bean.getCompany_brief());
            holder.ledName.setText(bean.getLead_name());
            holder.founderName.setText(bean.getCf_advantage().get(0).getAdcontent());
            holder.hatchName.setText(bean.getCf_advantage().get(1).getAdcontent());
            holder.raiseTv.setText(bean.getFundStatus().getDesc());
            holder.progressTv.setText("已融资"+(int)(bean.getRate() * 100)+"%");
            holder.progressBar.setProgress((int) (bean.getRate()*100));



            Picasso.with(context).load(bean.getCompany_logo()).resize(width/6,height/10).into(holder.titleImg);
            Picasso.with(context).load(bean.getFile_list_img()).into(holder.contentImg);
        }
        return convertView;
    }




    private class EquityFragmentHolder{
        CircleImageView titleImg;
        TextView titleName;
        TextView titleAuthor;
        ImageView contentImg;
        TextView ledName;
        TextView founderName;
        TextView hatchName;
        TextView raiseTv;
        TextView progressTv;
        Button subscribeBtn;
         ProgressBar progressBar;

        public EquityFragmentHolder(View view){
            titleImg = (CircleImageView) view.findViewById(R.id.equity_item_title_img);
            titleName = (TextView) view.findViewById(R.id.equity_item_title_name);
            titleAuthor = (TextView) view.findViewById(R.id.equity_item_title_author);
            contentImg = (ImageView) view.findViewById(R.id.equity_item_content_img);
            ledName = (TextView) view.findViewById(R.id.equity_item_led_tv);
            founderName = (TextView) view.findViewById(R.id.equity_item_founder_tv);
            hatchName = (TextView) view.findViewById(R.id.equity_item_hatch_tv);
            raiseTv = (TextView) view.findViewById(R.id.equity_item_raise_tv);
            progressTv = (TextView) view.findViewById(R.id.equity_item_progress_tv);
            subscribeBtn = (Button) view.findViewById(R.id.equity_item_subscribe_btn);
            progressBar = (ProgressBar) view.findViewById(R.id.equity_item_progressbar);

        }
    }
}
