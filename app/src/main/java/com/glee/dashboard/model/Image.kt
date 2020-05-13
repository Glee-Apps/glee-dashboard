package com.glee.dashboard.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "image_table")
data class Image(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("ID")
    var id: Int,

    @SerializedName("CreatedAt")
    var createdAt: String,

    @SerializedName("UpdatedAt")
    var updatedAt: String,

    @SerializedName("product_id")
    var productId: Int,

    @SerializedName("url")
    var url: String
)