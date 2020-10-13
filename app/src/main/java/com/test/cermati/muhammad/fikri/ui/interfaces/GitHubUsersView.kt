package com.test.cermati.muhammad.fikri.ui.interfaces

import com.test.cermati.muhammad.fikri.model.User


/*
* Created by Muhammad Fikri on 11/10/2020
* */
interface GitHubUsersView {
    fun onSuccessGetUsers(users : List<User>){}
    fun onFailedGetUsers(error : String?){}

    fun onShowLoading(){}
    fun onHideLoading(){}
}