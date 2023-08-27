package com.technosp.livecurrencyconvertor.Utils

import com.technosp.livecurrencyconvertor.Interfaces.CurrencyApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrencyApiUtil {

    private var retrofit: Retrofit?= null

    val BASE_URL= "https://api.freecurrencyapi.com/v1/"

    fun getCurrentApiInterface(): CurrencyApiInterface? {

        if (retrofit==null){

            retrofit= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        return retrofit?.create(CurrencyApiInterface::class.java)

    }

}