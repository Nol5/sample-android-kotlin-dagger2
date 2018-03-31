package com.example.kaweel.sample.net

import dagger.Component
import javax.inject.Singleton
import android.content.SharedPreferences
import com.example.kaweel.sample.app.AppModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit


@Singleton
@Component(modules = [(NetModule::class), (AppModule::class)])
interface NetComponent {

    fun retrofit(): Retrofit
    fun okHttpClient(): OkHttpClient
    fun sharedPreferences(): SharedPreferences

}