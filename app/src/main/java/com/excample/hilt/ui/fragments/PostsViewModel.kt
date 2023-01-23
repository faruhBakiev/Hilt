package com.excample.hilt.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.excample.hilt.data.models.PostModel
import com.excample.hilt.data.repositories.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostsRepository) : ViewModel() {

    private val _postsLiveData = MutableLiveData<PostModel>()
    val postLiveData: LiveData<PostModel> = _postsLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun sendPost(
        PostAlbumId: Int,
        PostTitle: String,
        PostId: Int,
        PostUrl: String,
        PostThumbnailUrl: String
    ) {
        repository.sendPost(
            onSuccess = {
                _postsLiveData.value = it
            },
            onFailure = {

            },
            post = PostModel(PostAlbumId, PostId, PostTitle, PostUrl, PostThumbnailUrl)
        )
    }
}