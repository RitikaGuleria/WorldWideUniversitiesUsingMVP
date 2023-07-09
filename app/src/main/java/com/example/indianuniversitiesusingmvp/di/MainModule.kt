package com.example.indianuniversitiesusingmvp.di

import com.example.indianuniversitiesusingmvp.network.api.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
object MainModule {
    @Provides
    fun provideAPIService() : APIService{
        return Retrofit.Builder().baseUrl("http://universities.hipolabs.com/").addConverterFactory(
            GsonConverterFactory.create()).build().create(APIService::class.java)
    }
}