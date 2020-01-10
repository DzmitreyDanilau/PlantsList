package com.example.coroutineemvvm.presentation.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coroutineemvvm.presentation.model.Plant
import com.example.coroutineemvvm.presentation.utils.DB_NAME

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun plantDao(): FlowerDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                    .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME).build()
        }
    }
}