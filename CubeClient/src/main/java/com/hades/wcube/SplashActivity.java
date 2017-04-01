package com.hades.wcube;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.hades.baselib.ui.NormalBaseActivity;

/**
 * Created by Hades on 2017/3/26.
 */

public class SplashActivity extends NormalBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);
        init(savedInstanceState);
    }

    @Override
    protected void hideStatusBar() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
//        SplashActivity.this.finish();
        new Thread(new Runnable(){

            @Override
            public void run() {
                SystemClock.sleep(3000);
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, ConfigActivity.class));
                SplashActivity.this.finish();
            }
        }).start();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }


}
