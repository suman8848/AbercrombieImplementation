package com.example.abercrombietestcode.constants;

public enum AppConstants {
  WEBVIEW_KEY("webview");

  private String value;

  AppConstants(String value) {
    this.value = value;
  }

  public String getKey() {
    return value;
  }
}
