package com.ouday.currencyexchange.conversion.presentation.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.ouday.currencyexchange.R
import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.currencyexchange.conversion.domain.ext.toExchange
import com.ouday.currencyexchange.conversion.presentation.ui.adapter.RateRecyclerViewAdapter
import com.ouday.currencyexchange.conversion.presentation.viewmodel.ConversionViewModel
import com.ouday.test.core.presentation.BaseFragment
import com.ouday.test.core.presentation.ViewModelFactory
import javax.inject.Inject


class AllRatesFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory : ViewModelFactory

    private var viewModel: ConversionViewModel? = null

    private var adapter = RateRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ConversionViewModel::class.java)

//        rvCurrency.adapter = adapter
//        rvCurrency.layoutManager = LinearLayoutManager(context)
//
//        viewModel?.getConvertionLiveData()?.observe(this@AllRatesFragment,androidx.lifecycle.Observer {
//            when (it.status) {
//                Status.LOADING -> showLoading()
//                Status.ERROR -> {
//                    it.message
//                    dismissLoading()
//                    onFailedToLoadCurrencies()
//                }
//                Status.SUCCESS -> {
//                    dismissLoading()
//                    it.data?.let {data ->
//                        onLoadCurrencies(data)
//                    }
//                }
//            }
//        })
//
//        viewModel?.requestAllRates()


    }

    private fun onLoadCurrencies(data: ConversionResponse) {
        adapter.setCurrencies(data.toExchange())
    }

    private fun onFailedToLoadCurrencies() {
        Toast.makeText(context, "Offline", Toast.LENGTH_LONG).show()
    }

}
