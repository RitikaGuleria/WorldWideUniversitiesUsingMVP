package com.example.indianuniversitiesusingmvp.network.api

import com.example.indianuniversitiesusingmvp.network.model.UniversityDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("search")
    suspend fun getUniversity(
        @Query("country") country : String
    ) : Response<List<UniversityDTO>>
}