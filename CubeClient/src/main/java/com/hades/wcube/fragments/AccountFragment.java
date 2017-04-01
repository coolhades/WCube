package com.hades.wcube.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.hades.baselib.ui.NormalBaseFragment;
import com.hades.wcube.R;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Hades on 2017/3/26.
 */

public class AccountFragment extends NormalBaseFragment {
    private FancyButton mdCloud;
    private EditText edCloud;
    private FancyButton mdUser;
    private EditText tvName;
    private FancyButton btnStartconnect;

    private CheckBox mdCheck;
    private TextView mdTextview;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaneState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ft_account, container, false);
        init(v,savedInstaneState);
        return v;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        btnStartconnect = (FancyButton) view.findViewById(R.id.btn_startconnect);
        mdUser = (FancyButton) view.findViewById(R.id.md_user);
        mdUser.setCustomIconFont("iconfont.ttf");
        mdUser.setIconResource(getString(R.string.vr));

        tvName = (EditText) view.findViewById(R.id.tv_name);
        mdCloud = (FancyButton) view.findViewById(R.id.md_cloud);
        edCloud = (EditText) view.findViewById(R.id.ed_cloud);
        mdCheck = (CheckBox) view.findViewById(R.id.md_check);
        mdTextview = (TextView) view.findViewById(R.id.md_textview);
        mdTextview.setText("记住服务器信息");


    }

    @Override
    protected void initData() {

    }

    ConnectingFragment connectingFragment;

    @Override
    protected void initEvent() {
        mdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mdCheck.isChecked()){

                }
            }
        });



        btnStartconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                connectingFragment = new ConnectingFragment();
                transaction.setCustomAnimations(
                        R.anim.push_left_in,
                        R.anim.push_left_out,
                        R.anim.back_left_in,
                        R.anim.back_right_out);
                transaction.replace(R.id.ft_container, connectingFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });



    }
}
