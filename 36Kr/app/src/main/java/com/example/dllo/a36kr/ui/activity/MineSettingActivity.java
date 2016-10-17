package com.example.dllo.a36kr.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.IsLoginBean;
import com.example.dllo.a36kr.ui.fragment.MessageFragment;
import com.example.dllo.a36kr.utils.DataCleanManger;

import org.greenrobot.eventbus.EventBus;

public class MineSettingActivity extends AbsBaseActivity implements View.OnClickListener {
    private boolean isLogin;
    private Button loginBtn;
    private ImageView backImg;
    private TextView titleTv;
    private TextView cleanTv;
    private RelativeLayout cleanRL;


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("text",MODE_PRIVATE);
        isLogin = sp.getBoolean("isLogin",false);
        Log.d("ffff", "resume");
        Log.d("ffff", "isLogin:" + isLogin);
        if (isLogin ){
            loginBtn.setText("退出登录");
        }
        loginBtn.setOnClickListener(this);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_mine_setting;
    }

    @Override
    protected void initViews() {
        loginBtn = byView(R.id.activity_mine_setting_login_btn);
        backImg = byView(R.id.activity_discover_back_img);
        titleTv = byView(R.id.activity_discover_title_tv);
        cleanTv = byView(R.id.activity_mine_setting_clean_tv);
        cleanRL = byView(R.id.activity_mine_setting_clear_relativelayout);

    }

    @Override
    protected void initDatas() {
        Log.d("MineSettingActivity", "isLogin:" + isLogin);

        titleTv.setText("设置");
        backImg.setOnClickListener(this);
        try {
            cleanTv.setText(DataCleanManger.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        cleanRL.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_discover_back_img:
                finish();
                break;
            case R.id.activity_mine_setting_clear_relativelayout:
                DataCleanManger.clearAllCache(getApplication());
                try {
                    cleanTv.setText(DataCleanManger.getTotalCacheSize(getApplication()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.activity_mine_setting_login_btn:
                isLogin = false;
                //创建Sp对象
                //sp对象获取:通过Activity的get方法
                //Fragemnt里:通过context
                //参数一:文件名
                //参数二:权限:全手机可读可写的权限不安全,谷歌推荐使用PRIVATE,只有当前app能访问
                SharedPreferences sp = getSharedPreferences("text", Context.MODE_PRIVATE);
                //想要存储数据-需要使用操作类
                //Editor 编辑者的意思 edit是编辑的意思
                SharedPreferences.Editor editor = sp.edit();
                //存储的数据都是 key-value形式
                editor.putBoolean("isLogin",isLogin);

                editor.commit();
                Log.d("asd", "isLogin:" + isLogin);
                finish();
                break;
        }
    }



}
