package com.example.coroutineemvvm.presentation.data.repositories

import com.example.coroutineemvvm.presentation.model.Plant

interface FlowerRepository {
    suspend fun fetchDataFromNetwork(): List<Plant>
}