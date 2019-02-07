package com.example.abercrombietestcode.app;

import android.app.Application;

import com.example.abercrombietestcode.di.component.ApplicationComponent;
import com.example.abercrombietestcode.di.component.DaggerApplicationComponent;
import com.example.abercrombietestcode.di.module.ApplicationModule;

public class AbercrombieTestApp extends Application {

  private ApplicationComponent mApplicationComponent;

  public static final String BASE_URL = "https://www.abercrombie.com/";

  @Override
  public void onCreate() {
    super.onCreate();
    initializeApplicationComponent();
  }

  private void initializeApplicationComponent() {

    mApplicationComponent =
        DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this, BASE_URL))
            .build();
  }

  public ApplicationComponent getmApplicationComponent() {
    return mApplicationComponent;
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
  }
}
