package com.example.kaweel.sample.github

import com.google.gson.annotations.SerializedName

data class GitHubUser(
        @SerializedName("login") val username: String = "",
        @SerializedName("id") val id: Int = 0,
        @SerializedName("avatar_url") val imageUrl: String = "",
        @SerializedName("repos_url") val repoUrl: String = ""
)
