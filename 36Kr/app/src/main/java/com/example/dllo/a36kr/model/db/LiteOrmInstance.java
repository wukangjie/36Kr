package com.example.dllo.a36kr.model.db;

import com.example.dllo.a36kr.model.bean.CollectBean;
import com.example.dllo.a36kr.ui.app.MyApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 * 数据库操作单例类
 */
public class LiteOrmInstance {
    private static LiteOrmInstance instance;
    /**
     * 定义数据库的名字
     */
    private static final String DB_NAME = "collect.db";
    /**
     * LiteOrm对象
     */
    private LiteOrm liteOrm;
    private LiteOrmInstance(){
        liteOrm = LiteOrm.newSingleInstance(MyApp.getContext(),DB_NAME);
    }
    public static LiteOrmInstance getInstance(){
        if (instance == null){
            synchronized (LiteOrmInstance.class){
                if (instance == null){
                    instance = new LiteOrmInstance();
                }
            }
        }
        return instance;
    }

    /*****************增删改查*******************/
    //插入一条数据的方法
    public void insert(CollectBean cb){
        liteOrm.insert(cb);
    }
    //插入集合数据
    public void insert(List<CollectBean> cbs){
        liteOrm.insert(cbs);
    }
    /**
     * 查询所有
     */
    public List<CollectBean> queryAll(){
        return liteOrm.query(CollectBean.class);
    }

    /**
     * 按条件删除
     */
    public void deleteByPostId(String postId){
        WhereBuilder wb = new WhereBuilder(CollectBean.class,"postId = ?",new String[]{postId});
        liteOrm.delete(wb);
    }
    /**
     * 根据条件查询
     */
    public List<CollectBean> queryByPostId(String postId){
        QueryBuilder<CollectBean> qb = new QueryBuilder<>(CollectBean.class);
        qb.where("PostId = ?" , new Object[]{postId});
        return liteOrm.query(qb);
    }

    /**
     * 删除数据库所有的数据
     */
    public void deleteAll(){
        liteOrm.deleteAll(CollectBean.class);
    }



}
