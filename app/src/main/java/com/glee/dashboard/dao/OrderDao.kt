package com.glee.dashboard.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glee.dashboard.model.Order

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setOrder(orders: Order)

    @Query("DELETE FROM order_table")
    fun nukeOrderTable()

    @Query("SELECT * from order_table")
    fun getOrders(): LiveData<List<Order>>
}