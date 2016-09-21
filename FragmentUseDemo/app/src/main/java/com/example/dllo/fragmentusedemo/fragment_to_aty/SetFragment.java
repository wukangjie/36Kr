package com.example.dllo.fragmentusedemo.fragment_to_aty;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dllo.fragmentusedemo.R;

import static com.example.dllo.fragmentusedemo.R.id.beginning;
import static com.example.dllo.fragmentusedemo.R.id.fragment_set_changecolor_btn;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetFragment extends Fragment implements View.OnClickListener {
    private Button colorBtn,contentBtn,homeBtn,toSetBtn;

    /**
     * 定义接口对象
     * 这个借口就是Activity,也是Context
     * 因为多态
     * Activity实现了该接口后,具有多种数据形态
     * 具有多种数据类型,可以作为Context,Activity
     * 和该接口的实例对象
     */
    private IToContralAty iToContralAty;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /**
         * context也是Activity-Activity实现了接口
         * 他们就是一个内存空间,可以相互转型使用
         */
        iToContralAty = (IToContralAty) context;
    }

    public static SetFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SetFragment fragment = new SetFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        colorBtn = (Button) view.findViewById(R.id.fragment_set_changecolor_btn);
        contentBtn = (Button) view.findViewById(R.id.fragment_set_changecontent_btn);
        homeBtn = (Button) view.findViewById(R.id.fragment_set_changehomecontent_btn);
        toSetBtn = (Button) view.findViewById(R.id.fragment_set_toset_btn);
        colorBtn.setOnClickListener(this);
        contentBtn.setOnClickListener(this);
        homeBtn.setOnClickListener(this);
        toSetBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_set_changecolor_btn:

                //调用接口,传入颜色
                iToContralAty.onToControlAty(Color.BLUE,null,0);

                break;
            case R.id.fragment_set_changecontent_btn:
                if (iToContralAty != null){
                    iToContralAty.onToControlAty(0,"哈哈哈",0);
                }
                break;
            case R.id.fragment_set_changehomecontent_btn:
                iToContralAty.onToControlAty(0,"来自Set",999);
                break;
            case R.id.fragment_set_toset_btn:
                iToContralAty.onToControlAty(0,"来自Set",999);

                break;
        }
    }
}
