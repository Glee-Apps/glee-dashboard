package com.glee.dashboard.source

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private val interceptor = Interceptor { chain ->

        val url = chain.request().newBuilder().addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOjEsIlVzZXJuYW1lIjoiIn0.KsJ-JVxUnPFAeMJbA19P6wQBWVrB6fwI0ruc8KnN1qg")
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
            .baseUrl("http://172.20.10.3:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiInterface = getRetrofit().create(ApiInterface::class.java)
}