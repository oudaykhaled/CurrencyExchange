package com.ouday.currencyexchange.conversion.domain

object CurrencyDescriptionProvider {

    private val map = HashMap<String, String>()

    init {
        map["USD"] = "United State Dollar"
        map["AUD"] = "Australian dollar"
        map["BGN"] = "Bulgarian lev"
        map["BRL"] = "Brazilian real"
        map["CAD"] = "Canadian dollar"
        map["CHF"] = "Swiss franc"
        map["CNY"] = "Chinese yuan"
        map["CZK"] = "Czech koruna"
        map["DKK"] = "Danish krone"
        map["GBP"] = "British pound"
        map["HKD"] = "Hong Kong dollar"
        map["HRK"] = "Croatian kuna"
        map["HUF"] = "Hungarian forint"
        map["IDR"] = "Indonesian rupiah"
        map["ILS"] = "Israeli new shekel"
        map["INR"] = "Indian rupee"
        map["ISK"] = "Icelandic króna"
        map["JPY"] = "Japanese yen"
        map["KRW"] = "South Korean won"
        map["MXN"] = "Mexican peso"
        map["MYR"] = "Malaysian ringgit"
        map["NOK"] = "Norwegian krone"
        map["NZD"] = "New Zealand dollar"
        map["PHP"] = "Philippine peso"
        map["PLN"] = "Polish złoty"
        map["RON"] = "Romanian leu"
        map["RUB"] = "Russian ruble"
        map["SEK"] = "Swedish krona"
        map["SGD"] = "Singapore dollar"
        map["THB"] = "Thai baht"
        map["TRY"] = "Turkish lira"
        map["ZAR"] = "South African rand"
    }

    fun getDescription(currencyCode: String): String? {
        return map[currencyCode]
    }

}