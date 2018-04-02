package com.example.kaweel.sample.app

import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import dagger.Module
import javax.inject.Singleton
import dagger.Provides


@Module
class AppModule(private var mApplication: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider() : SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideInputMethod() = mApplication.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

}