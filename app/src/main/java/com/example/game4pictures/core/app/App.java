package com.example.game4pictures.core.app;

import android.app.Application;

import com.example.game4pictures.core.Cache.GameCache;

public class App extends Application {

    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        GameCache.init(this);
    }
}
