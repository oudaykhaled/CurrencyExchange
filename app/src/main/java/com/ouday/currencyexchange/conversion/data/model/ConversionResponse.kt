package com.ouday.currencyexchange.conversion.data.model

import java.math.BigDecimal

data class ConversionResponse(
    val base: String,
    val date: String,
    val rates: HashMap<String, BigDecimal>
)