package com.temperatureapplication.data.repository

import com.temperatureapplication.data.WeatherApi
import com.temperatureapplication.data.remote.dto.ForecastData
import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.domain.repository.WeatherRepository
import com.temperatureapplication.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getTemperatureData(
        appId: String,
        queryCity: String,
        unit: String
    ): Resource<TemperatureData> {
        return try {
            val res = api.getTemperatureData(appId, queryCity, unit)
            if (res.isSuccessful) {
                res.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error("Unknown Error Occurred")
            } else
                Resource.Error(res.message())
        } catch (e: HttpException) {
            Resource.Error("Unexpected HttpException " + e.localizedMessage)
        } catch (e: IOException) {
            Resource.Error("IO Exception, couldn't reach server " + e.localizedMessage)
        }

    }

    override suspend fun getForeCastData(
        appId: String,
        queryCity: String,
        unit: String
    ): ForecastData? {
        val res = api.getForecastData(appId, queryCity, unit)
        if (res.isSuccessful) {
            return res.body()!!
        }
        return null
    }

}