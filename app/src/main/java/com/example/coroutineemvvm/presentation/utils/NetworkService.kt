package com.example.coroutineemvvm.presentation.utils

import com.example.coroutineemvvm.presentation.model.Plant
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(FlowerService::class.java)

    //When we try to do some actions with fetched data,
    // best practice would be do this work in another CoroutinesScope (IO/Default)

    suspend fun getPlants(): List<Plant> = withContext(IO) {
        val result = service.getPlants()
        result.shuffled()
    }

}

interface FlowerService {
    @GET("googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json")
    suspend fun getPlants(): List<Plant>

}
