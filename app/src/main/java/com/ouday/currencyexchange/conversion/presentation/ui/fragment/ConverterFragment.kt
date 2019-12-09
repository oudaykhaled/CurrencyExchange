package com.ouday.currencyexchange.conversion.presentation.ui.fragment


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ouday.currencyexchange.R
import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.currencyexchange.conversion.domain.ext.toExchange
import com.ouday.currencyexchange.conversion.presentation.ui.adapter.RateRecyclerViewAdapter
import com.ouday.currencyexchange.conversion.presentation.viewmodel.ConversionViewModel
import com.ouday.test.core.network.Status
import com.ouday.test.core.presentation.BaseFragment
import com.ouday.test.core.presentation.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_converter.*
import javax.inject.Inject


class ConverterFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory : ViewModelFactory

    private var viewModel: ConversionViewModel? = null

    private var adapter = RateRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ConversionViewModel::class.java)

        //        rvCurrency.itemAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        rvCurrency.adapter = adapter
        rvCurrency.layoutManager = LinearLayoutManager(context)

        viewModel?.getConvertionLiveData()?.observe(this@ConverterFragment,androidx.lifecycle.Observer {
            when (it.status) {
//                Status.LOADING -> showLoading()
                Status.ERROR -> {
                    it.message
//                    dismissLoading()
                    onFailedToLoadCurrencies()
                }
                Status.SUCCESS -> {
//                    dismissLoading()
                    it.data?.let {data ->
                        onLoadCurrencies(data)
                    }
                }
            }
        })

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                viewModel?.requestAllRates()
                mainHandler.postDelayed(this, 1000)
            }
        })

    }

    private fun onLoadCurrencies(data: ConversionResponse) {
        adapter.setCurrencies(data.toExchange())
    }

    private fun onFailedToLoadCurrencies() {
        Toast.makeText(context, "Offline", Toast.LENGTH_LONG).show()
    }

}
