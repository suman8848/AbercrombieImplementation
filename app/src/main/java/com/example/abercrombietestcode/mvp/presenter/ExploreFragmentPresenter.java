package com.example.abercrombietestcode.mvp.presenter;

import com.example.abercrombietestcode.webservice.AbercrombieSevices;
import com.example.abercrombietestcode.base.BasePresenter;
import com.example.abercrombietestcode.mvp.model.AbercrombieExploreResponses;
import com.example.abercrombietestcode.mvp.view.ExploreFragmentView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/** Presenter handles network calls and some backend process deliver required class from fragment */
public class ExploreFragmentPresenter extends BasePresenter<ExploreFragmentView> {

  private AbercrombieSevices abercrombieSevices;
  private Disposable abercrombieExploreDisposable;

  @Inject
  ExploreFragmentPresenter(AbercrombieSevices abercrombieSevices) {
    this.abercrombieSevices = abercrombieSevices;
  }

  // gets the data from the network api call
  public void getAbercrombieExploreResponses() {
    getView().onShowDialog("Please Wait");
    subscribe(
        abercrombieSevices.getAbercrombieExploreData(),
        new Observer<List<AbercrombieExploreResponses>>() {

          @Override
          public void onSubscribe(Disposable d) {
            abercrombieExploreDisposable = d;
          }

          @Override
          public void onNext(List<AbercrombieExploreResponses> abercrombieExploreResponses) {
            getView().onHideDialog();
            if (abercrombieExploreResponses != null) {
              getView().sendExploreJsonData(abercrombieExploreResponses);
            }
          }

          @Override
          public void onError(Throwable e) {
            getView().onHideDialog();
          }

          @Override
          public void onComplete() {
            getView().onHideDialog();
          }
        });
  }

  // disposing rx stream from fragment
  public void tearDown() {
    disposable(abercrombieExploreDisposable);
  }
}
