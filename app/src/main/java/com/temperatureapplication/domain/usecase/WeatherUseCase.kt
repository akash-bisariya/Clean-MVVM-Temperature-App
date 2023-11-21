package com.temperatureapplication.domain.usecase

import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.domain.repository.WeatherRepository
import com.temperatureapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

open class WeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    operator  fun invoke(
        appId: String,
        city: String,
        unit: String
    ): Flow<Resource<TemperatureData>> = flow {

        emit(Resource.Loading())

        val data = weatherRepository.getTemperatureData(appId, city, unit)

        emit(data)


    }

}