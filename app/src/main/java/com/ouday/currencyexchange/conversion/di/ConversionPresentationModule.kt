package com.ouday.currencyexchange.conversion.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ouday.currencyexchange.conversion.presentation.viewmodel.ConversionViewModel
import com.ouday.currencyexchange.core.di.modules.ViewModelKey
import com.ouday.test.core.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ConversionPresentationModule {

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ConversionViewModel::class)
    abstract fun bindsViewModel(viewModel: ConversionViewModel): ViewModel

}