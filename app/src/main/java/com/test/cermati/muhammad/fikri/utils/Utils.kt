package com.test.cermati.muhammad.fikri.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/*
* Created by Muhammad Fikri on 11/10/2020
* */

fun log(content : String?){
    val tag = "TAG"
    if (!content.isNullOrBlank()){
        val maxLogSize = 1000
        val stringLength = content.length
        for (i in 0..stringLength/maxLogSize) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = if (end > content.length) content.length else end
            Log.w(tag, content.substring(start, end))
        }
    } else {
        Log.w(tag,"$content")
    }
}

fun log(where: Class<Any>, content: String?) {
    Log.v(where.simpleName, "$content")
}
fun Context.toast(text : String?){
    Toast.makeText(this,"$text", Toast.LENGTH_SHORT).show()
}

fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.invisible(){
    this.visibility = View.INVISIBLE
}
fun View.gone(){
    this.visibility = View.GONE
}
fun View.isVisible() : Boolean {
    return this.visibility == View.VISIBLE
}
fun View.isInvisible() : Boolean {
    return this.visibility == View.INVISIBLE
}
fun View.isGone() : Boolean {
    return this.visibility == View.GONE
}

fun ImageView.fromUrl(url : String?){
    val options: RequestOptions = RequestOptions()
        .centerCrop()
    Glide.with(this).load(url).apply(options).into(this)
}

fun Any?.isNotNull() : Boolean {
    return this != null
}



