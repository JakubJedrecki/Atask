package com.example.task.interviewtask;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
    }

    public MyApplication getApplicationInstance() {
        return INSTANCE;
    }

}
