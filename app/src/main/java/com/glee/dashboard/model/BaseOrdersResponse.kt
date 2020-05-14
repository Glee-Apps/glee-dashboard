package com.glee.dashboard.model

import com.google.gson.annotations.SerializedName

data class BaseOrdersResponse(@SerializedName("data") var request: List<Order>)