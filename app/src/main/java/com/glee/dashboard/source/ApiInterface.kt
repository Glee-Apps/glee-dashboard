package com.glee.dashboard.source

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("auth/generate-jwt")
    fun getToken(@Body req: String): Call<String>
}