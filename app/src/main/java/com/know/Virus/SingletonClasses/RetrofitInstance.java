package com.know.Virus.SingletonClasses;

import com.know.Virus.Utils.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
   private static Retrofit retrofit =null;
   public static Retrofit getRetrofit(){
       if (retrofit == null){
           retrofit = new Retrofit.Builder().baseUrl(Utils.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

       }
       return retrofit;
   }
}
