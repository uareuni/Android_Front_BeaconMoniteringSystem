package com.example.kbpark.frontbeaconmonitor.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by KBPark on 2017. 1. 23..
 */

public interface GithubServiceSample {
    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

}
