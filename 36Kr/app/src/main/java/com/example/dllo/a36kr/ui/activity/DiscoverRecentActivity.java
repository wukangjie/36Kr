package com.example.dllo.a36kr.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.EquityFragmentBean;
import com.example.dllo.a36kr.model.bean.RecentDiscoverActivityBean;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.ui.adapter.RecentDiscoverActivityAdapter;
import com.example.dllo.a36kr.ui.fragment.DiscoverFragment;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.example.dllo.a36kr.view.RefreshLayout;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.List;

public class DiscoverRecentActivity extends AbsBaseActivity implements View.OnClickListener, PopupWindow.OnDismissListener {
    private RecentDiscoverActivityAdapter adapter;
    private RefreshLayout swipeRefreshLayout;
    private ListView listView;
    private ImageView mBackImg;//返回图标的点击事件
    private RelativeLayout mTypeRL;//设置活动类型的点击事件
    private PopupWindow mTypePopWindow;//设置弹窗
    private ImageView mTypeImg;
    private TextView mTypeTv;
    private RelativeLayout mTimeRL;
    private PopupWindow mTimePopWindow;
    private TextView mTimeTv;
    private ImageView mTimeImg;
    private TextView mTitleTv;
    private String url = AllContantValues.ALLDISSCOVERRECENT;//网址
    private String strLoad;//拼接后的网址
    private int page = 2;//拼接的页数
    private List<RecentDiscoverActivityBean.DataBean.DataBean1> data;
    private List<RecentDiscoverActivityBean.DataBean.DataBean1> data1;


    @Override
    protected int setLayout() {
        return R.layout.activity_discover_recent;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.activity_discover_recent_listview);
        mBackImg = byView(R.id.activity_discover_back_img);
        mTypeImg = byView(R.id.activity_discover_recent_type_img);
        mTypeRL = byView(R.id.activity_discover_recent_type_relativelayout);
        mTypeTv = byView(R.id.activity_discover_recent_type_tv);
        mTimeRL = byView(R.id.activity_discover_recent_time_relativelayout);
        mTimeTv = byView(R.id.activity_discover_recent_time_tv);
        mTimeImg = byView(R.id.activity_discover_recent_time_img);
        mTitleTv = byView(R.id.activity_discover_title_tv);
        swipeRefreshLayout = byView(R.id.activity_discover_recent_rootview);
        adapter = new RecentDiscoverActivityAdapter(getApplicationContext());
        listView.setAdapter(adapter);


    }

    @Override
    protected void initDatas() {
        strLoad = url.replace("1", page + "");

        /**
         * 设置下拉刷新
         */
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        VolleyInstance.getInstance().startRequest(url, new VolleyReault() {
                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                RecentDiscoverActivityBean bean = gson.fromJson(resultStr, RecentDiscoverActivityBean.class);
                                data = bean.getData().getData();
                                adapter.setDatas(data);
                            }

                            @Override
                            public void failure() {

                            }
                        });
                        //停止刷新动画
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        swipeRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        VolleyInstance.getInstance().startRequest(strLoad, new VolleyReault() {


                            @Override
                            public void success(String resultStr) {
                                Gson gson = new Gson();
                                RecentDiscoverActivityBean bean = gson.fromJson(resultStr, RecentDiscoverActivityBean.class);
                                data1 = bean.getData().getData();
                                adapter.setDatas(data);
                                strLoad.replace(page + "", page + 1 + "");
                                swipeRefreshLayout.setLoading(false);
                            }

                            @Override
                            public void failure() {

                            }
                        });
                        //停止刷新动画
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        VolleyInstance.getInstance().startRequest(url, new VolleyReault() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                RecentDiscoverActivityBean bean = gson.fromJson(resultStr, RecentDiscoverActivityBean.class);
                data = bean.getData().getData();
                adapter.setDatas(data);
            }

            @Override
            public void failure() {

            }
        });
        mBackImg.setOnClickListener(this);
        mTypeRL.setOnClickListener(this);
        mTimeRL.setOnClickListener(this);
        mTitleTv.setText("近期活动");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_discover_back_img:
                finish();
                break;
            case R.id.activity_discover_recent_type_relativelayout:
                showTypePopupWindow();//设置类型弹窗
                mTypeTv.setTextColor(Color.BLUE);
                mTypeImg.setImageResource(R.mipmap.icon_up);
                mTypeImg.setColorFilter(Color.BLUE);
                mTypePopWindow.setOnDismissListener(this);



                break;
            case R.id.activity_discover_recent_time_relativelayout:
                showTimePopupWindow();//设置时间弹窗
                mTimeTv.setTextColor(Color.BLUE);
                mTimeImg.setImageResource(R.mipmap.icon_up);
                mTimeImg.setColorFilter(Color.BLUE);
                mTimePopWindow.setOnDismissListener(this);



                break;
            case R.id.activity_type_pop_all:
                url = AllContantValues.ALLDISSCOVERRECENT;
                mTypeTv.setText("全部");
                mTypePopWindow.dismiss();
                break;
            case R.id.activity_type_pop_day:
                url = AllContantValues.DISSCOVERDEMODAY;
                mTypeTv.setText("Demo Day");
                mTypePopWindow.dismiss();
                break;
            case R.id.activity_type_pop_space:
                url = AllContantValues.DISSCOVERSPACE;
                mTypeTv.setText("氪空间");
                mTypePopWindow.dismiss();
                break;
            case R.id.activity_type_pop_equity:
                url = AllContantValues.DISSCOVEREQUITY;
                mTypeTv.setText("股权投资");
                mTypePopWindow.dismiss();
                break;
            case R.id.activity_type_pop_service:
                url = AllContantValues.DISSCOVERSERVICE;
                mTypeTv.setText("企业服务");
                mTypePopWindow.dismiss();
                break;
            case R.id.activity_type_pop_financing:
                url = AllContantValues.DISSCOVERFINANCING;
                mTypeTv.setText("极速融资");
                mTypePopWindow.dismiss();
                break;
            case R.id.activity_time_pop_all:
                mTimeTv.setText("全部");
                mTimePopWindow.dismiss();
                break;
            case R.id.activity_time_pop_late:
                mTimeTv.setText("本周末");
                mTimePopWindow.dismiss();
                break;
            case R.id.activity_time_pop_thisweek:
                mTimeTv.setText("本周");
                mTimePopWindow.dismiss();
                break;
            case R.id.activity_time_pop_nextweek:
                mTimeTv.setText("下周");
                mTimePopWindow.dismiss();
                break;
        }
        VolleyInstance.getInstance().startRequest(url, new VolleyReault() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                RecentDiscoverActivityBean bean = gson.fromJson(resultStr, RecentDiscoverActivityBean.class);
                List<RecentDiscoverActivityBean.DataBean.DataBean1> data = bean.getData().getData();
                adapter.setDatas(data);
            }

            @Override
            public void failure() {

            }
        });
    }

    private void showTimePopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_time_pop_time, null);
        mTimePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mTimePopWindow.setContentView(contentView);
        TextView mAllTimePop = (TextView) contentView.findViewById(R.id.activity_time_pop_all);
        TextView mLateTimePop = (TextView) contentView.findViewById(R.id.activity_time_pop_late);
        TextView mThisTimePop = (TextView) contentView.findViewById(R.id.activity_time_pop_thisweek);
        TextView mNextTimePop = (TextView) contentView.findViewById(R.id.activity_time_pop_nextweek);
        mAllTimePop.setOnClickListener(this);
        mLateTimePop.setOnClickListener(this);
        mThisTimePop.setOnClickListener(this);
        mNextTimePop.setOnClickListener(this);


        mTimePopWindow.showAsDropDown(mTimeRL);//使PopupWindow显示在指定空间的下方
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 1f;
        getWindow().setAttributes(params);

        //点击其他地方消失
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                closeTimePopupWindow();
                return false;
            }
        });

    }


    /**
     * 关闭弹窗
     */
    private void closeTimePopupWindow() {
        if (mTimePopWindow != null && mTimePopWindow.isShowing()) {
            mTimePopWindow.dismiss();
            mTimePopWindow = null;
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);
        }
    }


    private void showTypePopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_type_pop_type, null);
        mTypePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mTypePopWindow.setContentView(contentView);
        TextView mAllTypePop = (TextView) contentView.findViewById(R.id.activity_type_pop_all);
        TextView mDayTypePop = (TextView) contentView.findViewById(R.id.activity_type_pop_day);
        TextView mSpaceTypePop = (TextView) contentView.findViewById(R.id.activity_type_pop_space);
        TextView mEquityTypePop = (TextView) contentView.findViewById(R.id.activity_type_pop_equity);
        TextView mServiceTypePop = (TextView) contentView.findViewById(R.id.activity_type_pop_service);
        TextView mFinancingTypePop = (TextView) contentView.findViewById(R.id.activity_type_pop_financing);
        mAllTypePop.setOnClickListener(this);
        mDayTypePop.setOnClickListener(this);
        mSpaceTypePop.setOnClickListener(this);
        mEquityTypePop.setOnClickListener(this);
        mServiceTypePop.setOnClickListener(this);
        mFinancingTypePop.setOnClickListener(this);

        mTypePopWindow.showAsDropDown(mTypeRL);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 1f;
        getWindow().setAttributes(params);

        //点击其他地方消失
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                closeTypePopupWindow();
                return false;
            }
        });

    }


    /**
     * 关闭弹窗
     */
    private void closeTypePopupWindow() {
        if (mTypePopWindow != null && mTypePopWindow.isShowing()) {
            mTypePopWindow.dismiss();
            mTypePopWindow = null;
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);
        }
    }

    /**
     * 弹窗处于关闭状态下的状态
     */
    @Override
    public void onDismiss() {
        mTypeTv.setTextColor(Color.BLACK);
        mTypeImg.setImageResource(R.mipmap.icon_down);
        mTypeImg.setColorFilter(Color.BLACK);
        mTimeTv.setTextColor(Color.BLACK);
        mTimeImg.setImageResource(R.mipmap.icon_down);
        mTimeImg.setColorFilter(Color.BLACK);
    }
}
