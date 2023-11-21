package com.temperatureapplication.data.repository

import com.temperatureapplication.data.WeatherApi
import com.temperatureapplication.data.remote.dto.ForecastData
import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyObject
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class WeatherRepositoryImplTest {

    @Mock
    lateinit var weatherApi: WeatherApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetForeCastData_CorrectResponse() = runTest {
        val sampleData = TemperatureData()
        Mockito.`when`(weatherApi.getTemperatureData(anyString(), anyString(), anyString()))
            .thenReturn(Response.success(sampleData))

        val sut = WeatherRepositoryImpl(weatherApi)
        val result = sut.getTemperatureData(anyString(), anyString(), anyString())
        assertEquals(true,result is Resource.Success)
        assertEquals(sampleData,result.data)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetForeCastData_ErrorResponse() = runTest {
        val sampleData = null
        Mockito.`when`(weatherApi.getTemperatureData(anyString(), anyString(), anyString()))
            .thenReturn(Response.success(sampleData))

        val sut = WeatherRepositoryImpl(weatherApi)
        val result = sut.getTemperatureData(anyString(), anyString(), anyString())
        assertEquals(true,result is Resource.Error)

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetForeCastData_NullResponse() = runTest {
        val sampleData = null
        Mockito.`when`(weatherApi.getTemperatureData(anyString(), anyString(), anyString()))
            .thenReturn(Response.success(sampleData))

        val sut = WeatherRepositoryImpl(weatherApi)
        val result = sut.getTemperatureData(anyString(), anyString(), anyString())
        assertEquals(sampleData,result.data)

    }
}