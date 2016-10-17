package com.example.dllo.a36kr.model.bean;

/**
 * Created by dllo on 16/9/26.
 * 登录状态的实体类
 */
public class IsLoginBean {
    private boolean data;
    private String name;
    private String ImgUrl;

    public IsLoginBean(boolean data, String name, String imgUrl) {
        this.data = data;
        this.name = name;
        ImgUrl = imgUrl;
    }



    public IsLoginBean setData(boolean data,String name,String imgUrl) {
        this.name = name;
        ImgUrl = imgUrl;
        this.data = data;
        return this;
    }

    public boolean isData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return ImgUrl;
    }
}
