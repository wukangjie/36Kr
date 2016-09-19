package com.example.dllo.fragmentusedemo.fragment_to_aty;


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
                break;
            case R.id.fragment_set_changecontent_btn:
                break;
            case R.id.fragment_set_changehomecontent_btn:
                break;
            case R.id.fragment_set_toset_btn:
                break;
        }
    }
}
