package com.example.kaweel.sample.net

import dagger.Component
import javax.inject.Singleton
import android.content.SharedPreferences
import android.view.inputmethod.InputMethodManager
import com.example.kaweel.sample.app.AppModule
import com.example.kaweel.sample.app.SchedulerProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit


@Singleton
@Component(modules = [(NetModule::class), (AppModule::class)])
interface NetComponent {

    fun inputMethodManager(): InputMethodManager
    fun schedulerProvider(): SchedulerProvider
    fun retrofit(): Retrofit
    fun okHttpClient(): OkHttpClient
    fun sharedPreferences(): SharedPreferences

}