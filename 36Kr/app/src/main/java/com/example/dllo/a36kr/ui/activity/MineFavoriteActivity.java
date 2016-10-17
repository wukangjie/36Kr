package com.example.dllo.a36kr.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.CollectBean;
import com.example.dllo.a36kr.model.db.LiteOrmInstance;
import com.example.dllo.a36kr.ui.adapter.MineFavoriteAdapter;

import java.util.List;

public class MineFavoriteActivity extends AbsBaseActivity {
    private ImageView backImg;
    private TextView titleTv;
    private ListView listView;
    private MineFavoriteAdapter mineFavoriteAdapter;


    @Override
    protected int setLayout() {
        return R.layout.activity_mine_favorite;
    }

    @Override
    protected void initViews() {
        backImg = byView(R.id.activity_discover_back_img);
        titleTv = byView(R.id.activity_discover_title_tv);
        listView = byView(R.id.activity_mine_favorite_listview);
    }

    @Override
    protected void initDatas() {
        titleTv.setText("我的收藏");
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MineFavoriteActivity.this, "哈哈哈", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

//        List<CollectBean> list = LiteOrmInstance.getInstance().queryAll();
//        List<HashMap<String, Object>> data = new ArrayList<>();
//        for (CollectBean content : list) {
//            HashMap<String, Object> item = new HashMap<>();
//            Log.d("fff", content.getNameTv());
//            item.put("name", content.getNameTv());
//            item.put("content", content.getContentTv());
//            item.put("time", content.getTimeTv());
//            item.put("type", content.getTypeTv());
//            item.put("postId", content.getPostId());
//            item.put("imgUrl", content.getImgUrl());
//
//            data.add(item);
//
//
//            //适配器
//            SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.mine_item_listview,
//                    new String[]{"name", "content", "time", "type", "imgUrl"},
//                    new int[]{R.id.news_item_author_tv1, R.id.news_item_content_tv1,
//                            R.id.news_item_time_tv1, R.id.news_item_type_tv1, R.id.news_item_list_img1});
//            listView.setAdapter(adapter);


            /************************************/


        mineFavoriteAdapter = new MineFavoriteAdapter(this);
        final List<CollectBean> cbs = LiteOrmInstance.getInstance().queryAll();
        Log.d("ttt", "cbs:" + cbs);
        mineFavoriteAdapter.setDatas(cbs);
        listView.setAdapter(mineFavoriteAdapter);//加载适配器

        /**
         * 设置listView(列表视图) 的监听事件
         * 点击跳转到 NewsFragment的详情页
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), NewsFragmentActivity.class);
                intent.putExtra("publishTime", cbs.get(position).getTimeTv());
                intent.putExtra("postId", cbs.get(position).getPostId());
                intent.putExtra("typeTv",cbs.get(position).getTypeTv());
                startActivity(intent);
            }
        });
        }

    }



