package com.hades.wcube.cubecontrol;

import android.app.Application;

import com.hades.baselib.net.HttpClientManager;
import com.hades.baselib.net.RetrofitManager;

/**
 * Created by Hades on 2017/3/26.
 */

public class ControlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //网络
        HttpClientManager.getInstance().initClient(this);
        RetrofitManager.getInstance().bindHttpClient(HttpClientManager.getInstance().getmOkHttpClient(), this);


    }
}
