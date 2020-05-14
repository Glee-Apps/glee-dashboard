package com.glee.dashboard.model

import com.google.gson.annotations.SerializedName

data class BaseProductsResponse(@SerializedName("data") var request: List<Product>)