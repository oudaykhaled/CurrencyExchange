package com.ouday.currencyexchange.conversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.currencyexchange.conversion.data.remote.service.ConversionService
import com.ouday.currencyexchange.conversion.data.remote.source.ConversionRemoteDataSource
import com.ouday.currencyexchange.conversion.data.remote.source.ConversionRemoteDataSourceImpl
import com.ouday.test.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class ConversionRemoteDataSrourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCarolineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var remoteDataSource: ConversionRemoteDataSource

    lateinit var service: ConversionService


    lateinit var response: ConversionResponse

    @Before
    fun init(){
        response = Gson().fromJson(MockConversionResponse, ConversionResponse::class.java)

        service = mock {
            onBlocking {
                getConversionsAsync("USD")
            } doReturn GlobalScope.async {
                Response.success(response)
            }
        }
        remoteDataSource = ConversionRemoteDataSourceImpl(service, mainCarolineRule.coroutineContext)

    }

    @Test
    fun testRequestExchangeReturnSuccessResponse() = runBlocking {
        val result = remoteDataSource.requestExchange("USD")
        assert(result == response)
    }

}