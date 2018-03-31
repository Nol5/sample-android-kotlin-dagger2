package com.example.kaweel.sample.github

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GitHubPresenter @Inject constructor(private val gitHubApiInterface: GitHubModule.GitHubApiInterface) {

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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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