package com.ouday.currencyexchange.conversion.domain.ext

import com.ouday.currencyexchange.conversion.data.model.Currency
import java.math.BigDecimal
import java.math.RoundingMode

fun Currency.convertTo(amount: BigDecimal, currency: Currency): BigDecimal? {
    return amount.divide(currency.rateToBase, 2, RoundingMode.HALF_UP).multiply(this.rateToBase)
}
