package com.ouday.currencyexchange.conversion.di

import com.ouday.currencyexchange.conversion.data.remote.service.ConversionService
import com.ouday.currencyexchange.conversion.data.remote.source.ConversionRemoteDataSource
import com.ouday.currencyexchange.conversion.data.remote.source.ConversionRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [ConversionRemoteModule.Binders::class])
class ConversionRemoteModule {

    @Module
    interface Binders {

        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: ConversionRemoteDataSourceImpl
        ): ConversionRemoteDataSource

    }

    @Provides
    fun providesConversionService(retrofit: Retrofit): ConversionService =
        retrofit.create(ConversionService::class.java)

}