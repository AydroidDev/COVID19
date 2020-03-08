package com.Corona.Virus.Interfaces;

import com.Corona.Virus.ModelClasses.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsFeatures {
    @GET("/v2/everything")
    Call<List<Article>> getNews(@Query("q") String theme,@Query("pageSize") long pagesize,@Query("language") String language,@Query("sortBy") String sortBy,@Query("apiKey") String ApiKey);
}
