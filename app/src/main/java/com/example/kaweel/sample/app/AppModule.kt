package com.example.kaweel.sample.app

import android.app.Application
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
}