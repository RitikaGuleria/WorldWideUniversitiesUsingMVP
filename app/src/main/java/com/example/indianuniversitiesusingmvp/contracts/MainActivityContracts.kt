package com.example.indianuniversitiesusingmvp.contracts

import com.example.indianuniversitiesusingmvp.network.model.UniversityDTO

//Communication between View-Presenter and Presenter-Model happens via an interface(also called Contract).

interface MainActivityContracts {

    interface View{
        fun onLoading()
        fun onSuccess(list:List<UniversityDTO>)
        fun onError(message:String)
    }

    interface Presenter{
        fun getUniversity(country:String)
        fun onDestroy()
    }

    interface Model{
        interface OnFinishListener{
            fun onLoading()
            fun onSuccess(list:List<UniversityDTO>)
            fun onError(message:String)
        }
        suspend fun fetchUniversity(onFinishListener : OnFinishListener,country:String)
    }
}