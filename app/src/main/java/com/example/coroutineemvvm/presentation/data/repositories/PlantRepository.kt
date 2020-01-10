package com.example.coroutineemvvm.presentation.data.repositories

import com.example.coroutineemvvm.presentation.model.Plant

interface PlantRepository {
    suspend fun fetchPlantsList(): List<Plant>
    suspend fun fetchPlantsListWithGrowZone(grownZone: Int)
    fun shouldUpdateChachedPlants(): Boolean
    suspend fun tryToUdpateCachedPlants()
}