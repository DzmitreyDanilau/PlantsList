package com.example.coroutineemvvm.presentation.domain.interactor

import com.example.coroutineemvvm.presentation.data.repositories.FlowerRepository
import com.example.coroutineemvvm.presentation.model.Plant

class FlowerInteractorImpl(private val flowerRepository: FlowerRepository) : FlowerInteractor {

    override suspend fun fetchFlowers(): List<Plant> {

    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: FlowerInteractor? = null

        fun getInstance(flowerRepository: FlowerRepository) =
            instance ?: synchronized(this) {
                instance ?: FlowerInteractorImpl(flowerRepository).also { instance = it }
            }
    }

}