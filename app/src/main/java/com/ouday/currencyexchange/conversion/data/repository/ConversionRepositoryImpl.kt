package com.ouday.currencyexchange.conversion.data.repository

import androidx.lifecycle.liveData
import com.ouday.currencyexchange.conversion.data.remote.source.ConversionRemoteDataSource
import com.ouday.test.core.network.Result
import javax.inject.Inject

class ConversionRepositoryImpl @Inject constructor(
    private val remoteDataSource: ConversionRemoteDataSource
) :
    ConversionRepository {

    override suspend fun requestConversions(currency: String) = liveData {
        emit(Result.loading())
        try {
            var response = remoteDataSource.requestExchange(currency)
            emit(Result.success(response))
        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }

}