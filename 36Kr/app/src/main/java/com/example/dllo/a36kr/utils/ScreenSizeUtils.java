package com.example.dllo.a36kr.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 16/9/13.
 * 获取屏幕的宽高
 */
public class ScreenSizeUtils {
    public static int getSreenWidth(Context context){
        //获取窗口管理者
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        //将屏幕的尺寸设置给 显示尺寸类
        manager.getDefaultDisplay().getMetrics(metrics);
        //返回屏幕宽度
        return metrics.widthPixels;
    }
    public static int getSreenHeight(Context context){
        //获取窗口管理者
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        //将屏幕的尺寸设置给 显示尺寸类
        manager.getDefaultDisplay().getMetrics(metrics);
        //返回屏幕gao度
        return metrics.heightPixels;
    }
    //简单整合

    /**
     * @param state 状态
     * @return 1 返回宽
     * 2 返回高
     * 3.默认返回高
     */

    public static int getSreenSize(Context context, int state) {
        //获取窗口管理者
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        //将屏幕的尺寸设置给 显示尺寸类
        manager.getDefaultDisplay().getMetrics(metrics);
        //返回屏幕gao度
        switch (state) {
            case 1:
                return metrics.widthPixels;
            case 2:
                return metrics.heightPixels;
            default:
                return metrics.heightPixels;
        }
    }

    //再次整合
    //枚举:整型常量
    //一般用枚举来规定一些状态
    //如横屏 竖屏 全屏 窗口化
    //如播放 暂停 停止
    //如宽 高
    //关键字 enum, 也是一种数据类型,就是一个类
    public enum ScreenState {
        WIDTH, HEIGHT
    }

    public static int getSreenSize(Context context, ScreenState state) {
        //获取窗口管理者
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        //将屏幕的尺寸设置给 显示尺寸类
        manager.getDefaultDisplay().getMetrics(metrics);
        //返回屏幕gao度
        if (state.equals(ScreenState.WIDTH)) {
            return metrics.widthPixels;
        } else if (state.equals(ScreenState.HEIGHT)) {
            return metrics.heightPixels;
        }
        return metrics.heightPixels;
    }
}
