package com.hades.wcube.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hades.wcube.bean.EtChangeControl;
import com.hades.wcube.bean.EtStartLoop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Hades on 2017/3/28.
 */

public class BackGroundService extends Service {

    private IBinder binder = new ControlBind();

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    public class ControlBind extends Binder {
        BackGroundService getService() {
            return BackGroundService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(priority = 10)
    public void startLoop(EtStartLoop startLoop) {
        Log.d("BackGroundService", "startLoop");
        EtChangeControl changeControl = new EtChangeControl();
        changeControl.setFlag(2);
        EventBus.getDefault().post(changeControl);
    }




}
