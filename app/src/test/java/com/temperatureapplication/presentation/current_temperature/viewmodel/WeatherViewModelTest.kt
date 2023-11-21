package com.temperatureapplication.presentation.current_temperature.viewmodel

import app.cash.turbine.test
import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.data.remote.dto.Weather
import com.temperatureapplication.data.repository.FakeWeatherRepository
import com.temperatureapplication.domain.usecase.WeatherForecastUseCase
import com.temperatureapplication.domain.usecase.WeatherUseCase
import com.temperatureapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class WeatherViewModelTest {

    private lateinit var weatherViewModel: WeatherViewModel
    lateinit var fakeWeatherRepository: FakeWeatherRepository

    @Mock
    lateinit var weatherUseCase: WeatherUseCase

    @Mock
    lateinit var weatherForecastUseCase: WeatherForecastUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        fakeWeatherRepository = FakeWeatherRepository()


    }

    @Test
    fun test_success_case()= runTest{
        fakeWeatherRepository.setShouldReturnError(true)

        weatherViewModel = WeatherViewModel(WeatherUseCase(fakeWeatherRepository),WeatherForecastUseCase(fakeWeatherRepository))

        weatherViewModel.weatherState.test {
            val emission = awaitItem()
            assertNotNull(emission.error)
        }



    }
}