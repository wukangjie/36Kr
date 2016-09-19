package com.example.dllo.a36kr.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.squareup.picasso.Picasso;

import me.yokeyword.swipebackfragment.SwipeBackActivity;

/**
 * Created by dllo on 16/9/13.
 * news详情页
 */
public class NewsFragmentActivity extends SwipeBackActivity{
    private ImageView titleImg;
    private TextView authorTv;
    private TextView likeTv;
    private TextView titleTv;
    private TextView contentTv;
    private TextView timeTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_fragment);
        titleImg = (ImageView) findViewById(R.id.activity_news_fragment_title_img);
        authorTv = (TextView) findViewById(R.id.activity_news_fragment_title_name);
        likeTv = (TextView) findViewById(R.id.activity_news_fragment_title_like);
        titleTv = (TextView) findViewById(R.id.activity_news_fragment_content_name);
        contentTv = (TextView) findViewById(R.id.activity_news_fragment_content_content);
        timeTv = (TextView) findViewById(R.id.activity_news_fragment_content_time);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String imgSrc = intent.getStringExtra("imgSrc");
        String columnName = intent.getStringExtra("columnName");
        String publishTime = intent.getStringExtra("publishTime");
        String authorName = intent.getStringExtra("authorName");
        titleTv.setText(title);
        authorTv.setText(authorName);
        timeTv.setText(publishTime);
        Picasso.with(this).load(imgSrc).into(titleImg);
    }
    @Override
    public void setVrModeEnabled(boolean enabled, ComponentName requestedComponent) throws PackageManager.NameNotFoundException {
        getSwipeBackLayout().setEnableGesture(true);
    }
}
