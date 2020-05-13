package com.glee.dashboard.source

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glee.dashboard.model.Image
import com.glee.dashboard.model.Order
import com.glee.dashboard.model.Product

@androidx.room.Database(
    entities = [Image::class, Order::class, Product::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    companion object {

        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database? {
            if (INSTANCE == null) {
                synchronized(Database::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            Database::class.java, "glee_dashboard_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}