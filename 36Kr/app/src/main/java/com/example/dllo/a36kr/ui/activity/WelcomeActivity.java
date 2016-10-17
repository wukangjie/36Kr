package com.example.dllo.a36kr.ui.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.squareup.picasso.Picasso;

public class WelcomeActivity extends AbsBaseActivity {
    private ImageView showImg;
    private TextView jumpTv;
    private Handler handler;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;


    }

    @Override
    protected void initViews() {
        showImg = byView(R.id.welcome_img);
        jumpTv = byView(R.id.weclome_jumptv);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent( getApplication(),MainActivity.class  ));
                finish();
            }
        } , 3000);

    }

    @Override
    protected void initDatas() {
//        jumpTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent( getApplication(),MainActivity.class  ));
//                finish();
//            }
//        });



    }
}
