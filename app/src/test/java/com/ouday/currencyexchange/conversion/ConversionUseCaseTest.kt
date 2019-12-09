package com.ouday.currencyexchange.conversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.currencyexchange.conversion.data.repository.ConversionRepository
import com.ouday.currencyexchange.conversion.domain.ConversionUseCase
import com.ouday.currencyexchange.conversion.domain.ConversionUseCaseImpl
import com.ouday.test.LiveDataTestUtil
import com.ouday.test.MainCoroutineRule
import com.ouday.test.core.network.Result
import com.ouday.test.core.network.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ConversionUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCarolineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var ConversionUseCase: ConversionUseCase

    lateinit var repository: ConversionRepository

    lateinit var response: ConversionResponse

    @Before
    fun init(){
        response = Gson().fromJson(MockConversionResponse, ConversionResponse::class.java)
    }

    @Test
    fun testRequestLoginWhenStatusIsLoading() = mainCarolineRule.runBlockingTest{
        repository = mock {
            onBlocking { requestConversions("USD") } doReturn object : LiveData<Result<ConversionResponse>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        ConversionUseCase = ConversionUseCaseImpl(repository)
        val result = ConversionUseCase.requestConversions("USD")
        result.observeForever {  }
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
    }

    @Test
    fun testRequestLoginWhenStatusIsSuccess() = mainCarolineRule.runBlockingTest{
        repository = mock {
            onBlocking { requestConversions("USD") } doReturn object : LiveData<Result<ConversionResponse>>() {
                init {
                    value = Result.success(response)
                }
            }
        }
        ConversionUseCase = ConversionUseCaseImpl(repository)
        val result = ConversionUseCase.requestConversions("USD")
        result.observeForever {  }
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(result).data == response)

    }

    @Test
    fun testRequestLoginWhenStatusIsFailed() = mainCarolineRule.runBlockingTest{
        repository = mock {
            onBlocking { requestConversions("USD") } doReturn object : LiveData<Result<ConversionResponse>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        ConversionUseCase = ConversionUseCaseImpl(repository)
        val result = ConversionUseCase.requestConversions("USD")
        result.observeForever {  }
        assert(LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(result).message == "error")

    }
    
}