package com.example.dllo.a36kr.model.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.a36kr.ui.app.MyApp;

/**
 * Created by dllo on 16/9/9.
 * volley使用单例类
 */
public class VolleyInstance {
    /**
     * 什么是单例
     *
     * 想要使用时,就来调用这个单独的对象,而不是每次都new新的
     * 节省内存,避免多线程访问对象冲突
     *
     * 哪些部分需要单例:线程池,网络,数据库,sp,下载
     *
     * 常用的命名:XXInstance
     */
    /**
     * 单例的写法:(双重校验锁)
     * 1.私有化构造方法:外部不能调用构造方法随意的创建对象
     * 2.对外提供获取对象的方法
     * (1).定义静态当前类对象
     * (2).对外提供获取方法:进行单例判断
     *
     */
    //2-1
    private static VolleyInstance instance;
    //定义请求队列
    private RequestQueue requestQueue;

    //1.私有构造方法
    private VolleyInstance(){
        //初始化请求队列
        requestQueue = Volley.newRequestQueue(MyApp.getContext());

    }
    //2-2
    public static VolleyInstance getInstance(){
        //如果该对象是空
        if (instance == null){
            //全部线程同步扫描
            synchronized (VolleyInstance.class){
                //如果该对象还是null
                if (instance == null){
                    instance = new VolleyInstance();
                }
            }
        }
        return instance;


    }
    /*****************************************************/
    //对外提供请求方法
    public void startRequest(String url, final VolleyReault reault){
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //如果请求成功,将返回数据存储到接口里
                reault.success(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //请求失败,通过接口通知调用者失败
                reault.failure();

            }
        });
        requestQueue.add(sr);
    }

}
