package com.gurbuz.finalapp.Api.ApiRepository

import com.gurbuz.finalapp.Api.Model.Currency
import com.gurbuz.finalapp.Api.RetrofitInstance
import retrofit2.Call

class ApiRepository {
    private val api by lazy { RetrofitInstance.api }
    fun getConverted(base : String): Call<Currency>{
        return api.getCorverted(base)
    }

}