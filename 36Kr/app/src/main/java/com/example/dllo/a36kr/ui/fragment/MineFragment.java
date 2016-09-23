package com.example.dllo.a36kr.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.ui.activity.UnLoginActivity;


/**
 * Created by dllo on 16/9/9.
 */
public class MineFragment extends AbsFragment implements View.OnClickListener {
    private RelativeLayout loginRLL;
    private boolean isLogin = false;
    private ImageView mSettingImg;
    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {
        loginRLL = byView(R.id.fragment_mine_login_rll);
        mSettingImg = byView(R.id.fragment_mine_setting_img);

    }

    @Override
    protected void initDatas() {
        loginRLL.setOnClickListener(this);
        mSettingImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.fragment_mine_login_rll:
            if (isLogin){

            }else {
                startActivity(new Intent(getActivity(),UnLoginActivity.class));
            }
            break;
            case R.id.fragment_mine_setting_img:

                break;
        }


    }
}
