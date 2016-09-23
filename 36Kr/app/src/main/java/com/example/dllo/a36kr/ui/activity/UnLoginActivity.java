package com.example.dllo.a36kr.ui.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.fragment.LoginFragment;
import com.example.dllo.a36kr.ui.fragment.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/22.
 */
public class UnLoginActivity extends AbsBaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragments;
    private ImageView mBackImg;

    @Override
    protected int setLayout() {
        return R.layout.activity_unlogin;
    }

    @Override
    protected void initViews() {
        mTabLayout = byView(R.id.activity_unlogin_tablayout);
        mViewPager = byView(R.id.activity_unlogin_viewpaget);
        mBackImg = byView(R.id.activity_unlogin_setting_close);
    }

    @Override
    protected void initDatas() {
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("UnLoginActivity", "点击了返回键");
                finish();
            }
        });
        fragments = new ArrayList<>();
        fragments.add(new LoginFragment());
        fragments.add(new RegisterFragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabTextColors(Color.GRAY, Color.WHITE);
        mTabLayout.getTabAt(0).setText("登录");
        mTabLayout.getTabAt(1).setText("注册");


    }
}
