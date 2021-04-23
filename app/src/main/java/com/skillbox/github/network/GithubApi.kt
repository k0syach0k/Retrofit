package com.skillbox.github.network

import com.skillbox.github.models.Repository
import com.skillbox.github.models.UserProfile
import retrofit2.Call
import retrofit2.http.*

interface GithubApi {

    @GET("/user")
    fun getUserInfo(): Call<UserProfile>

    @GET("/repositories")
    fun getUserRepositories(): Call<List<Repository>>

    @GET("/user/starred/{owner}/{repo}")
    fun getStarState(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Boolean>

    @PUT("/user/starred/{owner}/{repo}")
    fun starRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Boolean>

    @DELETE("/user/starred/{owner}/{repo}")
    fun unStarRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Boolean>
}
