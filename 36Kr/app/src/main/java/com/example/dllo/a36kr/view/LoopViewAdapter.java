package com.example.dllo.a36kr.view;


import com.bumptech.glide.Glide;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.CarouselBean;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.ui.activity.LoopViewActivity;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图的适配器
 *
 */
public class LoopViewAdapter extends PagerAdapter {
    private List<LoopViewEntity> list;
    private Context context;
    private OnItemClickListener listener;
    private List<CarouselBean.DataBean.PicsBean> datas;
    private CarouselBean.DataBean.PicsBean data;
    private List<CarouselBean.DataBean.PicsBean> datas1;

    public LoopViewAdapter(Context context, List<LoopViewEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        datas = new ArrayList<>();
        View view = LayoutInflater.from(context).inflate(R.layout.loopview_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.loop_image);
        Glide.with(context).load(list.get(position).getImageUrl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
        VolleyInstance.getInstance().startRequest(AllContantValues.ALLNEWSROTATEURL, new VolleyReault() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                CarouselBean myBean = gson.fromJson(resultStr, CarouselBean.class);
                //获取解析数据的集合
                datas1 = myBean.getData().getPics();
            }

            @Override
            public void failure() {

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "点击了"+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,LoopViewActivity.class);
                intent.putExtra("title",datas1.get(position).getTitle());
                intent.putExtra("url", datas1.get(position).getLocation());

                context.startActivity(intent);//由适配器传到Activity
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setDatas(List<CarouselBean.DataBean.PicsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
