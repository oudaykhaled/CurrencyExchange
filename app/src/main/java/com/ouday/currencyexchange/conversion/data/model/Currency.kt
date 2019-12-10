package com.ouday.currencyexchange.conversion.data.model

import java.math.BigDecimal

data class Currency (
    val code: String,
    val description: String?,
    var rateToBase: BigDecimal)