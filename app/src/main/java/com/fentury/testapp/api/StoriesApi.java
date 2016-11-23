package com.fentury.testapp.api;

import com.fentury.testapp.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Vadim on 17.11.2016.
 */

public interface StoriesApi {
    @GET("v0/topstories.json")
    Call<List<Integer>> getTopStories();

    @GET("v0/item/{id}.json")
    Call<Model> getTopStore(@Path("id") int id);
}

