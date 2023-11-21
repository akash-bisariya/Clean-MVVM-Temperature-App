package com.temperatureapplication.domain.usecase

import com.temperatureapplication.data.remote.dto.ForecastData
import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.domain.repository.WeatherRepository
import com.temperatureapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherForecastUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {
    operator fun invoke(appId: String, city: String, unit: String): Flow<Resource<ForecastData>> =
        flow {
            try {
                emit(Resource.Loading())

                val data = weatherRepository.getForeCastData(appId, city, unit)

                data?.let {
                    emit(Resource.Success(it))
                }


            } catch (e: HttpException) {
                emit(Resource.Error("Unexpected HttpException " + e.localizedMessage))
            } catch (e: IOException) {
                emit(Resource.Error("IO Exception, couldn't reach server " + e.localizedMessage))
            }

        }

}