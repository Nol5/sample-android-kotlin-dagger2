package com.example.kaweel.sample.github

import com.example.kaweel.sample.app.SchedulerProvider
import javax.inject.Inject


class GitHubPresenter @Inject constructor(private val gitHubApiInterface: GitHubModule.GitHubApiInterface, private val schedulerProvider: SchedulerProvider) {

    private lateinit var view: View

    interface View {

        fun onLoading()

        fun onDismiss()

        fun displaySuccess(gitHubUser: GitHubUser)

        fun displayError(message: String)

    }

    fun injectView(view: View) {
        this.view = view
    }

    fun getUser(username: String) {
        view.onLoading()
        gitHubApiInterface.getUser(username)
                .subscribeOn(schedulerProvider.ioScheduler())
                .observeOn(schedulerProvider.uiScheduler())
                .subscribe({ data ->
                    view.displaySuccess(data)
                }, { throwable ->
                    view.displayError(throwable.message.toString())
                    view.onDismiss()
                }, {
                    view.onDismiss()
                })
    }

}