package com.example.retrivedata;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.43.45/android/v1/get_results.php";

    private static Retrofit retrofit;
    public static Retrofit getApiClient(){

        if(retrofit==null){

            retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }

        return retrofit;

    }

}
