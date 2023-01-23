package com.excample.hilt.di

import com.excample.hilt.data.remote.RetrofitClient
import com.excample.hilt.data.remote.api.PostApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

//   @Provides
    //   @Singleton
    //  fun provideRetrofitClient(): RetrofitClient {
    //      return RetrofitClient()

    @Provides
    @Singleton
    fun providePostsApiService(): PostApiService {
        return retrofitClient.postsApiService
    }
}