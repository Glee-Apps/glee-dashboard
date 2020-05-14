package com.glee.dashboard.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glee.dashboard.model.Product
import com.glee.dashboard.model.ProductsWithImages

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setProduct(product: Product)

    @Query("DELETE FROM product_table")
    fun nukeProductTable()

    @Query("SELECT * from product_table")
    fun getProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM product_table")
    fun loadProductsWithImages(): LiveData<List<ProductsWithImages>>
}