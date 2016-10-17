package com.example.dllo.a36kr.ui.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.IsLoginBean;
import com.example.dllo.a36kr.ui.activity.ItoContralActivity;
import com.example.dllo.a36kr.ui.activity.MineFavoriteActivity;
import com.example.dllo.a36kr.ui.activity.MineSettingActivity;
import com.example.dllo.a36kr.ui.activity.UnLoginActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by dllo on 16/9/9.
 * 我的Fragment
 */
public class MineFragment extends AbsFragment implements View.OnClickListener {
    private RelativeLayout loginRLL;//登录控件
    private boolean isLogin = false;//判断是否登录
    private EventBus mEventBus;
    private ImageView mSettingImg;//设置图标
    private RelativeLayout mOrderRL, mAccoutRL;
    private RelativeLayout mAuthenticationRL, mFavoriteRL;
    private RelativeLayout mCompanyRL;
    private RelativeLayout mCouponRL;
    private RelativeLayout mUnderstandRL;
    private RelativeLayout mHontlineRL;
    private TextView nameTv;

    private ImageView authorImg;

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getContext().getSharedPreferences("text",Context.MODE_PRIVATE);
        isLogin = sp.getBoolean("isLogin",false);
        Log.d("MineFragment", "isLogin:" + isLogin);
        if (isLogin ){
            Log.d("MineFragment", "asdfsfsdf-------");
            nameTv.setText(sp.getString("name",null));
            Log.d("MineFragment", sp.getString("name", null));
            Picasso.with(getContext()).load(sp.getString("icon",null)).into(authorImg);


        }else {
            Log.d("MineFragment", "asdfsfsd高合金钢f-------");
            nameTv.setText("未登录");
            Log.d("MineFragment", "豆腐干豆腐");
            authorImg.setImageResource(R.mipmap.common_avatar);


        }
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        loginRLL = byView(R.id.fragment_mine_login_rll);
        mSettingImg = byView(R.id.fragment_mine_setting_img);
        mOrderRL = byView(R.id.fragment_mine_order_relativelayout);
        mAccoutRL = byView(R.id.fragment_mine_accout_relativelayout);
        mAuthenticationRL = byView(R.id.fragment_mine_authentication_relativelayout);
        mFavoriteRL = byView(R.id.fragment_mine_favorite_relativelayout);
        mCompanyRL = byView(R.id.fragment_mine_company_relativelayout);
        mCouponRL = byView(R.id.fragment_mine_company_relativelayout);
        mUnderstandRL = byView(R.id.fragment_mine_understand_relativelayout);
        mHontlineRL = byView(R.id.fragment_mine_hotline_relativelayout);
        mEventBus = EventBus.getDefault();
        authorImg = byView(R.id.fragment_mine_avatar_img);
        nameTv = byView(R.id.fragment_mine_name_tv);


    }


    @Override
    protected void initDatas() {

        loginRLL.setOnClickListener(this);
        mSettingImg.setOnClickListener(this);
        mOrderRL.setOnClickListener(this);
        mAccoutRL.setOnClickListener(this);
        mAuthenticationRL.setOnClickListener(this);
        mFavoriteRL.setOnClickListener(this);
        mCompanyRL.setOnClickListener(this);
        mCouponRL.setOnClickListener(this);
        mUnderstandRL.setOnClickListener(this);
        mHontlineRL.setOnClickListener(this);
//        EventBus.getDefault().register(this);




    }



//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getData(IsLoginBean bean){
//        Log.d("MineFragment", "bean:" + bean);
//
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }

    @Override
    public void onClick(View v) {
        if (isLogin) {
            switch (v.getId()) {
                case R.id.fragment_mine_setting_img:
                    startActivity(new Intent(getContext(), MineSettingActivity.class));
                    break;


                case R.id.fragment_mine_login_rll:


                    break;
                case R.id.fragment_mine_order_relativelayout:

                    break;
                case R.id.fragment_mine_accout_relativelayout:

                    break;
                case R.id.fragment_mine_authentication_relativelayout:

                    break;
                case R.id.fragment_mine_favorite_relativelayout:
                    startActivity(new Intent(getActivity(), MineFavoriteActivity.class));
                    break;
                case R.id.fragment_mine_company_relativelayout:

                    break;
                case R.id.fragment_mine_coupon_relativelayout:

                    break;
                case R.id.fragment_mine_understand_relativelayout:

                    break;
                case R.id.fragment_mine_hotline_relativelayout:
                    showDialog();
                    break;

            }
        } else {
            Intent intent = new Intent(getActivity(), UnLoginActivity.class);
            intent.putExtra("name", "");
            switch (v.getId()) {
                case R.id.fragment_mine_setting_img:
                    startActivity(new Intent(getContext(), MineSettingActivity.class));
                    break;
                case R.id.fragment_mine_login_rll:

                    startActivity(intent);


                    break;
                case R.id.fragment_mine_order_relativelayout:

                    startActivity(intent);


                    break;
                case R.id.fragment_mine_accout_relativelayout:

                    startActivity(intent);

                    break;
                case R.id.fragment_mine_authentication_relativelayout:

                    startActivity(intent);

                    break;
                case R.id.fragment_mine_favorite_relativelayout:

                    startActivity(intent);

                    break;
                case R.id.fragment_mine_company_relativelayout:

                    startActivity(intent);

                    break;
                case R.id.fragment_mine_coupon_relativelayout:

                    startActivity(intent);
                    break;
                case R.id.fragment_mine_understand_relativelayout:

                    startActivity(intent);
                    break;
                case R.id.fragment_mine_hotline_relativelayout:

                   showDialog();
                    break;
            }
        }


    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //设置标题
        builder.setTitle("工作日: 10:00 - 19:00");
        //设置信息内容
        builder.setMessage("呼叫400-995-3636");
        builder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "haha", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //设置按钮
        //position 确认的,积极地
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"4009953636"));
                startActivity(intent);
            }
        });

        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //消极的,不确认的
//        builder.setNegativeButton("不知道", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
        /**
         * 发短信成并创造
         * */
        builder.create().show();
    }


}

