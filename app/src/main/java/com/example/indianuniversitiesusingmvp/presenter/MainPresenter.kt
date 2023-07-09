package com.example.indianuniversitiesusingmvp.presenter

import com.example.indianuniversitiesusingmvp.contracts.MainActivityContracts
import com.example.indianuniversitiesusingmvp.network.model.UniversityDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainPresenter(
    private val view : MainActivityContracts.View,
    private val model : MainActivityContracts.Model
) : MainActivityContracts.Presenter, MainActivityContracts.Model.OnFinishListener
{
    val scope = CoroutineScope(Dispatchers.IO)

    override fun getUniversity(country: String) {
//        TODO("Not yet implemented")

        scope.launch {
            model.fetchUniversity(onFinishListener = this@MainPresenter,country=country)
        }
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
        scope.cancel()
    }

    override fun onLoading() {
//        TODO("Not yet implemented")
        view.onLoading()
    }

    override fun onSuccess(list: List<UniversityDTO>) {
//        TODO("Not yet implemented")
        view.onSuccess(list)
    }

    override fun onError(message: String) {
//        TODO("Not yet implemented")
        view.onError(message)
    }
}