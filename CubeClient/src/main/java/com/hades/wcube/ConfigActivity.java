package com.hades.wcube;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.hades.baselib.ui.NormalBaseActivity;
import com.hades.wcube.fragments.AccountFragment;
import com.hades.wcube.fragments.ConnectedFragment;
import com.hades.wcube.fragments.ConnectingFragment;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Hades on 2017/3/26.
 */

public class ConfigActivity extends NormalBaseActivity {

    AccountFragment accountFragment;
    ConnectingFragment connectingFragment;
    ConnectedFragment connectedFragment;
    private FrameLayout ftContainer;
    private FancyButton btnViewmode;



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

        ftContainer = (FrameLayout) findViewById(R.id.ft_container);
        btnViewmode = (FancyButton) findViewById(R.id.btn_viewmode);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        accountFragment = new AccountFragment();
        transaction.setCustomAnimations(
                R.anim.push_left_in,
                R.anim.push_left_out);
        transaction.replace(R.id.ft_container, accountFragment)
                .commitAllowingStateLoss();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        btnViewmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
