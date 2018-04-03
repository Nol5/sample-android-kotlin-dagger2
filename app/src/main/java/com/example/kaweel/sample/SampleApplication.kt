package com.example.kaweel.sample

import android.app.Application
import com.example.kaweel.sample.app.AppModule
import com.example.kaweel.sample.github.DaggerGitHubComponent
import com.example.kaweel.sample.github.GitHubComponent
import com.example.kaweel.sample.github.GitHubModule
import com.example.kaweel.sample.net.DaggerNetComponent
import com.example.kaweel.sample.net.NetComponent
import com.example.kaweel.sample.net.NetModule


class SampleApplication : Application() {

    private lateinit var netComponent: NetComponent
    private lateinit var gitHubComponent: GitHubComponent

    fun getGitHubComponent(): GitHubComponent {
        return gitHubComponent
    }

    override fun onCreate() {
        super.onCreate()
        initialComponent("https://api.github.com/")
    }

    fun initialComponent(url: String) {
        netComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(url))
                .build()

        gitHubComponent = DaggerGitHubComponent.builder()
                .netComponent(netComponent)
                .gitHubModule(GitHubModule())
                .build()
    }


}