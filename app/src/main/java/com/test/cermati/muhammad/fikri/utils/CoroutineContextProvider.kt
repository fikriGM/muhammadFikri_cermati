package com.test.cermati.muhammad.fikri.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/*
* Created by Muhammad Fikri on 11/10/2020
* */

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
}