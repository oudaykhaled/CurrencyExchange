package com.ouday.currencyexchange.core.di.builder;

import com.ouday.currencyexchange.conversion.presentation.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBuilder {

    @ContributesAndroidInjector
    MainActivity getMainActivity();

}
