package com.ouday.currencyexchange.conversion.data.repository

import androidx.lifecycle.LiveData
import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.test.core.network.Result

interface ConversionRepository {
    suspend fun requestConversions(currency: String): LiveData<Result<ConversionResponse>>
}