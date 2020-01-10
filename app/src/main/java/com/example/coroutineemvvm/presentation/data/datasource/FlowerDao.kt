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

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getPlantsByGrownZone(growZoneNumber: Int): LiveData<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(plants: List<Plant>)


}