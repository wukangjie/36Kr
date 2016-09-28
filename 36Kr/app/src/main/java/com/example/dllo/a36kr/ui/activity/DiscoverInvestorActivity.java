package com.example.dllo.a36kr.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.InvestorDiscoverActivityBean;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.ui.adapter.InvestorDiscoverActivityAdapter;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.google.gson.Gson;

import java.util.List;

public class DiscoverInvestorActivity extends AbsBaseActivity implements View.OnClickListener {
    private ListView listView;
    private InvestorDiscoverActivityAdapter adapter;
    private ImageView mBackImg;
    private TextView mTitleTv;

    @Override
    protected int setLayout() {
        return R.layout.activity_discover_investor;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.activity_discover_investor_listview);
        adapter = new InvestorDiscoverActivityAdapter(getApplicationContext());
        listView.setAdapter(adapter);
        mBackImg = byView(R.id.activity_discover_back_img);
        mTitleTv = byView(R.id.activity_discover_title_tv);

    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startRequest(AllContantValues.DISSCOVERINVESTOR, new VolleyReault() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                InvestorDiscoverActivityBean bean = gson.fromJson(resultStr,InvestorDiscoverActivityBean.class);
               List <InvestorDiscoverActivityBean.DataBean.DataBean1> data = bean.getData().getData();
                adapter.setDatas(data);
            }

            @Override
            public void failure() {

            }
        });
        mBackImg.setOnClickListener(this);
        mTitleTv.setText("寻找投资人");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_discover_back_img:
                finish();
                break;
        }
    }
}
