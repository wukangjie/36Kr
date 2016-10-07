package com.example.dllo.a36kr.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;

public class LoopViewActivity extends AbsBaseActivity implements View.OnClickListener {
    private String url;//网址
    private ImageView backTitleImg;
    private TextView titleTv;
    private ImageView backFootImg;
    private WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.activity_loop_view;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.activity_loop_view_webview);
        backTitleImg = byView(R.id.activity_discover_back_img);
        backFootImg = byView(R.id.activity_foot_back_img);
        titleTv = byView(R.id.activity_discover_title_tv);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return false;
            }

        });
        WebSettings webSettings =webView.getSettings();
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(false);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        webView.loadUrl(url);
        Log.d("LoopViewActivity", url);
        titleTv.setText(intent.getStringExtra("title"));
        backTitleImg.setImageResource(R.mipmap.mine_icon_bounced_close);
        backTitleImg.setColorFilter(Color.BLUE);
        backTitleImg.setOnClickListener(this);
        backFootImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_discover_back_img:
                finish();
                break;
            case R.id.activity_foot_back_img:
                finish();
                break;
        }
    }
}
