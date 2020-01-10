package com.example.coroutineemvvm.presentation.domain.interactor

import androidx.lifecycle.LiveData
import com.example.coroutineemvvm.presentation.model.Plant

interface FlowerInteractor {
    fun getPlants(): LiveData<List<Plant>>
    fun getPlantsWithGrowZone(growZone: Int): LiveData<List<Plant>>
}