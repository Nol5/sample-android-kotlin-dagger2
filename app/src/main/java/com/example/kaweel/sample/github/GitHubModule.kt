package com.example.kaweel.sample.github

import com.example.kaweel.sample.scope.GitHubScope
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

@Module
class GitHubModule {

    interface GitHubApiInterface {
        @GET("users/{user}")
        fun getUser(@Path("user") user: String): Observable<GitHubUser>
    }

    @Provides
    @GitHubScope
    fun providesGitHubInterface(retrofit: Retrofit): GitHubApiInterface {
        return retrofit.create(GitHubApiInterface::class.java)
    }
}