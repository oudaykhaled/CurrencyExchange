package com.ouday.currencyexchange.core.utils

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class MinMaxDoubleFilter(min:Float, max:Float): InputFilter {
    private var min:Float = 0.0F
    private var max:Float = 0.0F

    init{
        this.min = min
        this.max = max
    }

    private val digitsBeforeZero = 12
    private val digitsAfterZero = 3
    private val pattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int): CharSequence? {
        try {
            val destinationValue = dest.toString().replace(",".toRegex(), "").replace("/.".toRegex(), "")
            val sourceValue = source.toString().replace(",".toRegex(), "").replace("/.".toRegex(), "")
            val str = destinationValue + sourceValue
            if (str.length > 0 && str[str.length - 1] == '.') return null // ignore typing .
            val matcher = pattern.matcher(destinationValue)
            if (!matcher.matches())
                return ""
            val input = java.lang.Double.parseDouble(destinationValue + sourceValue)
            if (isInRange(min.toDouble(), max.toDouble(), input))
                return null
        } catch (nfe: Exception) {
            return ""
        }

        return ""

    }

    private fun isInRange(minValue: Double, maxValue: Double, inputValue: Double): Boolean {
        return if (maxValue > minValue) inputValue >= minValue && inputValue <= maxValue else inputValue >= maxValue && inputValue <= minValue
    }
}

