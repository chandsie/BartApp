package com.shreyaschand.bart;

import android.app.Application;

public class BartApplication extends Application{

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .build();
    }

    public AppComponent getAppComponent() {
        return component;
    }
}
