package com.example.dllo.a36kr.ui.activity;


import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.InvestorDiscoverActivityBean;
import com.example.dllo.a36kr.model.bean.NewsActivityPopBean;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.ui.adapter.NewsActivityPopAdapter;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by dllo on 16/9/13.
 * NewsFragment的详情页
 */
public class NewsFragmentActivity extends AbsBaseActivity implements View.OnClickListener, PopupWindow.OnDismissListener,View.OnTouchListener,GestureDetector.OnGestureListener {


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
    private ImageView upDownImg;//上下的图标
    private PopupWindow mPopupWindow;//设置弹窗
    private View mView;//标题栏的下划线
    private NewsActivityPopAdapter newsActivityPopAdapter;
    private ListView listView;
    private TextView totleTv;
    private TextView viewTv;
    private ScrollView mScrollView;
    private ImageView backImg;


    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;
    GestureDetector mGestureDetector = null;
    private LinearLayout footLinearLayout;




    @Override
    protected int setLayout() {
        return R.layout.activity_news_fragment;
    }

    @Override
    protected void initViews() {
        titleImg = byView(R.id.activity_news_fragment_title_img);
        authorTv = byView(R.id.activity_news_fragment_title_name);
        likeTv = byView(R.id.activity_news_fragment_title_like);
        titleTv = byView(R.id.activity_news_fragment_content_name);
        contentTv = byView(R.id.activity_news_fragment_content_content);
        timeTv = byView(R.id.activity_news_fragment_content_time);
        upDownImg = byView(R.id.activity_news_fragment_title_updown);
        mView = byView(R.id.activity_news_fragment_view);
        footLinearLayout = byView(R.id.activity_foot_linearlayout);
        mScrollView = byView(R.id.activity_scrollview);
        backImg = byView(R.id.activity_foot_back_img);

        newsActivityPopAdapter = new NewsActivityPopAdapter(getApplicationContext());


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

        upDownImg.setOnClickListener(this);
        backImg.setOnClickListener(this);

        /**
         * 手势监听
         */
        mGestureDetector = new GestureDetector(getApplicationContext(),this);
        mScrollView.setOnTouchListener(this);
        //下面这几行一定要加上否则就只能识别onDown，onShowPress，和onLongPress
        mScrollView.setFocusable(true);
        mScrollView.setClickable(true);
        mScrollView.setLongClickable(true);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_news_fragment_title_updown:
                showPopupWindow();
                upDownImg.setImageResource(R.mipmap.icon_up);
                mPopupWindow.setOnDismissListener(this);
                break;
            case R.id.activity_foot_back_img:
                finish();
                break;
        }
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_news_pop_details, null);
        viewTv = (TextView) contentView.findViewById(R.id.activity_news_pop_details_view);
        totleTv = (TextView) contentView.findViewById(R.id.activity_news_pop_details_total);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        listView = (ListView) contentView.findViewById(R.id.activity_news_pop_details_listview);
        mPopupWindow.showAsDropDown(mView);
        listView.setAdapter(newsActivityPopAdapter);
        VolleyInstance.getInstance().startRequest(AllContantValues.ACTIVITYNEWSDETAIL + postId + "/author-region", new VolleyReault() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                NewsActivityPopBean bean = gson.fromJson(resultStr, NewsActivityPopBean.class);
                List<NewsActivityPopBean.DataBean.LatestArticleBean> data = bean.getData().getLatestArticle();
                newsActivityPopAdapter.setDatas(data);

            }

            @Override
            public void failure() {

            }
        });


        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 1f;
        getWindow().setAttributes(params);
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                closePopupWindow();
                return false;
            }
        });
    }

    private void closePopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);

        }
    }

    @Override
    public void onDismiss() {
        upDownImg.setImageResource(R.mipmap.icon_down);
    }






/****************************手势监听方法***************************/
    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("onDown", "onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.e("onShowPress", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.e("onSingleTapUp", "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        Log.e("onScroll", "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.e("onLongPress", "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getY()-e2.getY() > FLING_MIN_DISTANCE
                && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
            // Fling left
            footLinearLayout.setVisibility(View.GONE);
        } else if (e2.getY()-e1.getY() > FLING_MIN_DISTANCE
                && Math.abs(velocityY) > FLING_MIN_VELOCITY) {
            // Fling right
            footLinearLayout.setVisibility(View.VISIBLE);
        }
        Log.e("onFling", "onFling");
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
