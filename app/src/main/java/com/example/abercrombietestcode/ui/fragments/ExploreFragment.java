package com.example.abercrombietestcode.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.abercrombietestcode.R;
import com.example.abercrombietestcode.base.BaseFragment;
import com.example.abercrombietestcode.di.component.DaggerExploreFragmentComponent;
import com.example.abercrombietestcode.di.module.ExploreFragmentModule;
import com.example.abercrombietestcode.mvp.model.AbercrombieExploreResponses;
import com.example.abercrombietestcode.mvp.presenter.ExploreFragmentPresenter;
import com.example.abercrombietestcode.mvp.view.ExploreFragmentView;
import com.example.abercrombietestcode.ui.adapters.RecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ExploreFragment extends BaseFragment implements ExploreFragmentView {

  @BindView(R.id.explore_recycler)
  RecyclerView mRecyclerView;

  @Inject ExploreFragmentPresenter exploreFragmentPresenter;

  private RecyclerViewAdapter adapter;

  @Override
  protected void onViewReadyFragment(Bundle savedInstanceState, View view, Intent intent) {
    super.onViewReadyFragment(savedInstanceState, view, intent);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setItemViewCacheSize(20);
    mRecyclerView.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    if (exploreFragmentPresenter != null) {
      exploreFragmentPresenter.getAbercrombieExploreResponses();
    }
  }

  @Override
  public int getContentView() {
    return R.layout.fragment_explore;
  }

  @Override
  protected void resolveDaggerDependency() {
    super.resolveDaggerDependency();
    DaggerExploreFragmentComponent.builder()
        .applicationComponent(getApplicationComponent())
        .exploreFragmentModule(new ExploreFragmentModule(this))
        .build()
        .inject(this);
  }

  @Override
  public void onShowDialog(String message) {
    showDialog(message);
  }

  @Override
  public void onHideDialog() {
    hideDialog();
  }

  @Override
  public void sendExploreJsonData(List<AbercrombieExploreResponses> abercrombieExploreResponses) {
    adapter =
        new RecyclerViewAdapter(
            getActivity(),
            abercrombieExploreResponses,
            item -> {
              showToast(getActivity(), "" + item.getTitle());
            });
    mRecyclerView.setAdapter(adapter);
    mRecyclerView.setNestedScrollingEnabled(false);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void onShowToast(String message) {
    showToast(getContext().getApplicationContext(), message);
  }

  @Override
  public void onError(String errorMessage) {}

  @Override
  public void onDestroy() {
    super.onDestroy();
    exploreFragmentPresenter.tearDown();
  }
}
