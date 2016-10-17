package com.example.dllo.a36kr.model.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/10/9.
 * 收藏的实体类
 */
@Table("collection")
public class CollectBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String postId;
    private String imgUrl;
    private String contentTv;
    private String nameTv;
    private String timeTv;
    private String typeTv;
    private boolean isLike;

    public CollectBean() {
    }

    public CollectBean(int id, String postId, String imgUrl, String contentTv, String nameTv, String timeTv, String typeTv, boolean isLike) {
        this.id = id;
        this.postId = postId;
        this.imgUrl = imgUrl;
        this.contentTv = contentTv;
        this.nameTv = nameTv;
        this.timeTv = timeTv;
        this.typeTv = typeTv;
        this.isLike = isLike;
    }


    public CollectBean(String postId, String imgUrl, String contentTv, String nameTv, String timeTv, String typeTv, boolean isLike) {
        this.postId = postId;
        this.imgUrl = imgUrl;
        this.contentTv = contentTv;
        this.nameTv = nameTv;
        this.timeTv = timeTv;
        this.typeTv = typeTv;
        this.isLike = isLike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContentTv() {
        return contentTv;
    }

    public void setContentTv(String contentTv) {
        this.contentTv = contentTv;
    }

    public String getNameTv() {
        return nameTv;
    }

    public void setNameTv(String nameTv) {
        this.nameTv = nameTv;
    }

    public String getTimeTv() {
        return timeTv;
    }

    public void setTimeTv(String timeTv) {
        this.timeTv = timeTv;
    }

    public String getTypeTv() {
        return typeTv;
    }

    public void setTypeTv(String typeTv) {
        this.typeTv = typeTv;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
