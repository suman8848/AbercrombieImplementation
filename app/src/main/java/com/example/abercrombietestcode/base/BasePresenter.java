package com.example.abercrombietestcode.base;

import android.support.annotation.VisibleForTesting;

import com.example.abercrombietestcode.mvp.view.BaseView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Base Presenter for all presenter gives the view
 * @param <V>
 */
public class BasePresenter<V extends BaseView> {

  @Inject
  V mView;

  protected V getView() {
    return mView;
  }

  protected void disposable(Disposable disposable) {
    if (disposable != null) disposable.dispose();
  }

  protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
    observable
        .subscribeOn(Schedulers.single())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer);
  }

  @VisibleForTesting
  public void setView(V exploreFragmentView) {
    this.mView = exploreFragmentView;
  }
}
