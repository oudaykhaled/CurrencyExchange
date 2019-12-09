package com.ouday.currencyexchange.conversion.data.remote.service

import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConversionService {

    @GET("https://revolut.duckdns.org/latest")
    fun getConversionsAsync(@Query("base") currency: String): Deferred<Response<ConversionResponse>>
}