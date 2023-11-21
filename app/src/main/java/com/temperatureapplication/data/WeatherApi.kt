package com.temperatureapplication.data

import com.temperatureapplication.data.remote.dto.ForecastData
import com.temperatureapplication.data.remote.dto.TemperatureData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/forecast")
    suspend fun getForecastData(
        @Query("APPID") appID: String,
        @Query("q") q: String,
        @Query("units") metric: String
    ): Response<ForecastData>


    @GET("data/2.5/weather")
    suspend fun getTemperatureData(
        @Query("APPID") appID: String,
        @Query("q") q: String,
        @Query("units") metric: String
    ): Response<TemperatureData>

}