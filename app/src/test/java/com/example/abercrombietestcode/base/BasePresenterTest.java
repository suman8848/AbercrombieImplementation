package com.example.abercrombietestcode.base;

import com.example.abercrombietestcode.mvp.view.BaseView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.disposables.Disposable;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BasePresenterTest {

  @Mock BaseView view;

  private BasePresenter testObject;

  @Before
  public void setUp() {
    testObject = new BasePresenter();
  }

  @Test
  public void getView() {
    testObject.mView = view;
    assertNotNull(testObject.getView());
  }

  @Test
  public void disposable_NotNull() {
    Disposable disposable = mock(Disposable.class);
    testObject.disposable(disposable);
    verify(disposable).dispose();
  }

  @Test
  public void disposable_Null() {
    Disposable disposable = mock(Disposable.class);
    testObject.disposable(null);
    verify(disposable, never()).dispose();
  }
}
