package com.example.abercrombietestcode.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.abercrombietestcode.R;
import com.example.abercrombietestcode.constants.AppConstants;
import com.example.abercrombietestcode.base.BaseActivity;

import butterknife.BindView;

/** Activity for opening the website using webview */
public class AbercrombieWebView extends BaseActivity {

  @BindView(R.id.webview)
  WebView webView;

  @Override
  protected void onViewReady(Bundle savedInstanceState, Intent intent) {
    super.onViewReady(savedInstanceState, intent);
    String url = getIntent().getStringExtra(AppConstants.WEBVIEW_KEY.getKey());
    setToolbar();
    getSupportActionBar().setTitle("Shop");
    webView.setWebViewClient(new AbrocrombieBrowser());
    webView.getSettings().setLoadsImagesAutomatically(true);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setUseWideViewPort(true);
    webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    webView.loadUrl(url);
  }

  @Override
  protected int getContentView() {
    return R.layout.activity_webview;
  }

  private class AbrocrombieBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      view.loadUrl(url);
      return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
      super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
      super.onReceivedError(view, request, error);
      Toast.makeText(getApplicationContext(), "Cannot load page", Toast.LENGTH_SHORT).show();
    }
  }
}
