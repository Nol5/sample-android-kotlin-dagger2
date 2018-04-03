package com.example.kaweel.sample.app

import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private var mApplication: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideInputMethod(): InputMethodManager {
        return mApplication.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

}