package com.example.multinote.Data;

import android.app.Application;

import com.example.multinote.Data.DataLocalManager;

public class Init extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
