package com.ouday.currencyexchange.conversion.presentation.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ouday.currencyexchange.R
import com.ouday.test.core.presentation.BaseFragment


class AllRatesFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_rates, container, false)
    }


}
