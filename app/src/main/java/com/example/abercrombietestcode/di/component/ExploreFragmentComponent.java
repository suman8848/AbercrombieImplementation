package com.example.abercrombietestcode.di.component;

import com.example.abercrombietestcode.di.module.ExploreFragmentModule;
import com.example.abercrombietestcode.di.scope.PerActivity;
import com.example.abercrombietestcode.ui.fragments.ExploreFragment;

import dagger.Component;

/**
 * Components Delivers the objects contained on module
 * also can extend or add dependencies
 */
@PerActivity
@Component(modules = ExploreFragmentModule.class, dependencies = ApplicationComponent.class)
public interface ExploreFragmentComponent {
  void inject(ExploreFragment exploreFragment);
}
