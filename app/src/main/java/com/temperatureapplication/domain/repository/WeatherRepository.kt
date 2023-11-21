package com.temperatureapplication.domain.repository

import com.temperatureapplication.data.remote.dto.ForecastData
import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.utils.Resource

interface WeatherRepository {

    suspend fun getTemperatureData(appId:String, queryCity:String, unit:String): Resource<TemperatureData>

    suspend fun getForeCastData(appId:String, queryCity:String, unit:String): ForecastData?

}