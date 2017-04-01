package com.hades.wcube.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hades.baselib.ui.NormalBaseFragment;
import com.hades.wcube.R;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Hades on 2017/3/26.
 */

public class ConnectingFragment extends NormalBaseFragment {
    private FancyButton btnBack;
    private TextView tvTitle;
    private FancyButton btnReload;
    ConnectedFragment connectedFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstaneState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ft_connecting, container, false);
        init(v,savedInstaneState);
        return v;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        btnBack = (FancyButton)view.findViewById(R.id.btn_back);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText("连接中...");
        btnReload = (FancyButton) view.findViewById(R.id.btn_reload);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                connectedFragment = new ConnectedFragment();
                transaction.setCustomAnimations(
                        R.anim.push_left_in,
                        R.anim.push_left_out,
                        R.anim.back_left_in,
                        R.anim.back_right_out);
                transaction.replace(R.id.ft_container, connectedFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            }
        });
    }
}
