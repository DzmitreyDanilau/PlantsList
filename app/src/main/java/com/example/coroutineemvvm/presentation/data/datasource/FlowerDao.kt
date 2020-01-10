package com.example.coroutineemvvm.presentation.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coroutineemvvm.presentation.model.Plant
import com.example.coroutineemvvm.presentation.utils.DB_NAME

@Dao
interface FlowerDao {
    @Query("SELECT * FROM $DB_NAME ORDER BY name")
    fun getPlants(): LiveData<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Plant>)

}