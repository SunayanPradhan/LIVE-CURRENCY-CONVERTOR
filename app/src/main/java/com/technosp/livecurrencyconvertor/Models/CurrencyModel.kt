package com.technosp.livecurrencyconvertor.Models

import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("data") val rates: Map<String, Double>
)