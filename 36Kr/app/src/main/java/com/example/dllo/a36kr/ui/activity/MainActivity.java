package com.example.dllo.a36kr.ui.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.fragment.DiscoverFragment;
import com.example.dllo.a36kr.ui.fragment.EquityFragment;
import com.example.dllo.a36kr.ui.fragment.MessageFragment;
import com.example.dllo.a36kr.ui.fragment.MineFragment;
import com.example.dllo.a36kr.ui.fragment.NewsFragment;
import com.example.dllo.a36kr.ui.fragment.NewsUseFragment;
import com.example.dllo.a36kr.utils.AllContantValues;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ItoContralActivity, View.OnClickListener {
    private DrawerLayout mainDrawerLayout;//实现侧滑菜单的组件
    private TabLayout mainTl;
    private ViewPager mainVp;
    private List<Fragment> fragments;//Fragment的集合
    private LinearLayout lineatLayout;//整个抽屉布局
    private LinearLayout allLl;//全部选项
    private LinearLayout earlyLl;//早期项目
    private LinearLayout blateLl;//B轮后
    private LinearLayout bigLl;//大公司
    private LinearLayout capitalLl;//资本
    private LinearLayout depthLl;//深度
    private LinearLayout researchLl;//研究
    private ImageView backImg;//返回图标
    private NewsFragment newsFragment;//新闻碎片
    private long tempTime = 0;//初始值，记录上一次按下返回键的时间点
    private boolean isLogin;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDatas();
    }

    private void initViews() {
        mainTl = (TabLayout) findViewById(R.id.main_tl);
        mainVp = (ViewPager) findViewById(R.id.main_vp);
        allLl = (LinearLayout) findViewById(R.id.main_drawer_all_ll);
        earlyLl = (LinearLayout) findViewById(R.id.main_drawer_early_ll);
        blateLl = (LinearLayout) findViewById(R.id.main_drawer_blate_ll);
        bigLl = (LinearLayout) findViewById(R.id.main_drawer_big_ll);
        capitalLl = (LinearLayout) findViewById(R.id.main_drawer_capital_ll);
        depthLl = (LinearLayout) findViewById(R.id.main_drawer_depth_ll);
        researchLl = (LinearLayout) findViewById(R.id.main_drawer_research_ll);
        backImg = (ImageView) findViewById(R.id.main_drawer_img);
        lineatLayout = (LinearLayout) findViewById(R.id.main_drawer_ll);
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);


    }
//    public class MyReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            isLogin = Boolean.parseBoolean(intent.getStringExtra("isLogin"));
//            Log.d("MyReceiver", intent.getStringExtra("isLogin"));
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("text",MODE_PRIVATE);
        isLogin = sp.getBoolean("isLogin",false);

    }

    protected void initDatas() {
        fragments = new ArrayList<>();
        // 如果要用这个Fragment对象
        newsFragment = NewsFragment.newInstance();
        fragments.add(newsFragment);
        fragments.add(new EquityFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MineFragment());
        /**
         * viewPage绑定Fragment
         */
        mainVp.setAdapter(new FragmentPagerAdapter
                (getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mainTl.setTabTextColors(Color.BLACK, Color.argb(255, 72, 118, 255));//设置TabLayout点击前后的颜色
        /**
         * TabLayout绑定ViewPager
         */
        mainTl.setupWithViewPager(mainVp);
        mainTl.setTabMode(TabLayout.MODE_FIXED);
        mainTl.getTabAt(0).setText(R.string.tab_news_name).setIcon(R.drawable.selector_news_tab);
        mainTl.getTabAt(1).setText(R.string.tab_equity_name).setIcon(R.drawable.selector_equity_tab);
        mainTl.getTabAt(2).setText(R.string.tab_discover_name).setIcon(R.drawable.selector_discover_tab);
        mainTl.getTabAt(3).setText(R.string.tab_message_name).setIcon(R.drawable.selector_message_tab);
        mainTl.getTabAt(4).setText(R.string.tab_mine_name).setIcon(R.drawable.selector_mine_tab);
        allLl.setOnClickListener(this);
        earlyLl.setOnClickListener(this);
        blateLl.setOnClickListener(this);
        bigLl.setOnClickListener(this);
        capitalLl.setOnClickListener(this);
        depthLl.setOnClickListener(this);
        researchLl.setOnClickListener(this);
        backImg.setOnClickListener(this);


        /**
         * 设置抽屉锁,使其只在newsFragment显示
         */
        mainTl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                } else {
                    mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                }
                if (tab.getPosition() == 3){
                    if (!isLogin ){
                        startActivity(new Intent(getApplication(),UnLoginActivity.class));
                    }

                }

                mainVp.setCurrentItem(mainTl.getSelectedTabPosition(), false);//重新设置ViewPager与TabLayout联动
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        mainVp.setOffscreenPageLimit(5);


    }

    /**
     * 抽屉的点击事件
     *
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_drawer_all_ll:
//                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.ALLNEWSROTATEURL, true));
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.ALLNEWSURL, true));
                newsFragment.changeTextFragment(getResources().getString(R.string.all_news_name));
                break;
            case R.id.main_drawer_early_ll:
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.EARLYNEWSURL, false));
                newsFragment.changeTextFragment(getResources().getString(R.string.early_news_name));
                break;
            case R.id.main_drawer_blate_ll:
//                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.BLATENEWSURL,false));
                newsFragment.changeTextFragment(getResources().getString(R.string.b_news_name));
                Toast.makeText(this, "这个网址不太好使", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_drawer_big_ll:
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.BIGNEWSURL, false));
                newsFragment.changeTextFragment(getResources().getString(R.string.big_news_name));
                break;
            case R.id.main_drawer_capital_ll:
//                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.CAPITALNEWSURL,false));
                newsFragment.changeTextFragment(getResources().getString(R.string.capital_news_name));
                Toast.makeText(this, "这个网址不太好使", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_drawer_depth_ll:
                newsFragment.changeTextFragment(getResources().getString(R.string.depth_news_name));
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.DEPTHNEWSURL, false));
                break;
            case R.id.main_drawer_research_ll:
                newsFragment.changeTextFragment(getResources().getString(R.string.research_news_name));
                newsFragment.changeFragment(NewsUseFragment.newInstance(AllContantValues.RESEARCHNEWSURL, false));
                break;
            case R.id.main_drawer_img:
                mainDrawerLayout.closeDrawer(lineatLayout);
                break;
        }
        mainDrawerLayout.closeDrawer(lineatLayout);

    }


    //退出程序提示
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//判断按下的是否为返回键，并且是否按下
        if((keyCode == KeyEvent.KEYCODE_BACK) &&
                (event.getAction() == KeyEvent.ACTION_DOWN))
        {
//如果两次按返回键时间间隔大于2000毫秒就吐司提示，否则finish（）当前Activity
            if((System.currentTimeMillis() - tempTime) > 2000 )
            {
                tempTime = System.currentTimeMillis();
                Toast.makeText(MainActivity.this, "再点击一次退出应用", Toast.LENGTH_SHORT).show();
            }else
            {
                SharedPreferences sp = getSharedPreferences("text", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                //存储的数据都是 key-value形式
                editor.putBoolean("isLogin",false);
                editor.commit();
                finish();
                System.exit(0);//0表示正常退出，非0表示不正常
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    /**
     * 接口回调接收NewsFragment传来的值
     *
     * @param state
     */
    @Override
    public void toContralActivity(boolean state) {
        if (state) {
            mainDrawerLayout.openDrawer(lineatLayout);
        }
    }


}
