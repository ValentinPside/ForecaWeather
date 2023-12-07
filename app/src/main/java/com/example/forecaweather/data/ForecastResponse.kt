package com.example.forecaweather.data

import com.google.gson.annotations.SerializedName

class ForecastResponse(
    @SerializedName("current")
    val current: CurrentWeather
)