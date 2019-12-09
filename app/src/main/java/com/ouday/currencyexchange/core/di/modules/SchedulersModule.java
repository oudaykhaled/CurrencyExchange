package com.ouday.currencyexchange.core.di.modules;

import com.ouday.currencyexchange.core.di.qualifier.IO;
import com.ouday.currencyexchange.core.di.qualifier.MainThread;
import com.ouday.test.core.di.qualifier.CoroutinesIO;
import com.ouday.test.core.di.qualifier.CoroutinesMainThread;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;

@Module
public class SchedulersModule {

    @Provides
    @IO
    public Scheduler bindIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @MainThread
    public Scheduler bindMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }



    @CoroutinesIO
    @Provides
    public CoroutineContext providesIoDispatcher() {
      return  Dispatchers.getIO();
    }

    @CoroutinesMainThread
    @Provides
    public CoroutineContext providesMainThreadDispatcher(){
        return Dispatchers.getMain();
    }

}
