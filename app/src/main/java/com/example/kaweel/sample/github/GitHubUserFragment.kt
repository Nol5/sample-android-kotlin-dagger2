package com.example.kaweel.sample.github

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kaweel.sample.R
import com.example.kaweel.sample.SampleApplication
import com.example.kaweel.sample.app.AppSchedulerProvider
import javax.inject.Inject


class GitHubUserFragment : Fragment(), GitHubPresenter.View {

    @Inject
    lateinit var gitHubPresenter: GitHubPresenter

    private lateinit var user: String

    companion object {
        @JvmStatic
        fun newInstance(user: String) = GitHubUserFragment().apply {
            arguments = Bundle().apply {
                putString("user", user)
            }
        }
//        @JvmStatic
//        fun newInstance(user: String): GitHubUserFragment {
//            val fragment = GitHubUserFragment()
//            val bundle = Bundle()
//            bundle.putString("user", user)
//            fragment.arguments = bundle
//            return fragment
//        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as SampleApplication).getGitHubComponent().inject(this)
        gitHubPresenter.injectView(this)

//        if(null != arguments){
//            user = arguments!!.getString("user")
//        }
        arguments?.let {
            user = it.getString("user")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_git_hub_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gitHubPresenter.getUser(user)
    }

    override fun onLoading() {
        Log.v("GitHubPresenterViewImpl", "onLoading")
    }

    override fun onDismiss() {
        Log.v("GitHubPresenterViewImpl", "onDismiss")
    }

    override fun displaySuccess(gitHubUser: GitHubUser) {
        Log.v("GitHubPresenterViewImpl", "displaySuccess ${gitHubUser.username}")
    }

    override fun displayError(message: String) {
        Log.v("GitHubPresenterViewImpl", "displayError : $message")
    }
}
