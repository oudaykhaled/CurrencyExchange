package com.ouday.currencyexchange.conversion.domain

import com.ouday.currencyexchange.conversion.data.repository.ConversionRepository
import javax.inject.Inject

class ConversionUseCaseImpl @Inject constructor(private val repository: ConversionRepository) : ConversionUseCase {
    override suspend fun requestConversions(currency: String) = repository.requestConversions(currency)


}