package com.example.exchangesapp.domain.usecases

import com.example.exchangesapp.data.ExchangesModel
import com.example.exchangesapp.domain.ExchangesApi
import retrofit2.Response
import javax.inject.Inject

class RequestExchangesByCityNameUseCase @Inject constructor(private val exchangesRep: ExchangesApi) {

    suspend fun requestExchangesByCityName(city: String): Response<List<ExchangesModel>> = exchangesRep.requestExchangesByCityName(city)
}