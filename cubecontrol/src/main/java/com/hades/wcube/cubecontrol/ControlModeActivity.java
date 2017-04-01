package com.hades.wcube.cubecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hades.baselib.ui.NormalBaseActivity;
import com.hades.wcube.cubecontrol.fragments.ViewModeFragment;

/**
 * Created by Hades on 2017/3/26.
 * 资源浏览  控制模块
 * 使用了复用的Fragment
 */
public class ControlModeActivity extends NormalBaseActivity {

    ViewModeFragment viewModeFragment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_viewmode);
        init(savedInstanceState);
    }

    @Override
    protected void hideStatusBar() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        viewModeFragment = new ViewModeFragment();
        transaction.replace(R.id.ft_container, viewModeFragment)
                .commitAllowingStateLoss();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
