package com.test.cermati.muhammad.fikri

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.cermati.muhammad.fikri.utils.CoroutineContextProvider

/*
* Created by Muhammad Fikri on 11/10/2020
* */

class MainActivityPresenter(private val context: MainActivity,
                            private val view : MainActivityView,
                            private val provider : CoroutineContextProvider = CoroutineContextProvider()) {

    fun replaceFragment(fragment: Fragment, backStack : Boolean = false, clear : Boolean = false){
        if (clear)
            context.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val fragmentTransaction = context.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_ll, fragment)
        if (backStack){
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }
}