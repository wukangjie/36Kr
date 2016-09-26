package com.example.dllo.a36kr.ui.activity;


import android.content.Intent;
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


    private String postId;
    private ImageView titleImg;
    private TextView authorTv;
    private TextView likeTv;
    private TextView titleTv;
    private WebView contentTv;
    private TextView timeTv;
    private String contentS;
    private String titleTvS;
    private String likeTvS;
    private String titleImgS;
    private String authorTvS;
    private String publishTime;


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
        Intent intent = getIntent();
        postId = intent.getStringExtra("postId");
        publishTime = intent.getStringExtra("publishTime");
        VolleyInstance.getInstance().startRequest(AllContantValues.ACTIVITYNEWSDETAIL + postId, new VolleyReault() {
            @Override
            public void success(String resultStr) {
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
                        likeTv.setText("这个作者很懒,什么都没有留下");
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
