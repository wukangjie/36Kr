package com.example.dllo.a36kr.ui.app;

import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 16/9/8.
 * 当前应用,每个应用只有一个
 * Context 环境
 *
 * 注意:要在清单文件注册
 *
 */
public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this);
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
