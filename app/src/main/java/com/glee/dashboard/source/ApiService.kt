package com.glee.dashboard.source

import android.annotation.SuppressLint
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("StaticFieldLeak")
object ApiService {

    private val interceptor = Interceptor { chain ->

        val url = chain.request().newBuilder().addHeader("Authorization", "Bearer ")
            .build()
        chain.proceed(url)
    }

    val i = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)


    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .addInterceptor(i)
        .build()

    private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .client(okHttpClient)
            .baseUrl("http://api.africasokoni.ke/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiInterface = getRetrofit().create(ApiInterface::class.java)
}