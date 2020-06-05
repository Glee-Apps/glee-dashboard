package com.glee.dashboard.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glee.dashboard.model.Image

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setImage(image: Image)

    @Query("DELETE FROM image_table")
    fun nukeImageTable()

    @Query("SELECT * from image_table")
    fun getImages(): LiveData<List<Image>>

    @Query("SELECT * from image_table Where productId=:id")
    fun getImagesForProduct(id: Int): LiveData<List<Image>>
}