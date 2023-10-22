package com.temperatureapplication.data

import com.temperatureapplication.data.remote.dto.ForecastData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //https://api.openweathermap.org/data/2.5/forecast?q=Bengaluru&APPID=9b8cb8c7f11c077f8c4e217974d9ee40&units=metric
    @GET("data/2.5/forecast")
    suspend fun getForecastData(
        @Query("q") q: String,
        @Query("APPID") appID: String,
        @Query("units") metric: String
    ): List<ForecastData>


    @GET("data/2.5/weather")
    suspend fun getTemperatureData(
        @Query("q") q: String,
        @Query("APPID") appID: String,
        @Query("units") metric: String
    ): List<ForecastData>

}