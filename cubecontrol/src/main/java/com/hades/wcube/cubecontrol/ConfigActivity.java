package com.hades.wcube.cubecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hades.baselib.ui.NormalBaseActivity;
import com.hades.wcube.cubecontrol.fragments.AccountFragment;

/**
 * Created by Hades on 2017/3/26.
 */

public class ConfigActivity extends NormalBaseActivity {

    AccountFragment accountFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_config);
        init(savedInstanceState);
    }

    @Override
    protected void hideStatusBar() {}

    @Override
    protected void initView(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        accountFragment = new AccountFragment();
        transaction.replace(R.id.ft_container, accountFragment)
                .commitAllowingStateLoss();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
