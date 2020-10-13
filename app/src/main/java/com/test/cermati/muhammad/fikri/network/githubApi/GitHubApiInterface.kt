package com.test.cermati.muhammad.fikri.network.githubApi

import com.test.cermati.muhammad.fikri.githubApi.RateLimitResponse
import com.test.cermati.muhammad.fikri.githubApi.SearchUserResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/*
* Created by Muhammad Fikri on 11/10/2020
* */
interface GitHubApiInterface {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/search/users")
    fun searchUsers(
        @Query ("q") users : String,
        @Query ("page") page : Int = 1,
        @Query ("per_page") perPage : Int = 10) : Call<SearchUserResponses>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/rate_limit")
    fun rateLimit() : Call<RateLimitResponse>
}