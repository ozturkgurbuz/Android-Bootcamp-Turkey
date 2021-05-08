package com.gurbuz.finalapp.Api

import com.gurbuz.finalapp.Api.Model.Currency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {
    @GET("v6/0af2f696bb358e0914653668/latest/{base_code}")
    fun getCorverted(@Path("base_code") base_code: String): Call<Currency>
}