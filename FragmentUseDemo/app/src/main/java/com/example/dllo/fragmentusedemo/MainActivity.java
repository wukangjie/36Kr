package com.example.dllo.fragmentusedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dllo.fragmentusedemo.drawer_change_ui.NewsAllFragment;
import com.example.dllo.fragmentusedemo.drawer_change_ui.NewsFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1,btn2,btn3;
    private NewsFragment newsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsFragment = NewsFragment.newInstance();
        //显示新闻界面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_replace_view,newsFragment)
                .commit();
        btn1 = (Button) findViewById(R.id.drawer_btn1);
        btn2 = (Button) findViewById(R.id.drawer_btn2);
        btn3 = (Button) findViewById(R.id.drawer_btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawer_btn1:
//                Toast.makeText(this, "btn1:" + btn1, Toast.LENGTH_SHORT).show();
//                //发广播
//                Intent intent = new Intent();
//                intent.setAction("change");
//                //把替换成哪个界面的区分存储到intent
//                intent.putExtra("index",0);
//                sendBroadcast(intent);

                newsFragment.changeFragment(NewsAllFragment.newInstance("默认"));

                break;
            case R.id.drawer_btn2:
//                Toast.makeText(this, "btn2:" + btn2, Toast.LENGTH_SHORT).show();
//                //发广播
//                Intent intent1 = new Intent();
//                intent1.setAction("change");
//                //把替换成哪个界面的区分存储到intent
//                intent1.putExtra("index",1);
//                sendBroadcast(intent1);

                newsFragment.changeFragment(NewsAllFragment.newInstance("B轮后"));

                break;
            case R.id.drawer_btn3:
//                Toast.makeText(this, "btn3:" + btn3, Toast.LENGTH_SHORT).show();
//                //发广播
//                Intent intent2 = new Intent();
//                intent2.setAction("change");
//                //把替换成哪个界面的区分存储到intent
//                intent2.putExtra("index",2);
//                sendBroadcast(intent2);

                newsFragment.changeFragment(NewsAllFragment.newInstance("早期项目"));

                break;
        }
    }
}
