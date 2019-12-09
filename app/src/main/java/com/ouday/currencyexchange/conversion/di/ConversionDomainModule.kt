package com.ouday.currencyexchange.conversion.di

import com.ouday.currencyexchange.conversion.data.repository.ConversionRepository
import com.ouday.currencyexchange.conversion.data.repository.ConversionRepositoryImpl
import com.ouday.currencyexchange.conversion.domain.ConversionUseCase
import com.ouday.currencyexchange.conversion.domain.ConversionUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ConversionDomainModule {

    @Binds
    abstract fun bindConversionUseCase(
        useCaseImpl: ConversionUseCaseImpl
    ): ConversionUseCase

    @Binds
    abstract fun bindRepo(
        repoImpl: ConversionRepositoryImpl
    ): ConversionRepository
}