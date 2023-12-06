package com.example.forecaweather.domain

import android.util.Log
import com.example.forecaweather.data.ForecaApi
import com.example.forecaweather.data.dto.ForecaAuthRequest
import com.example.forecaweather.data.dto.ForecaAuthResponse
import com.example.forecaweather.data.models.LocationsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecaRepository {

    private companion object {
        const val BASE_URL = "https://fnw-us.foreca.com"

        const val USER = "valentin-pside"
        const val PASSWORD = "B9EvY9zGkzUf"
        const val HARDCODED_LOCATION = "Barcelona"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val forecaService = retrofit.create(ForecaApi::class.java)

    private var token = ""

    fun authenticate() {
        forecaService.authenticate(ForecaAuthRequest(USER, PASSWORD))
            .enqueue(object : Callback<ForecaAuthResponse> {
                override fun onResponse(call: Call<ForecaAuthResponse>,
                                        response: Response<ForecaAuthResponse>
                ) {
                    if (response.code() == 200) {
                        token = response.body()?.token.toString()
                        search(token, HARDCODED_LOCATION)
                    } else {
                        Log.e("RxJavaForeca", "Something went wrong with auth: ${response.code().toString()}")
                    }
                }

                override fun onFailure(call: Call<ForecaAuthResponse>, t: Throwable) {
                    Log.e("RxJavaForeca", "onFailure auth request", t)
                }
            })
    }

    private fun search(accessToken: String, searchQuery: String) {
        val bearerToken = "Bearer $accessToken"
        forecaService.getLocations(bearerToken, searchQuery)
            .enqueue(object : Callback<LocationsResponse> {
                override fun onResponse(call: Call<LocationsResponse>,
                                        response: Response<LocationsResponse>) {
                    when (response.code()) {
                        200 -> {
                            if (response.body()?.locations?.isNotEmpty() == true) {
                                val locations = response.body()?.locations!!

                                Log.d("RxJavaForeca", "Found locations!")
                                locations.forEach {
                                    Log.d("RxJavaForeca", it.toString())
                                }


                            } else {
                                Log.d("RxJavaForeca", "Nothing found")
                            }

                        }
                        401 -> authenticate()
                        else -> {
                            Log.e("RxJavaForeca", "Something went wrong with search: ${response.code().toString()}")
                        }
                    }

                }

                override fun onFailure(call: Call<LocationsResponse>, t: Throwable) {
                    Log.e("RxJavaForeca", "onFailure search request", t)
                }

            })
    }

}