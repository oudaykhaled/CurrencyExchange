package com.ouday.currencyexchange.conversion.data.remote.source

import com.ouday.currencyexchange.conversion.data.remote.service.ConversionService
import com.ouday.test.core.di.qualifier.CoroutinesIO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ConversionRemoteDataSourceImpl @Inject constructor(
    private val service: ConversionService,
    @CoroutinesIO private val context: CoroutineContext
) : ConversionRemoteDataSource {

    override suspend fun requestExchange(currency: String) = withContext(context) {
        val response = service.getConversionsAsync(currency).await()
        if (response.isSuccessful)
            response.body() ?: throw Exception("No Response")
        else {
            throw Exception()
        }
    }

}