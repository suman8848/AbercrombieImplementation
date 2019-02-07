package com.example.abercrombietestcode.di.module;

import com.example.abercrombietestcode.webservice.AbercrombieSevices;
import com.example.abercrombietestcode.di.scope.PerActivity;
import com.example.abercrombietestcode.mvp.view.ExploreFragmentView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ExploreFragmentModule {

  ExploreFragmentView exploreFragmentView;

  public ExploreFragmentModule(ExploreFragmentView exploreFragmentView) {
    this.exploreFragmentView = exploreFragmentView;
  }

  @Provides
  @PerActivity
  public ExploreFragmentView providesExploreFragmentView() {
    return exploreFragmentView;
  }

  @Provides
  @PerActivity
  public AbercrombieSevices providesAbercrombieSevices(Retrofit retrofit) {
    return retrofit.create(AbercrombieSevices.class);
  }

}
