package com.example.kaweel.sample.github

import com.google.gson.annotations.SerializedName

data class GitHubUser(
        @SerializedName("login") val login: String = "",
        @SerializedName("id") val id: Int = 0,
        @SerializedName("avatar_url") val avatarUrl: String = "",
        @SerializedName("repos_url") val repoUrl: String = "",
        @SerializedName("blog") val blog: String = "",
        @SerializedName("name") val name: String = "",
        @SerializedName("html_url") val htmlUrl: String = ""
)
