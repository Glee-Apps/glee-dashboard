package com.glee.dashboard.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("ID")
    var id: Int,

    @SerializedName("CreatedAt")
    var createdAt: String,

    @SerializedName("UpdatedAt")
    var updatedAt: String,

    @SerializedName("quantity")
    var quantity: Int,

    @SerializedName("user_id")
    var userId: Int,

    @SerializedName("product_id")
    var productId: Int,

    @SerializedName("status_id")
    var statusId: Int
){
    constructor() : this(0, "", "", 0, 0, 0, 0)
}