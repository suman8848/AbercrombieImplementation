package com.example.abercrombietestcode.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * JsonPojo Model
 */

public class AbercrombieExploreResponses {

  @SerializedName("title")
  @Expose
  private String title;

  @SerializedName("backgroundImage")
  @Expose
  private String backgroundImage;

  @SerializedName("content")
  @Expose
  private List<Content> content = null;

  @SerializedName("promoMessage")
  @Expose
  private String promoMessage;

  @SerializedName("topDescription")
  @Expose
  private String topDescription;

  @SerializedName("bottomDescription")
  @Expose
  private String bottomDescription;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBackgroundImage() {
    return backgroundImage;
  }

  public void setBackgroundImage(String backgroundImage) {
    this.backgroundImage = backgroundImage;
  }

  public List<Content> getContent() {
    return content;
  }

  public void setContent(List<Content> content) {
    this.content = content;
  }

  public String getPromoMessage() {
    return promoMessage;
  }

  public void setPromoMessage(String promoMessage) {
    this.promoMessage = promoMessage;
  }

  public String getTopDescription() {
    return topDescription;
  }

  public void setTopDescription(String topDescription) {
    this.topDescription = topDescription;
  }

  public String getBottomDescription() {
    return bottomDescription;
  }

  public void setBottomDescription(String bottomDescription) {
    this.bottomDescription = bottomDescription;
  }



}
