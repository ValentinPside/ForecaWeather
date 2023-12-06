package com.example.forecaweather.data.models

import com.google.gson.annotations.SerializedName

data class ForecastLocation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String
)