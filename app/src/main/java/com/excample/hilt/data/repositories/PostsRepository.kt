package com.excample.hilt.data.repositories

import com.excample.hilt.data.models.PostModel
import com.excample.hilt.data.remote.api.PostApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostsRepository @Inject constructor(private val apiService: PostApiService) {

    fun getPhoto(
        onSuccess: (post: List<PostModel>) -> Unit,
        onFailure: (message: String) -> Unit,
    ) {
        apiService.getPost().enqueue(object : Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {
                response.body()?.let {
                    onSuccess(it)
                }
            }

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                t.localizedMessage?.let {
                    onFailure(it)
                }
            }

        })
    }

    fun sendPost(
        onSuccess: (post: PostModel) -> Unit,
        onFailure: (message: String) -> Unit,
        post: PostModel
    ) {
        apiService.sendPost(post).enqueue(object : Callback<PostModel> {
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                response.body()?.let {
                    onSuccess(it)
                }
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                t.localizedMessage?.let {
                    onFailure(it)
                }
            }
        })
    }
}