package com.example.anadministrator.day0823test;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 张祺钒
 * on2017/8/23.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
