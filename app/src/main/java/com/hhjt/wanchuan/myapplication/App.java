package com.hhjt.wanchuan.myapplication;

import android.app.Application;

import com.hhjt.wanchuan.myapplication.romateLog.Log;

/**
 * Created by Administrator
 * on 2019/11/1 11:42.
 * desc:
 *
 * @author Administrator
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Log.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.destory();
    }
}
