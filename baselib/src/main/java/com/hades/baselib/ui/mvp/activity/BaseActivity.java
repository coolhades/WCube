package com.hades.baselib.ui.mvp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hades.baselib.ui.mvp.interf.IRootView;
import com.hades.baselib.ui.mvp.presenter.IRootPresenter;


/**
 * Created by Hades on 16/10/8.
 */

public abstract class BaseActivity<P extends IRootPresenter> extends AppCompatActivity implements IRootView {

    protected P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void init(Bundle savedInstanceState){
        initView(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            hideStatusBar();
        }
        mPresenter = onLoadPresenter();
        getPresenter().attachView(this);
        initData();
        initEvent();
        getPresenter().start();
    }




    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        super.onDestroy();
    }

    protected abstract P onLoadPresenter();
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initData();
    protected abstract void initEvent();
    protected abstract void hideStatusBar();

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
