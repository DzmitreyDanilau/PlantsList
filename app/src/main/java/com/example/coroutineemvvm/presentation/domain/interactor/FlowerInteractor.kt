package com.example.coroutineemvvm.presentation.domain.interactor

import com.example.coroutineemvvm.presentation.model.Plant

interface FlowerInteractor {
    suspend fun fetchFlowers(): List<Plant>
}