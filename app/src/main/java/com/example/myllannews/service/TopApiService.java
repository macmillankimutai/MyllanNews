package com.example.myllannews.service;

import com.example.myllannews.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopApiService {
    @GET("top-headlines")
    Call<ResponseModel> getTopHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);
}
