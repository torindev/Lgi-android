package com.torin_dev.lgi_android;

import android.app.Application;

import com.github.torindev.lgi_android.Lgi;


/**
 * Created by fatal on 24.09.18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // set logging only on debug mode
        Lgi.sLog = BuildConfig.DEBUG;

        // set tag ("fatal" - is default)
        Lgi.sTag = "fatal";
        Lgi.p();
    }
}
