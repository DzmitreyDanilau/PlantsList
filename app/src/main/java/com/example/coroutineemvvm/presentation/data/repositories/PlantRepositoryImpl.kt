package com.example.coroutineemvvm.presentation.data.repositories

import com.example.coroutineemvvm.presentation.data.datasource.FlowerDao
import com.example.coroutineemvvm.presentation.model.Plant
import com.example.coroutineemvvm.presentation.utils.NetworkService

class PlantRepositoryImpl(
    private val plantDao: FlowerDao,
    private val plantService: NetworkService
) : PlantRepository {


    override suspend fun tryToUdpateCachedPlants() {
        if (shouldUpdateChachedPlants()) fetchPlantsList()
    }

    override suspend fun fetchPlantsList(): List<Plant> {
        return plantService.getPlants().apply { plantDao.insertAll(this) }
    }

    override suspend fun fetchPlantsListWithGrowZone(grownZone: Int) {
        plantDao.getPlantsByGrownZone(grownZone)
    }

    override fun shouldUpdateChachedPlants(): Boolean {
        //TODO for example chacke database status and then decide
        return true
    }

    companion object {
        @Volatile
        private var instance: PlantRepositoryImpl? = null

        fun getInstance(plantDao: FlowerDao, plantService: NetworkService) =
            instance ?: synchronized(this) {
                instance ?: PlantRepositoryImpl(plantDao, plantService).also { instance = it }
            }
    }
}