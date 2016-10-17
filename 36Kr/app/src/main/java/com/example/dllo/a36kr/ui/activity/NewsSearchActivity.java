package com.example.dllo.a36kr.ui.activity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.model.bean.NewsActivityPopBean;
import com.example.dllo.a36kr.model.bean.NewsSearchBean;
import com.example.dllo.a36kr.model.net.VolleyInstance;
import com.example.dllo.a36kr.model.net.VolleyReault;
import com.example.dllo.a36kr.ui.adapter.NewsFragmentAdapter;
import com.example.dllo.a36kr.ui.adapter.NewsSearchAdapter;
import com.example.dllo.a36kr.utils.AllContantValues;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.List;

public class NewsSearchActivity extends AbsBaseActivity implements View.OnClickListener {
    private ListView listView;
    private NewsSearchAdapter adapter;
    private EditText editText;
    private String search;
    private PopupWindow mPopupWindow;
    private RelativeLayout relativeLayout;
    private ImageView closeImg;
    private List<NewsSearchBean.DataBean.DataBean1> data;
    private NewsSearchBean bean;
    private TextView cancelTv;

    @Override
    protected int setLayout() {
        return R.layout.activity_news_search;
    }

    @Override
    protected void initViews() {
        editText = byView(R.id.activity_news_search_et);
        relativeLayout = byView(R.id.activity_news_search_relativelayout);
        closeImg = byView(R.id.activity_news_search_pwd_clear);
        adapter = new NewsSearchAdapter(getApplicationContext());
        cancelTv = byView(R.id.activity_news_search_cancel);
    }

    @Override
    protected void initDatas() {
        closeImg.setVisibility(View.GONE);
        editText.addTextChangedListener(new EditChangedListener());
        closeImg.setOnClickListener(this);
        cancelTv.setOnClickListener(this);

    }
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_news_search_pop, null);
        listView = (ListView) contentView.findViewById(R.id.activity_news_search_pop_listview);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        mPopupWindow.setContentView(contentView);
        listView.setAdapter(adapter);
        mPopupWindow.showAsDropDown(relativeLayout);
        VolleyInstance.getInstance().startRequest(AllContantValues.ACTIVITYNEWSSEARCHLEFT + search +AllContantValues.ACTIVITYNEWSSEARCHRIGHT, new VolleyReault() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                bean = gson.fromJson(resultStr, NewsSearchBean.class);
                data = bean.getData().getData();
                adapter.setDatas(data);

            }

            @Override
            public void failure() {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),NewsFragmentActivity.class);
                SimpleDateFormat format = new SimpleDateFormat("MM-dd-HH:mm");//将时间转换为正常显示
                String publishTime = format.format(data.get(position ).getPublishTime());
                intent.putExtra("publishTime", publishTime + "");
                intent.putExtra("postId", data.get(position ).getFeedId());
                intent.putExtra("typeTv",data.get(position).getColumnName());
                startActivity(intent);
            }
        });


//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.alpha = 1f;
//        getWindow().setAttributes(params);
//        contentView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                closePopupWindow();
//                return false;
//            }
//        });
    }

    private void closePopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.alpha = 1f;
            getWindow().setAttributes(params);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_news_search_pwd_clear:
                editText.setText(null);
                break;
            case R.id.activity_news_search_cancel:
                finish();
                break;
        }
    }

    private class EditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            if (!editText.getText().toString().isEmpty()&& mPopupWindow.isShowing()){
                mPopupWindow.dismiss();
                closeImg.setVisibility(View.VISIBLE);
            }else {
                closeImg.setVisibility(View.GONE);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


        }

        @Override
        public void afterTextChanged(Editable s) {
            search = s.toString();
            Log.d("EditChangedListener", "search.length():" + search.length());
            if (search.length() > 0){
                showPopupWindow();
                closeImg.setVisibility(View.VISIBLE);
            }else {
                closeImg.setVisibility(View.GONE);
            }
           

        }
    }
}
