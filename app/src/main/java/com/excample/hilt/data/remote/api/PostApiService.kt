package com.excample.hilt.data.remote.api

import com.excample.hilt.data.models.PostModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostApiService {

    @GET("albums/1/photos")
    fun getPost(): Call<List<PostModel>>

    @POST("posts")
    fun sendPost(
        @Body post: PostModel
    ) : Call<PostModel>
}