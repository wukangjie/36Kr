package com.example.dllo.a36kr.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.IsLoginBean;
import com.example.dllo.a36kr.ui.activity.MainActivity;
import com.example.dllo.a36kr.ui.activity.UnLoginActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/9/23.
 * 登录Fragment
 */
public class LoginFragment extends AbsFragment {

    private ImageView qqLoginImg;
    private PlatformDb db;
    private EditText genderEt, passwordEt;
    private String gender;
    private String passWord;
    private Button loginBtn;
    private String name = "狂野少年";
    private String icon = "https://krid-assets.b0.upaiyun.com/uploads/user/avatar/788/e1217e9c-ee20-43ea-8b6d-1b21ccee369e.jpg!480";
    private EventBus mEventBus;
    private boolean isLogin = false;

    @Override
    protected int setLayout() {
        return R.layout.item_unlogin_login;
    }

    @Override
    protected void initViews() {
        qqLoginImg = byView(R.id.item_unlogin_qq);
        genderEt = byView(R.id.item_unlogin_account);
        passwordEt = byView(R.id.your_item_unlogin_password);
        loginBtn = byView(R.id.item_unlogin_login_btn);
        mEventBus = EventBus.getDefault();

    }

    @Override
    protected void initDatas() {
        qqLoginImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QQLogin();

            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LoginFragment", "genderEt.getText().toString().isEmpty():" + genderEt.getText().toString().isEmpty());
                Log.d("LoginFragment", "passwordEt.getText().toString().isEmpty():" + passwordEt.getText().toString().isEmpty());
                if (!genderEt.getText().toString().isEmpty() && !passwordEt.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();

                    isLogin = true;
//                    boolean data = isLogin;
//                    IsLoginBean bean = new IsLoginBean(data, name, icon);
//                    mEventBus.post(bean);

                    //创建Sp对象
                    //sp对象获取:通过Activity的get方法
                    //Fragemnt里:通过context
                    //参数一:文件名
                    //参数二:权限:全手机可读可写的权限不安全,谷歌推荐使用PRIVATE,只有当前app能访问
                    SharedPreferences sp = getActivity().getSharedPreferences("text", Context.MODE_PRIVATE);
                    //想要存储数据-需要使用操作类
                    //Editor 编辑者的意思 edit是编辑的意思
                    SharedPreferences.Editor editor = sp.edit();
                    //存储的数据都是 key-value形式
                    editor.putBoolean("isLogin",isLogin);
                    editor.putString("name",name);
                    editor.putString("icon",icon);

                    editor.commit();
                    Log.d("asd", "isLogin:" + isLogin);



                    getActivity().finish();
                } else if (genderEt.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "请输入账号", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "请输入密码", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void QQLogin() {

        //获取第三方平台
        Platform platform = ShareSDK.getPlatform(getContext(), QQ.NAME);
        //授权
        platform.authorize();
        //获取用户信息
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(getContext(), "完成", Toast.LENGTH_SHORT).show();
                //获取QQ的头像和名字
                db = platform.getDb();
                name = db.getUserName();
                icon = db.getUserIcon();
                gender = db.getExpiresTime() + "";
                passWord = db.getUserName();
                genderEt.setText(gender);
                Log.d("eee", "genderEt:" + genderEt);
                passwordEt.setText(passWord);
                Log.d("qqq", passWord);
                Log.d("qqq", gender);


            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(getContext(), "错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
