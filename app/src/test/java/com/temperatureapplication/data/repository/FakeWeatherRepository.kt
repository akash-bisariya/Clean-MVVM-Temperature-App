package com.temperatureapplication.data.repository

import com.temperatureapplication.data.remote.dto.ForecastData
import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.domain.repository.WeatherRepository
import com.temperatureapplication.utils.Resource

class FakeWeatherRepository: WeatherRepository {

    private var shouldReturnError = false
    fun setShouldReturnError(value:Boolean){
        shouldReturnError = value
    }
    override suspend fun getTemperatureData(
        appId: String,
        queryCity: String,
        unit: String
    ): Resource<TemperatureData> {
        return if(shouldReturnError){
            Resource.Error("Error")
        }
        else{
            Resource.Success(TemperatureData(base="test"))
        }
    }

    override suspend fun getForeCastData(
        appId: String,
        queryCity: String,
        unit: String
    ): Resource<ForecastData> {
        return if(shouldReturnError){
            Resource.Error("Error")
        }
        else{
            Resource.Success(ForecastData())
        }
    }
}