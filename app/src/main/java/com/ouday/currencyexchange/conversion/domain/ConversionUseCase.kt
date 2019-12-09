package com.ouday.currencyexchange.conversion.domain

import androidx.lifecycle.LiveData
import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.test.core.network.Result

interface ConversionUseCase {
    suspend fun requestConversions(currency: String): LiveData<Result<ConversionResponse>>
}