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
import com.example.dllo.a36kr.model.bean.CollectBean;
import com.example.dllo.a36kr.model.db.LiteOrmInstance;
import com.example.dllo.a36kr.utils.ScreenSizeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public   class MineFavoriteAdapter extends BaseAdapter {
  private Context context;
  private List<CollectBean> datas;


  public void setDatas(List<CollectBean> datas) {
      this.datas = datas;
      notifyDataSetChanged();
  }

  public MineFavoriteAdapter(Context context) {
      this.context = context;
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
      datas = LiteOrmInstance.getInstance().queryAll();
      Log.d("aaa", "datas:" + datas);
      NewsFragmentHolder holder = null;
      if (convertView == null) {
          convertView = LayoutInflater.from(context).inflate(R.layout.mine_item_listview, parent, false);
          ScreenSizeUtils.getSreenSize(context, ScreenSizeUtils.ScreenState.HEIGHT);
          holder = new NewsFragmentHolder(convertView);
          convertView.setTag(holder);
      } else {
          holder = (NewsFragmentHolder) convertView.getTag();
      }
      CollectBean bean = datas.get(position);
      if (bean != null) {
          holder.newsItemContentTv.setText(bean.getContentTv());
          Log.d("qqq", bean.getContentTv());

          holder.newsItemAuthorTv.setText(bean.getNameTv());
          holder.newsItemTypeTv.setText(bean.getTypeTv());
          /**
           * 判断不同类型项目,显示不同的颜色
           */
          if (bean.getTypeTv().equals("早期项目") == true) {

              holder.newsItemTypeTv.setTextColor(Color.GREEN);
          } else {
              holder.newsItemTypeTv.setTextColor(Color.BLUE);
          }
          String publishTime = bean.getTimeTv();
          holder.newsItemTimeTv.setText(publishTime);
          Picasso.with(context).load(bean.getImgUrl()).into(holder.newsItemImg);//毕加索加载图片

      }
      return convertView;
  }


  private class NewsFragmentHolder {
      ImageView newsItemImg;
      TextView newsItemAuthorTv;
      TextView newsItemContentTv;
      TextView newsItemTimeTv;
      TextView newsItemTypeTv;

      public NewsFragmentHolder(View view) {
          newsItemImg = (ImageView) view.findViewById(R.id.news_item_list_img1);
          newsItemAuthorTv = (TextView) view.findViewById(R.id.news_item_author_tv1);
          newsItemContentTv = (TextView) view.findViewById(R.id.news_item_content_tv1);
          newsItemTimeTv = (TextView) view.findViewById(R.id.news_item_time_tv1);
          newsItemTypeTv = (TextView) view.findViewById(R.id.news_item_type_tv1);
      }
  }
}
