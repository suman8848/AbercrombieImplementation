package com.example.abercrombietestcode.di.module;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Application level module contains all the object required
 */
@Module
public class ApplicationModule {

  private String mBaseUrl;
  private Context mContext;

  public ApplicationModule(Context context, String baseUrl) {

    this.mBaseUrl = baseUrl;
    this.mContext = context;
  }

  @Singleton
  @Provides
  GsonConverterFactory provideGsonConverterFactory() {
    return GsonConverterFactory.create();
  }

  @Singleton
  @Provides
  OkHttpClient provideOkHttpClient() {
    return new OkHttpClient.Builder()
        .connectTimeout(200, TimeUnit.SECONDS)
        .readTimeout(200, TimeUnit.SECONDS)
        .build();
  }

  @Singleton
  @Provides
  RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(
      OkHttpClient client,
      GsonConverterFactory gsonConverterFactory,
      RxJava2CallAdapterFactory rxJavaCallAdapterFactory) {

    return new Retrofit.Builder()
        .baseUrl(mBaseUrl)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJavaCallAdapterFactory)
        .client(client)
        .build();
  }

  @Provides
  @Singleton
  Context provideContext() {
    return mContext;
  }
}
