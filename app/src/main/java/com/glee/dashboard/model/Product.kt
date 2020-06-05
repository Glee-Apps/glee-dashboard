package com.glee.dashboard.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "product_table")
@Parcelize
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

    @SerializedName("category_id")
    var category: Int,


    @SerializedName("images")
    @Ignore var images: @RawValue List<Image>?
) : Parcelable {
    constructor() : this(0, "", "", "", "", 0, 0, 0, null)
}