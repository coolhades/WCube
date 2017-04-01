package com.hades.wcube;

import android.app.Application;

import com.hades.baselib.net.HttpClientManager;
import com.hades.baselib.net.RetrofitManager;
import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by Hades on 2017/3/26.
 */

public class ClientApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //网络
        HttpClientManager.getInstance().initClient(this);
        RetrofitManager.getInstance().bindHttpClient(HttpClientManager.getInstance().getmOkHttpClient(), this);

        /**
         * 仅仅是缓存Application的Context，不耗时
         */
        FileDownloader.init(getApplicationContext());

    }
}
