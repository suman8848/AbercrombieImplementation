package com.example.abercrombietestcode.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.example.abercrombietestcode.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Base Activity for all Activities
 */

public abstract class BaseActivity extends AppCompatActivity {

  @BindView(R.id.toolbar)
  @Nullable
  Toolbar mToolbar;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getContentView());
    ButterKnife.bind(this);
    onViewReady(savedInstanceState, getIntent());
  }

  protected void onViewReady(Bundle savedInstanceState, Intent intent) {
    resolveDaggerDependency();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  protected void resolveDaggerDependency() {}

  protected void setToolbar() {
    setSupportActionBar(mToolbar);
    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
  }

  protected abstract int getContentView();

}
