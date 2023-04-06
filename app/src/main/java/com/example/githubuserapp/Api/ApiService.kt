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