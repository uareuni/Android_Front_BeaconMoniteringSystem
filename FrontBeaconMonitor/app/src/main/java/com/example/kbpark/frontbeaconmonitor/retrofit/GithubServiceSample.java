package com.example.kbpark.frontbeaconmonitor.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by KBPark on 2017. 1. 23..
 */

public interface GithubServiceSample {
    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @POST("http://echo.jsontest.com/insert-key-here/insert-value-here/key/value")
    Call<Task> createTask(@Body Task task);
}
