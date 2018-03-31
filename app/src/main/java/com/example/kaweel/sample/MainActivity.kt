package com.example.kaweel.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kaweel.sample.github.GitHubUserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initial()
    }

    private fun initial() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.contentBox, GitHubUserFragment.newInstance("Nol5"))
                .commit()
    }
}
