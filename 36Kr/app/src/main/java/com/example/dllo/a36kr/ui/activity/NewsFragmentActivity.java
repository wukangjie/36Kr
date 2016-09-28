package com.example.dllo.a36kr.ui.activity;


import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by dllo on 16/9/13.
 * NewsFragment的详情页
 */
public class NewsFragmentActivity extends AbsBaseActivity {


    private String postId;//定义从NewsFragment传来的ID
    private ImageView titleImg;//定义作者头像
    private TextView authorTv;//定义作者姓名
    private TextView likeTv;//简介
    private TextView titleTv;//标题
    private WebView contentTv;//内容
    private TextView timeTv;//时间
    private String contentS;//内容
    private String titleTvS;//标题
    private String likeTvS;//简介
    private String titleImgS;//头像
    private String authorTvS;//作者
    private String publishTime;//时间


    @Override
    protected int setLayout() {
        return R.layout.activity_news_fragment;
    }

    @Override
    protected void initViews() {
        titleImg = (CircleImageView) findViewById(R.id.activity_news_fragment_title_img);
        authorTv = (TextView) findViewById(R.id.activity_news_fragment_title_name);
        likeTv = (TextView) findViewById(R.id.activity_news_fragment_title_like);
        titleTv = (TextView) findViewById(R.id.activity_news_fragment_content_name);
        contentTv = (WebView) findViewById(R.id.activity_news_fragment_content_content);
        timeTv = (TextView) findViewById(R.id.activity_news_fragment_content_time);


    }

    @Override
    protected void initDatas() {

        /**
         * 设置不跳转网页,在当前页面显示
         */
        contentTv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return false;
            }

        });
        Intent intent = getIntent();//获取传值
        postId = intent.getStringExtra("postId");
        publishTime = intent.getStringExtra("publishTime");
        /**
         * 网络请求
         */
        VolleyInstance.getInstance().startRequest(AllContantValues.ACTIVITYNEWSDETAIL + postId, new VolleyReault() {
            @Override
            public void success(String resultStr) {
                /**
                 * JSON数据解析
                 */
                JSONObject obj = null;
                try {
                    obj = new JSONObject(resultStr);
                    JSONObject obj1 = obj.getJSONObject("data");
                    contentS = obj1.getString("content");
                    titleTvS = obj1.getString("title");
                    likeTvS = obj1.getString("summary");
                    JSONObject obj2 = obj1.getJSONObject("user");
                    titleImgS = obj2.getString("avatar");
                    authorTvS = obj2.getString("name");
                    titleTv.setText(titleTvS);
                    likeTv.setText(likeTvS);
                    if (likeTv.equals(null)) {
                        likeTv.setText(R.string.activity_news_Fragment_like);
                    }

                    timeTv.setText(publishTime);
                    contentTv.loadData(contentS, "text/html;charset=UTF-8", null);
                    authorTv.setText(authorTvS);
                    Picasso.with(getApplicationContext()).load(titleImgS).into(titleImg);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void failure() {

            }
        });


    }

}
