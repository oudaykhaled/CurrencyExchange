package com.ouday.currencyexchange.conversion.domain

import android.content.Context

object CurrencyImageProvider {

    fun loadImage(context: Context, currencyCode: String): Int? {
        return context.resources.getIdentifier(
            "ic_"
                    + currencyCode.toLowerCase(), "drawable", context?.packageName
        )
    }



}