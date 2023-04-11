package com.example.githubuserapp.Api

import com.example.githubuserapp.GithubResponse
import com.example.githubuserapp.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users?")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q") q: String
    ): Call<GithubResponse>

    @GET("/users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("/users/{username}/followers")
    suspend fun getFollowerUser(
        @Path("username") username: String
    ): List<ItemsItem>

    @GET("/users/{username}/following")
    suspend fun getFollowingUser(
        @Path("username") username: String
    ): List<ItemsItem>
}