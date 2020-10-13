package com.test.cermati.muhammad.fikri.network

import com.test.cermati.muhammad.fikri.network.githubApi.GitHubApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers

/*
* Created by Muhammad Fikri on 11/10/2020
* */
object ServiceBuilder {

    private fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }

    fun<T> buildService(service: Class<T>): T{
        val baseUrl =
            when(service) {
                GitHubApiInterface::class.java -> {
                    "https://api.github.com"
                }
                else -> ""
            }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build().create(service)
    }
}