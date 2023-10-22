package com.temperatureapplication.domain.repository

import com.temperatureapplication.data.remote.dto.ForecastData
import com.temperatureapplication.data.remote.dto.TemperatureData

interface WeatherRepository {

    suspend fun getTemperatureData(appId:String, queryCity:String, unit:String): TemperatureData

    suspend fun getForeCastData(appId:String, queryCity:String, unit:String): ForecastData

}