package com.fentury.testapp.api

import com.fentury.testapp.model.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Vadim on 17.11.2016.
 */

interface StoriesApi {
    @GET("v0/topstories.json")
    fun getTopStories(): Call<List<Int>>


    @GET("v0/item/{id}.json")
    fun getTopStore(@Path("id") id: Int): Call<Model>
}
