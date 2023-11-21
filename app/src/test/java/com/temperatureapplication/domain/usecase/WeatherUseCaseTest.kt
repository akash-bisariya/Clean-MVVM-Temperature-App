package com.temperatureapplication.domain.usecase

import app.cash.turbine.test
import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.data.repository.FakeWeatherRepository
import com.temperatureapplication.domain.repository.WeatherRepository
import com.temperatureapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class WeatherUseCaseTest {

    @Mock
    lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test_weather_useCaseSuccess()= runTest{
        val data = TemperatureData()
        val data1: Resource<TemperatureData> = Resource.Success(data)
        val flow = flow<Resource<TemperatureData>> { emit(data1) }
        whenever(weatherRepository.getTemperatureData(anyString(), anyString(), anyString())).thenReturn(data1)

        val res = WeatherUseCase(weatherRepository).invoke("","","").test {
            assertEquals(awaitItem(),Resource.Loading(data))
            assertEquals(awaitItem(),Resource.Success(data))
            awaitComplete()
        }
    }
}