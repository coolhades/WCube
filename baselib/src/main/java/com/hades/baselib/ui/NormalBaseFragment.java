package com.hades.baselib.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Hades on 2016/11/24.
 */

public abstract class NormalBaseFragment extends Fragment {


    public void init(View view, Bundle savedInstanceState){
        initView(view, savedInstanceState);
        initData();
        initEvent();
    }

    protected abstract void initView(View view, Bundle savedInstanceState);
    protected abstract void initData();
    protected abstract void initEvent();
}
