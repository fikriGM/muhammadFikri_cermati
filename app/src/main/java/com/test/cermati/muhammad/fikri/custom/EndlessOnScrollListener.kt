package com.test.cermati.muhammad.fikri.custom

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/*
* Created by Muhammad Fikri on 11/10/2020
* */

abstract class EndlessOnScrollListener : RecyclerView.OnScrollListener() {
    var previous = 0
    var load : Boolean = true
    private var mLoading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
        if (mLoading) {
            if (totalItemCount > previous) {
                mLoading = false
                previous = totalItemCount
            }
        }
        if (!mLoading && (lastVisibleItem == totalItemCount-1) && load) {
            onLoadMore()
            mLoading = true
        }
    }

    abstract fun onLoadMore()

}