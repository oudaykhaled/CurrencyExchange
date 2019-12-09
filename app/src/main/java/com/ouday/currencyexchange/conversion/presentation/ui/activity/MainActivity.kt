package com.ouday.currencyexchange.conversion.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.ouday.currencyexchange.R
import com.ouday.currencyexchange.conversion.presentation.ui.fragment.AllRatesFragment
import com.ouday.currencyexchange.conversion.presentation.ui.fragment.ConverterFragment
import com.ouday.currencyexchange.conversion.presentation.viewmodel.ConversionViewModel
import com.ouday.test.core.presentation.ViewModelFactory
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var viewModel: ConversionViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ConversionViewModel::class.java)

        viewPager.adapter = FragmentViewPager(this)
        TabLayoutMediator(tabs, viewPager,
            TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                tab.text = getString(
                    if (position == 1) R.string.all_rates
                    else R.string.converter
                )
            }).attach()



    }

    class FragmentViewPager(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = 2
        override fun createFragment(position: Int): Fragment {
            return if (position == 0) AllRatesFragment()
            else ConverterFragment()
        }
    }

}
