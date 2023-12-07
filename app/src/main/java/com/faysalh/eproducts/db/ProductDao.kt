package com.faysalh.eproducts.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faysalh.eproducts.model.productitem.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("Select * From productxtbl")
    fun getAllMeals(): LiveData<List<Product>>


}