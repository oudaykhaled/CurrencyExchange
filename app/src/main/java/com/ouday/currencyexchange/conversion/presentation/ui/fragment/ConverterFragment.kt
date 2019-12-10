package com.ouday.currencyexchange.conversion.presentation.ui.fragment


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ouday.currencyexchange.conversion.data.model.ConversionResponse
import com.ouday.currencyexchange.conversion.domain.ext.toExchange
import com.ouday.currencyexchange.conversion.presentation.ui.adapter.RateRecyclerViewAdapter
import com.ouday.currencyexchange.conversion.presentation.viewmodel.ConversionViewModel
import com.ouday.test.core.network.Status
import com.ouday.test.core.presentation.BaseFragment
import com.ouday.test.core.presentation.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_converter.*
import javax.inject.Inject
import com.shashank.sony.fancygifdialoglib.FancyGifDialog
import com.google.android.material.snackbar.Snackbar
import com.ouday.currencyexchange.R

class ConverterFragment : BaseFragment() {

    private var mainHandler: Handler? = null
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var viewModel: ConversionViewModel? = null

    private var adapter = RateRecyclerViewAdapter()

    private var isFirstTimeLoaded = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(ConversionViewModel::class.java)

        rvCurrency.adapter = adapter
        rvCurrency.layoutManager = LinearLayoutManager(context)

        mainHandler = Handler(Looper.getMainLooper())

        viewModel?.getConvertionLiveData()
            ?.observe(this@ConverterFragment, androidx.lifecycle.Observer {
                when (it.status) {
                    Status.LOADING -> {
                        if (isFirstTimeLoaded) showLoading()
                    }
                    Status.ERROR -> {
                        it.message
                        dismissLoading()
                        if (adapter.itemCount < 2) {
                            onNoDataShown()
                        } else {
                            onFailedToLoadCurrencies()
                            mainHandler?.postDelayed({
                                viewModel?.requestAllRates()
                            }, 1000)
                        }

                    }
                    Status.SUCCESS -> {
                        dismissLoading()
                        if (isFirstTimeLoaded) {
                            it.data?.let { data ->
                                onLoadCurrencies(data)
                            }
                            isFirstTimeLoaded = false
                        } else {
                            it.data?.let { data ->
                                onUpdateCurrencies(data)
                            }
                        }
                        mainHandler?.postDelayed({
                            viewModel?.requestAllRates()
                        }, 1000)
                    }
                }
            })


        mainHandler?.postDelayed({
            viewModel?.requestAllRates()
        }, 100)

    }

    private fun onNoDataShown() {
        isFirstTimeLoaded = true
        FancyGifDialog.Builder(activity)
            .setTitle(context?.getString(R.string.no_internet))
            .setMessage(context?.getString(R.string.refresh))
            .setNegativeBtnText(context?.getString(R.string.exit))
            .setPositiveBtnBackground("#D81B60")
            .setPositiveBtnText(context?.getString(R.string.ok))
            .setNegativeBtnBackground("#8B959E")
            .setGifResource(R.drawable.gif1)   //Pass your Gif here
            .isCancellable(true)
            .OnPositiveClicked {
                mainHandler?.postDelayed({
                    viewModel?.requestAllRates()
                }, 100)
            }
            .OnNegativeClicked {
                activity?.finish()
            }
            .build()
    }

    private fun onUpdateCurrencies(data: ConversionResponse) {
        adapter.updateCurrencies(data.toExchange())
    }

    private fun onLoadCurrencies(data: ConversionResponse) {
        adapter.setCurrencies(data.toExchange())
    }

    private fun onFailedToLoadCurrencies() {
        view?.let {
            val snackbar =
                Snackbar.make(it, R.string.offline, Snackbar.LENGTH_SHORT)
            snackbar.show()

        }
    }

}
