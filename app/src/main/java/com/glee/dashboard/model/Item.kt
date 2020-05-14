package com.glee.dashboard.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item_table")
data class Item(
    @SerializedName("amount")
    @ColumnInfo(name = "amount")
    var amount: Float?,

    @SerializedName("product_id")
    @ColumnInfo(name = "product_id")
    var productId: String?,

    @SerializedName("quantity")
    @ColumnInfo(name = "quantity")
    var quantity: Int
)