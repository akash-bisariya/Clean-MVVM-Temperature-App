package com.temperatureapplication.di

import com.temperatureapplication.data.WeatherApi
import com.temperatureapplication.data.repository.WeatherRepositoryImpl
import com.temperatureapplication.domain.repository.WeatherRepository
import com.temperatureapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideCarousellNewsApi(okHttpClient: OkHttpClient):WeatherApi{

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

    }

    @Provides
    @Singleton
    fun provideJokeRepository(api: WeatherApi):WeatherRepository{
        return WeatherRepositoryImpl(api)
    }

}