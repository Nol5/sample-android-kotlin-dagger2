package com.example.kaweel.sample

import android.app.Application
import com.example.kaweel.sample.app.AppModule
import com.example.kaweel.sample.github.DaggerGitHubComponent
import com.example.kaweel.sample.net.NetComponent
import com.example.kaweel.sample.net.NetModule
import com.example.kaweel.sample.github.GitHubModule
import com.example.kaweel.sample.github.GitHubComponent
import com.example.kaweel.sample.net.DaggerNetComponent


class SampleApplication : Application() {

    private lateinit var netComponent: NetComponent
    private lateinit var gitHubComponent: GitHubComponent

    fun getGitHubComponent(): GitHubComponent {
        return gitHubComponent
    }

    override fun onCreate() {
        super.onCreate()
        netComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("https://api.github.com/"))
                .build()

        gitHubComponent = DaggerGitHubComponent.builder()
                .netComponent(netComponent)
                .gitHubModule(GitHubModule())
                .build()
    }


}