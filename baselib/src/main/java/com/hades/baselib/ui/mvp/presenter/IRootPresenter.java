package com.hades.baselib.ui.mvp.presenter;


import com.hades.baselib.ui.mvp.interf.IRootView;

/**
 * Created by Hades on 16/10/8.
 */

public interface IRootPresenter<T extends IRootView>  {
    void attachView(T view);
    void start();
    void detachView();
}
