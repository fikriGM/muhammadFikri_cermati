package com.test.cermati.muhammad.fikri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.cermati.muhammad.fikri.ui.fragment.GitHubUsersFragment

/*
* Created by Muhammad Fikri on 11/10/2020
* */

class MainActivity : AppCompatActivity(),MainActivityView {

    private var presenter : MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this,this)

        presenter?.replaceFragment(GitHubUsersFragment())
    }
}