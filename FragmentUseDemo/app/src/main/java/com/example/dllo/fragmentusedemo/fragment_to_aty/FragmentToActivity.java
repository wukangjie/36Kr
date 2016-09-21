package com.example.dllo.fragmentusedemo.fragment_to_aty;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.fragmentusedemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment向Activity传值
 */

public class FragmentToActivity extends AppCompatActivity implements IToContralAty {
    private TextView showTv;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private List<String> title ;
    private VpAdapter vpAdapter;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_to);
        showTv = (TextView) findViewById(R.id.show_tv);
        //功能:Aty控制Fragment,在Aty中刷新Fragment界面
        showTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击了事件改变HomeFragment界面的东西
                homeFragment.changeTextViewText("水水水水");
                viewPager.setCurrentItem(1);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vpAdapter = new VpAdapter(getSupportFragmentManager());
        //构造函数
        buildDatas();
    }

    private void buildDatas() {
        fragments = new ArrayList<>();
        homeFragment = HomeFragment.newInstance();

        fragments.add(homeFragment);
        fragments.add(SetFragment.newInstance());
        title = new ArrayList<>();
        title.add("首页");
        title.add("设置");
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 当前Activity实现接口
     * 多态:Activity,Context,IControlActivity
     * onAttach方法把其转换为Context,传入Fragment
     * 在Fragment里,把context转成接口使用
     *
     * 接口回调方法
     * @param color
     * @param str
     * @param position
     */

    @Override
    public void onToControlAty(int color, String str, int position) {
        if (color != 0){
            showTv.setTextColor(color);
        }
        if (str != null){
            showTv.setText(str);
        }
        //后2个Button
        //接到命令
        //Set发出命令,Activity接到回调,再去改变HomeFragment
        if (position == 999){
            //控制homeFragment
            //方法一:
            homeFragment.changeTextViewText(str);
            //跳转
            viewPager.setCurrentItem(0);
            //方法二: 缺点:增加耦合性
            //一个类不要和别的类有过多的牵扯关联
//            HomeFragment homeFragment = (HomeFragment) vpAdapter.getItem(0);
//            TextView textView = homeFragment.getHomeTv();
//            textView.setText(str);
        }
    }
}
