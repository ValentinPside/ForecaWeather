package com.example.forecaweather.data

import com.example.forecaweather.data.dto.ForecaAuthRequest
import com.example.forecaweather.data.dto.ForecaAuthResponse
import com.example.forecaweather.data.models.LocationsResponse
import io.reactivex.Single
import retrofit2.http.*

interface ForecaApi {

    @POST("/authorize/token?expire_hours=-1")
    fun authenticate(
        @Body request: ForecaAuthRequest
    ): Single<ForecaAuthResponse>

    @GET("/api/v1/location/search/{query}")
    fun getLocations(
        @Header("Authorization") token: String,
        @Path("query") query: String
    ): Single<LocationsResponse>

    @GET("/api/v1/current/{location}")
    fun getForecast(
        @Header("Authorization") token: String,
        @Path("location") locationId: Int
    ): Single<ForecastResponse>

}