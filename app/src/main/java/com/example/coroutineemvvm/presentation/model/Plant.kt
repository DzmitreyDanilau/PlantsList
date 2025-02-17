package com.example.coroutineemvvm.presentation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coroutineemvvm.presentation.utils.DB_NAME

@Entity(tableName = DB_NAME)
data class Plant(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
) {
    override fun toString() = name
}

inline class GrowZone(val number: Int)

val NoGrowZone = GrowZone(-1)