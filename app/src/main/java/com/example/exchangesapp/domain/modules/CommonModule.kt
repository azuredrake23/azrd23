package com.example.exchangesapp.domain.modules

import com.example.exchangesapp.domain.ExchangesApi
import com.example.exchangesapp.domain.usecases.RequestExchangesByCityNameUseCase
import com.example.exchangesapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(OkHttpClient.Builder()
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideExchangesApi(retrofit: Retrofit): ExchangesApi =
        retrofit.create(ExchangesApi::class.java)

    @Singleton
    @Provides
    fun provideGetExchangesUseCase(exchangesRep: ExchangesApi) =
        RequestExchangesByCityNameUseCase(exchangesRep)

}