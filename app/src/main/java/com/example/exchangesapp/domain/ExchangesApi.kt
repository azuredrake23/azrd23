package com.example.exchangesapp.domain

import com.example.exchangesapp.data.ExchangesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangesApi {

    @GET("kursExchange?")
    suspend fun requestExchangesByCityName(@Query("name") city: String): Response<List<ExchangesModel>>
}