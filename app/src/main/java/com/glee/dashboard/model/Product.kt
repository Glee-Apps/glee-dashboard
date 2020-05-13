package com.glee.dashboard.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("ID")
    var id: Int,

    @SerializedName("CreatedAt")
    var createdAt: String,

    @SerializedName("UpdatedAt")
    var updatedAt: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("quantity")
    var quantity: Int,

    @SerializedName("cost")
    var cost: Int,

    @Ignore
    @SerializedName("images")
    var images: List<Image>
)