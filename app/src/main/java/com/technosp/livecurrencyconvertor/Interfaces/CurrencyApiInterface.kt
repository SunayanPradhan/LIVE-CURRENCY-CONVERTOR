package com.technosp.livecurrencyconvertor.Interfaces

import com.technosp.livecurrencyconvertor.Models.CurrencyModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiInterface {

    @GET("latest")
    fun getCurrentCurrencyData(
        @Query("apikey") apikey:String,
        @Query("currencies") currencies: String,
        @Query("base_currency") base_currency: String
    ):Call<CurrencyModel>

}