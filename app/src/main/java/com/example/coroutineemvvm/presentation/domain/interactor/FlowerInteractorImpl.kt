package com.example.coroutineemvvm.presentation.domain.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.coroutineemvvm.presentation.data.repositories.PlantRepository
import com.example.coroutineemvvm.presentation.model.Plant

class FlowerInteractorImpl(private val flowerRepository: PlantRepository) : FlowerInteractor {

    //I'm using experomental androidx.lifecycle.liveData

    override fun getPlants(): LiveData<List<Plant>> =
        liveData { flowerRepository.tryToUdpateCachedPlants() }

    override fun getPlantsWithGrowZone(growZone: Int): LiveData<List<Plant>> =
        liveData { flowerRepository.fetchPlantsListWithGrowZone(growZone) }


    companion object {
        @Volatile
        private var instance: FlowerInteractor? = null

        fun getInstance(flowerRepository: PlantRepository) =
            instance ?: synchronized(this) {
                instance ?: FlowerInteractorImpl(flowerRepository).also { instance = it }
            }
    }

}