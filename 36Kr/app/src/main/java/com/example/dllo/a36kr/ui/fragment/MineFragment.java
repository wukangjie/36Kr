package com.example.dllo.a36kr.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.activity.ItoContralActivity;
import com.example.dllo.a36kr.ui.activity.UnLoginActivity;


/**
 * Created by dllo on 16/9/9.
 */
public class MineFragment extends AbsFragment implements View.OnClickListener {
    private RelativeLayout loginRLL;
    private boolean isLogin = false;
    private ImageView mSettingImg;
    private RelativeLayout mOrderRL, mAccoutRL;
    private RelativeLayout mAuthenticationRL, mFavoriteRL;
    private RelativeLayout mCompanyRL;
    private RelativeLayout mCouponRL;
    private RelativeLayout mUnderstandRL;
    private RelativeLayout mHontlineRL;


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


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_mine_setting_img:

                break;
            case R.id.fragment_mine_login_rll:
                if (isLogin) {

                } else {
                    startActivity(new Intent(getActivity(), UnLoginActivity.class));


                }
            case R.id.fragment_mine_order_relativelayout:
                if (isLogin) {

                } else {
                    startActivity(new Intent(getActivity(), UnLoginActivity.class));

                }
                break;
            case R.id.fragment_mine_accout_relativelayout:
                if (isLogin) {

                } else {
                    startActivity(new Intent(getActivity(), UnLoginActivity.class));
                }
                break;
            case R.id.fragment_mine_authentication_relativelayout:
                if (isLogin) {

                } else {
                    startActivity(new Intent(getActivity(), UnLoginActivity.class));
                }
                break;
            case R.id.fragment_mine_favorite_relativelayout:
                if (isLogin) {

                } else {
                    startActivity(new Intent(getActivity(), UnLoginActivity.class));
                }
                break;
            case R.id.fragment_mine_company_relativelayout:
                if (isLogin) {

                } else {
                    startActivity(new Intent(getActivity(), UnLoginActivity.class));
                }
                break;
            case R.id.fragment_mine_coupon_relativelayout:
                if (isLogin) {

                } else {
                    startActivity(new Intent(getActivity(), UnLoginActivity.class));
                }
                break;
            case R.id.fragment_mine_understand_relativelayout:
                if (isLogin) {

                } else {
                    startActivity(new Intent(getActivity(), UnLoginActivity.class));
                }
                break;
            case R.id.fragment_mine_hotline_relativelayout:
                if (isLogin) {

                } else {
                    startActivity(new Intent(getActivity(), UnLoginActivity.class));
                }
                break;

        }




    }
}
