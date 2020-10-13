package com.test.cermati.muhammad.fikri.model

import com.google.gson.annotations.SerializedName

/*
* Created by Muhammad Fikri on 11/10/2020
* */
data class User (
    @SerializedName("id")
    var id : Int? = null,
    @SerializedName("login")
    var username : String? = null,
    @SerializedName("avatar_url")
    var avatarUrl : String? = null
)