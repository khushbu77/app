package com.example.retrivedata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("get_results.php")
    Call<List<Growth>> getGrowthInfo();

}
