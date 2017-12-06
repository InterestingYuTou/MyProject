package com.yutou;

import android.app.Application;

/**
 * Created by admin on 2016/2/26.
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

}
