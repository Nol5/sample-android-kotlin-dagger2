package com.example.kaweel.sample.github

import com.example.kaweel.sample.net.NetComponent
import com.example.kaweel.sample.scope.UserScope
import dagger.Component

@UserScope
@Component(dependencies = [(NetComponent::class)], modules = [(GitHubModule::class)])
interface GitHubComponent {
    fun inject(gitHubUserFragment: GitHubUserFragment)
}