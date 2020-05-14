package com.glee.dashboard.model

import androidx.room.*
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

    @SerializedName("images")
    @Ignore var images: List<Image>?
) {
    constructor() : this(0, "", "", "", "", 0, 0, null)
}