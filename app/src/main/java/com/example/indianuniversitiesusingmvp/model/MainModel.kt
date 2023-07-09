package com.example.indianuniversitiesusingmvp.model

import com.example.indianuniversitiesusingmvp.contracts.MainActivityContracts
import com.example.indianuniversitiesusingmvp.network.api.APIService
import java.lang.Exception

class MainModel(private val apiService: APIService) : MainActivityContracts.Model {

    override suspend fun fetchUniversity(
        onFinishListener: MainActivityContracts.Model.OnFinishListener,
        country: String,
    )
    {
        onFinishListener.onLoading()
        try {
            val response = apiService.getUniversity(country)
            if(response.isSuccessful)
            {
                response.body()?.let {
                    onFinishListener.onSuccess(it)
                }
            }
            else
            {
                onFinishListener.onError(response.errorBody().toString())

            }
        }
        catch(e:Exception)
        {
            onFinishListener.onError(message = e.message.toString())
        }
    }
}