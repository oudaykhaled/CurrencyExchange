package com.ouday.currencyexchange.conversion.domain.ext

import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.currencyexchange.conversion.data.model.Currency
import com.ouday.currencyexchange.conversion.domain.CurrencyDescriptionProvider
import java.math.BigDecimal

fun ConversionResponse.toExchange(): ArrayList<Currency> {

    val exchanges = ArrayList<Currency>()

    // Add Base Currency
    val baseExchange = Currency(
        this.base,
        CurrencyDescriptionProvider.getDescription(this.base),
        BigDecimal("1"))
    exchanges.add(baseExchange)

    //Add All currencies
    for (keyValue in this.rates){
        exchanges.add(Currency(
            keyValue.key,
            CurrencyDescriptionProvider.getDescription(keyValue.key),
            keyValue.value))
    }

    return exchanges

}
