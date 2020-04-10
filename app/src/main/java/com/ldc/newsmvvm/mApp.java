package com.ldc.newsmvvm;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

public class mApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
    }
}
