package com.ouday.currencyexchange.conversion.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.currencyexchange.conversion.domain.ConversionUseCase
import com.ouday.test.core.network.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConversionViewModel @Inject constructor(private val useCase: ConversionUseCase) : ViewModel() {

    private val conversionLiveData = MediatorLiveData<Result<ConversionResponse>>()

    fun requestAllRates() {
        viewModelScope.launch {
            conversionLiveData.addSource(useCase.requestConversions("USD")) {
                conversionLiveData.value = it
            }
        }
    }

    fun getConvertionLiveData(): MediatorLiveData<Result<ConversionResponse>> {
        return conversionLiveData
    }

}