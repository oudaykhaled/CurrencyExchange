package com.ouday.currencyexchange.core.di.component

import android.app.Application
import com.ouday.currencyexchange.conversion.di.ConversionDomainModule
import com.ouday.currencyexchange.conversion.di.ConversionPresentationModule
import com.ouday.currencyexchange.conversion.di.ConversionRemoteModule
import com.ouday.currencyexchange.core.di.builder.ActivityBuilder
import com.ouday.currencyexchange.core.di.builder.FragmentBuilder
import com.ouday.currencyexchange.core.di.modules.ContextModule
import com.ouday.currencyexchange.core.di.modules.NetworkModule
import com.ouday.currencyexchange.core.di.modules.SchedulersModule
import com.ouday.currencyexchange.core.App

import com.ouday.currencyexchange.core.di.modules.CoroutinesThreadsProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        NetworkModule::class, ContextModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        SchedulersModule::class,
        CoroutinesThreadsProvider::class,
        ConversionDomainModule::class,
        ConversionPresentationModule::class,
        ConversionRemoteModule::class
    ]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}