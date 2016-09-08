package com.example.dllo.a36kr.utils;

import android.widget.Toast;

import com.example.dllo.a36kr.ui.app.MyApp;

/**
 * Created by dllo on 16/9/8.
 * Toast的工具类
 */
public final class T {
    //final修饰,不能继承
    //私有方法,不能创建对象
    //锁死这个类
    private T(){}
    private static boolean isDebug = true;
    public static void shortMsg(String msg){
        if (isDebug){
            Toast.makeText(MyApp.getContext(),msg,Toast.LENGTH_SHORT).show();
        }
    }
    public static void longMsg(String msg){
        if (isDebug){
            Toast.makeText(MyApp.getContext(),msg,Toast.LENGTH_LONG).show();
        }
    }
}
