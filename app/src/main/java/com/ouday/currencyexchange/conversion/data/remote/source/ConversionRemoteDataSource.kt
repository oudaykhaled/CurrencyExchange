package com.ouday.currencyexchange.conversion.data.remote.source

import com.ouday.currencyexchange.conversion.data.model.ConversionResponse

interface ConversionRemoteDataSource {
    suspend fun requestExchange(currency: String): ConversionResponse
}