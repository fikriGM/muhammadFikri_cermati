package com.test.cermati.muhammad.fikri.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.cermati.muhammad.fikri.R
import com.test.cermati.muhammad.fikri.custom.EndlessOnScrollListener
import com.test.cermati.muhammad.fikri.model.User
import com.test.cermati.muhammad.fikri.ui.interfaces.GitHubUsersView
import com.test.cermati.muhammad.fikri.ui.presenter.GitHubUsersPresenter
import com.test.cermati.muhammad.fikri.utils.*
import kotlinx.android.synthetic.main.fragment_users_github.*
import m.test.cermati.muhammad.fikri.adapter.SimpleRecyclerAdapter
import kotlin.collections.ArrayList

/*
* Created by Muhammad Fikri on 11/10/2020
* */
class GitHubUsersFragment : Fragment(), GitHubUsersView {

    private var presenter : GitHubUsersPresenter? = null

    private var userList : ArrayList<User>? = null
    private var userAdapter : SimpleRecyclerAdapter<User>? = null

    private var page : Int = 0
    private var countDownTimer: CountDownTimer? = null
    private var scrollListener : EndlessOnScrollListener? = null
    private var searchText : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users_github, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_users?.layoutManager = LinearLayoutManager(context)
        userAdapter = SimpleRecyclerAdapter(
            context!!,
            SimpleRecyclerAdapter.Companion.SimpleRecyclerTag.GITHUB_USER_LIST,
            userList) {
            context?.toast(it.username)
        }
        recycler_users?.adapter = userAdapter
        notifyData()
        scrollListener = object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                if (searchText.isNotNull()){
                    presenter?.search(param = searchText,page = page)
                }
            }
        }
        recycler_users?.addOnScrollListener(scrollListener!!)
        search_users?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                if (countDownTimer != null){
                    countDownTimer?.cancel()
                }
                onShowLoading()
                countDownTimer = object : CountDownTimer(1200,500){
                    override fun onFinish() {
                        page = 1
                        userList?.clear()
                        userAdapter?.notifyDataSetChanged()
                        scrollListener?.previous = 0
                        if (text.toString().isNotBlank()){
                            searchText = text.toString()
                            presenter?.search(param = searchText,page = page)
                        } else {
                            searchText = null
                            userList?.clear()
                            onHideLoading()
                            notifyData()
                        }

                    }
                    override fun onTick(millisUntilFinished: Long) {
                    }
                }.start()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = GitHubUsersPresenter(context,this)
        userList = arrayListOf()
    }

    override fun onSuccessGetUsers(users : List<User>) {
        userList?.addAll(users)
        notifyData()
        page++
        presenter?.limit()
    }

    override fun onFailedGetUsers(error: String?) {
        log(javaClass,error)
        context?.toast(error)
        notifyData()
    }

    override fun onPause() {
        super.onPause()
        if (countDownTimer != null){
            countDownTimer?.cancel()
        }
    }

    override fun onStop() {
        super.onStop()
        if (countDownTimer != null){
            countDownTimer?.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (countDownTimer != null){
            countDownTimer?.cancel()
        }
    }

    override fun onShowLoading() {
        loading_users?.visible()
        recycler_users?.gone()
        empty_users?.gone()
    }

    override fun onHideLoading() {
        loading_users?.gone()
        recycler_users?.visible()
        empty_users?.visible()
    }

    private fun notifyData() {
        userAdapter?.notifyDataSetChanged()
        if (userList.isNotNull()){
            if (userList!!.isEmpty()){
                empty_users?.visible()
            } else {
                empty_users?.gone()
            }
        }
    }
}