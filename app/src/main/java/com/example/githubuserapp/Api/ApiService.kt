package com.example.githubuserapp.Api

import com.example.githubuserapp.GithubResponse
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
}