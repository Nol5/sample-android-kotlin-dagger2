package com.example.kaweel.sample.github

import com.example.kaweel.sample.net.NetComponent
import com.example.kaweel.sample.scope.GitHubScope
import dagger.Component

@GitHubScope
@Component(dependencies = [(NetComponent::class)], modules = [(GitHubModule::class)])
interface GitHubComponent {
    fun inject(gitHubUserFragment: GitHubUserFragment)
}