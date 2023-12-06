package com.example.forecaweather.data.models

import com.google.gson.annotations.SerializedName

class LocationsResponse(
    @SerializedName("locations")
    val locations: List<ForecastLocation>
)