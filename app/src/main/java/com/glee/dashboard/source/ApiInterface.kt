package com.glee.dashboard.source

import com.glee.dashboard.model.BaseOrdersResponse
import com.glee.dashboard.model.BaseProductsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @GET("me/orders")
    fun getOrders(): Call<BaseOrdersResponse>

    @Headers("Content-Type: application/json")
    @GET("me/products")
    fun getProducts(): Call<BaseProductsResponse>
}