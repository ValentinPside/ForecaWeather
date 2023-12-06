package com.example.forecaweather.data

import com.example.forecaweather.data.dto.ForecaAuthRequest
import com.example.forecaweather.data.dto.ForecaAuthResponse
import com.example.forecaweather.data.models.LocationsResponse
import retrofit2.Call
import retrofit2.http.*

interface ForecaApi {

    @POST("/authorize/token?expire_hours=-1")
    fun authenticate(
        @Body request: ForecaAuthRequest
    ): Call<ForecaAuthResponse>

    @GET("/api/v1/location/search/{query}")
    fun getLocations(
        @Header("Authorization") token: String,
        @Path("query") query: String
    ): Call<LocationsResponse>

}