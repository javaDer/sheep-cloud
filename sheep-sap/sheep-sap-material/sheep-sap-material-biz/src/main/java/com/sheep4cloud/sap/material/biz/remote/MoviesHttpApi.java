package com.sheep4cloud.sap.material.biz.remote;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;

import java.util.concurrent.TimeUnit;

@RetrofitClient(baseUrl = "${baseUrl.movies}", connectTimeoutMs = 20000, readTimeoutMs = 80000, writeTimeoutMs = 80000)
public interface MoviesHttpApi {
    @GET("movies.json")
    Response<ResponseBody> getMoviesData();
}
