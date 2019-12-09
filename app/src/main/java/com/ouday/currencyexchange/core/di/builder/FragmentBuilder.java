package com.ouday.currencyexchange.core.di.builder;

import com.ouday.currencyexchange.conversion.presentation.ui.fragment.AllRatesFragment;
import com.ouday.currencyexchange.conversion.presentation.ui.fragment.ConverterFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentBuilder {

    @ContributesAndroidInjector
    ConverterFragment getConverterFragment();

    @ContributesAndroidInjector
    AllRatesFragment getAllRatesFragment();

}
