package com.test.cermati.muhammad.fikri.ui.presenter

import android.content.Context
import com.test.cermati.muhammad.fikri.githubApi.RateLimitResponse
import com.test.cermati.muhammad.fikri.githubApi.SearchUserResponses
import com.test.cermati.muhammad.fikri.network.ServiceBuilder
import com.test.cermati.muhammad.fikri.network.githubApi.GitHubApiInterface
import com.test.cermati.muhammad.fikri.ui.interfaces.GitHubUsersView
import com.test.cermati.muhammad.fikri.utils.Constants.RATE_LIMIT
import com.test.cermati.muhammad.fikri.utils.CoroutineContextProvider
import com.test.cermati.muhammad.fikri.utils.isNotNull
import com.test.cermati.muhammad.fikri.utils.log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
* Created by Muhammad Fikri on 11/10/2020
* */
class GitHubUsersPresenter (private val context: Context?,
                            private val view : GitHubUsersView,
                            private val provider : CoroutineContextProvider = CoroutineContextProvider()) {
    fun search(param : String?,page : Int){
        GlobalScope.launch(provider.main){
            view.onShowLoading()
            try {
                if (param.isNotNull()){
                    ServiceBuilder.buildService(GitHubApiInterface::class.java)
                        .searchUsers(users = param!!,page = page)
                        .enqueue(object : Callback<SearchUserResponses>{
                            override fun onFailure(call: Call<SearchUserResponses>, t: Throwable) {
                                view.onHideLoading()
                                view.onFailedGetUsers(t.localizedMessage)
                            }
                            override fun onResponse(call: Call<SearchUserResponses>, response: Response<SearchUserResponses>) {
                                if (response.isNotNull()){
                                    if (response.code() == RATE_LIMIT){
                                        view.onHideLoading()
                                        view.onFailedGetUsers(response.message())
                                    } else {
                                        val searchUserResponses = response.body()
                                        if (searchUserResponses.isNotNull()){
                                            if (!searchUserResponses!!.message.isNullOrBlank()){
                                                view.onHideLoading()
                                                view.onFailedGetUsers(searchUserResponses.message)
                                            } else {
                                                if (searchUserResponses.users.isNotNull()){
                                                    view.onHideLoading()
                                                    view.onSuccessGetUsers(searchUserResponses.users!!)
                                                } else {
                                                    view.onHideLoading()
                                                    view.onFailedGetUsers("null users")
                                                }
                                            }
                                        } else {
                                            view.onHideLoading()
                                            view.onFailedGetUsers(searchUserResponses.toString())
                                        }
                                    }
                                } else {
                                    view.onHideLoading()
                                    view.onFailedGetUsers(response.body().toString())
                                }
                            }
                        })
                } else {
                    view.onHideLoading()
                    view.onFailedGetUsers("null param")
                }
            } catch (e : Exception){
                view.onHideLoading()
                view.onFailedGetUsers(e.localizedMessage)
            }
        }
    }

    fun limit(){
        GlobalScope.launch(provider.main){
            try {
                ServiceBuilder.buildService(GitHubApiInterface::class.java)
                    .rateLimit()
                    .enqueue(object : Callback<RateLimitResponse>{
                        override fun onFailure(call: Call<RateLimitResponse>, t: Throwable) {
                            log(t.localizedMessage)
                        }

                        override fun onResponse(call: Call<RateLimitResponse>, response: Response<RateLimitResponse>) {
                            log(response.body().toString())
                        }
                    })
            } catch (e : Exception){

            }
        }
    }
}