package com.ouday.currencyexchange.conversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.currencyexchange.conversion.data.remote.source.ConversionRemoteDataSource
import com.ouday.currencyexchange.conversion.data.repository.ConversionRepository
import com.ouday.currencyexchange.conversion.data.repository.ConversionRepositoryImpl
import com.ouday.test.LiveDataTestUtil
import com.ouday.test.MainCoroutineRule
import com.ouday.test.core.network.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ConversionRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var repository: ConversionRepository

    @Mock
    lateinit var remoteDataSource: ConversionRemoteDataSource

    lateinit var response: ConversionResponse

    @Before
    fun init(){
        response = Gson().fromJson(MockConversionResponse, ConversionResponse::class.java)
        MockitoAnnotations.initMocks(this)
        repository = ConversionRepositoryImpl(remoteDataSource)
    }

    @Test
    fun testRequestConversionReturnSuccess() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.requestExchange("USD")).thenReturn(response)
        val result = repository.requestConversions("USD")
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == response)
    }

    @Test
    fun testRequestConversionReturnFailed() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(remoteDataSource.requestExchange("USD")).thenReturn(response)
        val result = repository.requestConversions("EURO")
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
    }

}