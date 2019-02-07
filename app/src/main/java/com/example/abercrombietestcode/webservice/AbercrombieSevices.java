package com.example.abercrombietestcode.webservice;

import com.example.abercrombietestcode.mvp.model.AbercrombieExploreResponses;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface AbercrombieSevices {
  @Headers({"Accept: application/json", "User-Agent: Abercrombie"})
  @GET("anf/nativeapp/qa/codetest/codeTest_exploreData.json")
  Observable<List<AbercrombieExploreResponses>> getAbercrombieExploreData();
}
