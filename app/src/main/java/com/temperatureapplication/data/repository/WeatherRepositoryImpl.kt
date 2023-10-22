package com.temperatureapplication.data.repository

import com.temperatureapplication.data.WeatherApi
import com.temperatureapplication.data.remote.dto.ForecastData
import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.domain.repository.WeatherRepository
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getTemperatureData(
        appId: String,
        queryCity: String,
        unit: String
    ): TemperatureData {
        return api.getTemperatureData(appId, queryCity, unit)
    }

    override suspend fun getForeCastData(
        appId: String,
        queryCity: String,
        unit: String
    ): ForecastData {
        return api.getForecastData(appId, queryCity, unit)
    }

}