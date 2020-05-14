package com.glee.dashboard.model

import androidx.room.Embedded
import androidx.room.Relation

class ProductsWithImages {
    @Embedded
    var product: Product? = null

    @Relation(parentColumn = "id", entityColumn = "productId", entity = Image::class)
    var images: List<Image>? = null
}