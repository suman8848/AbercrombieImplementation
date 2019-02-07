package com.example.abercrombietestcode.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.abercrombietestcode.app.AbercrombieTestApp;
import com.example.abercrombietestcode.di.component.ApplicationComponent;

import java.util.Objects;

import butterknife.ButterKnife;

/**
 * BaseFragment for all Fragments
 */

public abstract class BaseFragment extends Fragment {

  private ProgressDialog mProgressDialog;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(
          @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(getContentView(), container, false);
    ButterKnife.bind(this, view);
    Log.e("BaseFrag", "Test");
    onViewReadyFragment(savedInstanceState, view, Objects.requireNonNull(getActivity()).getIntent());
    return view;
  }

  @CallSuper
  protected void onViewReadyFragment(Bundle savedInstanceState, View view, Intent intent) {
    resolveDaggerDependency();
  }

  public abstract int getContentView();

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  protected void showDialog(String message) {
    if (mProgressDialog == null) {
      mProgressDialog = new ProgressDialog(getActivity());
      mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      mProgressDialog.setCancelable(false);
    }
    mProgressDialog.setMessage(message);
    mProgressDialog.show();
  }

  protected void hideDialog() {
    if (mProgressDialog != null && mProgressDialog.isShowing()) {
      mProgressDialog.dismiss();
    }
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((AbercrombieTestApp) Objects.requireNonNull(getActivity()).getApplication()).getmApplicationComponent();
  }

  protected void resolveDaggerDependency() {}

  protected void showToast(Context context, String message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }

}
