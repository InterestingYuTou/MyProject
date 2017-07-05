package com.yutou;

import android.app.Application;

/**
 * Created by samsung on 2017/4/6.
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
