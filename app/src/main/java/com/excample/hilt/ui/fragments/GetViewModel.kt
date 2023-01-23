package com.excample.hilt.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.excample.hilt.data.models.PostModel
import com.excample.hilt.data.repositories.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetViewModel @Inject constructor(private val repository: PostsRepository): ViewModel(){

    private val _getLiveData = MutableLiveData<List<PostModel>>()
    val getLiveData: LiveData<List<PostModel>> = _getLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun getPosts(){
        repository.getPhoto(
            onSuccess = {
                _getLiveData.value = it
            },
            onFailure = {
                _errorLiveData.value = it
            }
        )
    }
}