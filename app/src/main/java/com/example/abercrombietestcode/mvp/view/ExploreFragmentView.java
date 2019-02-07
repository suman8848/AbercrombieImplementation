package com.example.abercrombietestcode.mvp.view;

import com.example.abercrombietestcode.mvp.model.AbercrombieExploreResponses;

import java.util.List;

public interface ExploreFragmentView extends BaseView {

  void onShowDialog(String message);

  void onHideDialog();

  void sendExploreJsonData(List<AbercrombieExploreResponses> abercrombieExploreResponses);

  void onShowToast(String message);

  void onError(String errorMessage);
}
