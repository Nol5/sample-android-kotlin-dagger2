package com.example.kaweel.sample

import com.example.kaweel.sample.github.GitHubModule
import com.example.kaweel.sample.github.GitHubPresenter
import com.example.kaweel.sample.github.GitHubUser
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GitHubPresenterTest {

    @Mock
    private lateinit var gitHubApiInterface: GitHubModule.GitHubApiInterface

    @Mock
    private lateinit var gitHubPresenterView: GitHubPresenter.View

    private lateinit var testSchedulerProvider: TestSchedulerProvider

    private lateinit var gitHubPresenter: GitHubPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider()
        gitHubPresenter = GitHubPresenter(gitHubApiInterface, testSchedulerProvider)
        gitHubPresenter.injectView(gitHubPresenterView)

    }

    @Test
    fun display_error() {
        val userName = "Nol4"
        val observable: Observable<GitHubUser> = Observable.create { it ->
            it.onError(Throwable("GGWP"))
        }

        Mockito.`when`(gitHubApiInterface.getUser(Mockito.anyString())).thenReturn(observable)

        gitHubPresenter.getUser(userName)
        testSchedulerProvider.testScheduler.triggerActions()

        Mockito.verify(gitHubPresenterView, Mockito.times(1)).onLoading()
        Mockito.verify(gitHubPresenterView, Mockito.times(0)).displaySuccess(GitHubUser())
        Mockito.verify(gitHubPresenterView, Mockito.times(1)).displayError("GGWP")
        Mockito.verify(gitHubPresenterView, Mockito.times(1)).onDismiss()

    }

    @Test
    fun display_success() {
        val userName = "Nol5"
        val gitHubUser = GitHubUser(userName)
        val observable: Observable<GitHubUser> = Observable.create { it ->
            it.onNext(gitHubUser)
            it.onComplete()
        }

        Mockito.`when`(gitHubApiInterface.getUser(Mockito.anyString())).thenReturn(observable)

        gitHubPresenter.getUser(userName)
        testSchedulerProvider.testScheduler.triggerActions()


        Mockito.verify(gitHubPresenterView, Mockito.times(1)).onLoading()
        Mockito.verify(gitHubPresenterView, Mockito.times(1)).displaySuccess(gitHubUser)
        Mockito.verify(gitHubPresenterView, Mockito.times(0)).displayError(Mockito.anyString())
        Mockito.verify(gitHubPresenterView, Mockito.times(1)).onDismiss()

    }

}