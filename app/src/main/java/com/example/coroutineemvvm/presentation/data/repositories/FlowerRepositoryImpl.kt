package com.example.coroutineemvvm.presentation.data.repositories

import com.example.coroutineemvvm.presentation.data.datasource.FlowerDao
import com.example.coroutineemvvm.presentation.model.Plant
import com.example.coroutineemvvm.presentation.utils.NetworkService

class FlowerRepositoryImpl(
    private val plantDao: FlowerDao,
    private val plantService: NetworkService
) : FlowerRepository {

    override suspend fun fetchDataFromNetwork(): List<Plant> {
        val plants = plantService.getPlants()
        plantDao.insertAll(plants)
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: FlowerRepository? = null

        fun getInstance(plantDao: FlowerDao, plantService: NetworkService) =
            instance ?: synchronized(this) {
                instance ?: FlowerRepositoryImpl(plantDao, plantService).also { instance = it }
            }
    }
}