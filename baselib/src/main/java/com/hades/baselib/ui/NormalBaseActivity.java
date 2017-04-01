package com.hades.baselib.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hades on 2016/11/21.
 */

public abstract class NormalBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public void init(Bundle savedInstanceState) {
        initView(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            hideStatusBar();
        }

        initData();
        initEvent();
    }

    protected abstract void hideStatusBar();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void initEvent();

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
