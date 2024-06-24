package com.example.exchangesapp.ui.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangesapp.data.ExchangesModel
import com.example.exchangesapp.domain.usecases.RequestExchangesByCityNameUseCase
import com.example.exchangesapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val requestExchangesByCityNameUseCase: RequestExchangesByCityNameUseCase
) : ViewModel() {

    val loading: StateFlow<Boolean> = savedStateHandle.getStateFlow(Constants.LOADING, false)

    val exchanges: StateFlow<List<ExchangesModel>?> =
        savedStateHandle.getStateFlow(Constants.EXCHANGES, null)

    fun requestExchanges(city: String) {
        viewModelScope.launch {
            savedStateHandle[Constants.LOADING] = true
            val response = requestExchangesByCityNameUseCase.requestExchangesByCityName(city)
            if (response.isSuccessful){
                savedStateHandle[Constants.EXCHANGES] = response.body()
            } else {
                savedStateHandle[Constants.EXCHANGES] = null
            }
            savedStateHandle[Constants.LOADING] = false
        }
    }
}