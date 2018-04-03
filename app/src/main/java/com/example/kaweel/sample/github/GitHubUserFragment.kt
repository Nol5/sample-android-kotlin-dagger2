package com.example.kaweel.sample.github

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.kaweel.sample.R
import com.example.kaweel.sample.SampleApplication
import com.example.kaweel.sample.util.Keyboards
import kotlinx.android.synthetic.main.fragment_git_hub_user.*
import javax.inject.Inject


class GitHubUserFragment : Fragment(), GitHubPresenter.View {

    @Inject
    lateinit var gitHubPresenter: GitHubPresenter

    @Inject
    lateinit var keyboards: Keyboards

    companion object {
        @JvmStatic
        fun newInstance() = GitHubUserFragment()
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

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_git_hub_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadButton.setOnClickListener {
            if (userNameEditText.text.isNotBlank()) {
                userNameEditText.clearFocus()
                keyboards.hideKeyboard(userNameEditText)
                gitHubPresenter.getUser(userNameEditText.text.toString())
            }

        }
    }

    override fun onLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onDismiss() {
        progressBar.visibility = View.GONE
    }

    override fun displaySuccess(gitHubUser: GitHubUser) {

        Glide.with(this)
                .load(gitHubUser.avatarUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        Handler().post({
                            view?.let { Glide.with(it).load(model).apply(RequestOptions().placeholder(R.drawable.profile_default)).into(profileImageView) }
                        })
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                })
                .into(profileImageView)

        nameValTextView.text = gitHubUser.name
        urlValTextView.text = gitHubUser.htmlUrl
        blogValTextView.text = gitHubUser.blog
    }

    override fun displayError(message: String) {
        Snackbar.make(nameValTextView, message, Snackbar.LENGTH_LONG).show()
    }

}
