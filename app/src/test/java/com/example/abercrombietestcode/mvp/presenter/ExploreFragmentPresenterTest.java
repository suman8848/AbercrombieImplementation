package com.example.abercrombietestcode.mvp.presenter;

import com.example.abercrombietestcode.webservice.AbercrombieSevices;
import com.example.abercrombietestcode.mvp.model.AbercrombieExploreResponses;
import com.example.abercrombietestcode.mvp.view.ExploreFragmentView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExploreFragmentPresenterTest {

  @Mock private AbercrombieSevices abercrombieservice;
  @Mock private ExploreFragmentView exploreFragmentView;

  private ExploreFragmentPresenter presenter;

  @Before
  public void setup() {
    presenter = new ExploreFragmentPresenter(abercrombieservice);
    presenter.setView(exploreFragmentView);
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
  }

  @Test
  public void test_GetAbercrombieExploreResponses_WhenExploreTabOpen() {
    List<AbercrombieExploreResponses> mockData = new ArrayList<>();
    when(abercrombieservice.getAbercrombieExploreData()).thenReturn(Observable.just(mockData));

    presenter.getAbercrombieExploreResponses();
    verify(exploreFragmentView).onShowDialog(eq("Please Wait"));
    verify(exploreFragmentView).sendExploreJsonData(eq(mockData));
    verify(exploreFragmentView, times(2)).onHideDialog();
  }

  @Test
  public void test_GetAbercrombieExploreResponses_WhenExploreTabOpenError() {
    List<AbercrombieExploreResponses> mockData = new ArrayList<>();
    when(abercrombieservice.getAbercrombieExploreData())
        .thenReturn(Observable.error(new Throwable()));
    presenter.getAbercrombieExploreResponses();
    verify(exploreFragmentView).onShowDialog(eq("Please Wait"));
    verify(exploreFragmentView, never()).sendExploreJsonData(eq(mockData));
  }
}
