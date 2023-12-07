package com.faysalh.eproducts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faysalh.eproducts.model.descripitem.ProductX
import com.faysalh.eproducts.model.productitem.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase:RoomDatabase() {
    abstract fun productDao():ProductDao

    companion object{
        @Volatile
        var INSTANCE :ProductDatabase?=null

        @Synchronized
        fun getInstance(context: Context):ProductDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,ProductDatabase::class.java,"database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as ProductDatabase
        }
    }
}