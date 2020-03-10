package com.know.Virus.Interfaces;

import com.know.Virus.ModelClasses.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsFeatures {
    @GET("/v2/everything")
    Call<Example> getNews(@Query("q") String theme, @Query("pageSize") long pagesize, @Query("language") String language, @Query("sortBy") String sortBy, @Query("apiKey") String ApiKey);
}
